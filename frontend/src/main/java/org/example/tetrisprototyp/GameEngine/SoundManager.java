package org.example.tetrisprototyp.GameEngine;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Spielt Sounds parallel zum Spiel ab, damit diese den Spielfluss unterbrechen.
 * Wird von der GameEngine informiert, wenn sich der Status des Spiels ändert.
 */
public class SoundManager implements Observer {

    // Thread-Pool für die asynchrone Verarbeitung. newCachedThreadPool() erstellt dabei neue Threads oder
    // wiederverwendet alte. Stellt die Erweiterbarkeit des Codes sicher, da nun auch z.B. Musik
    // hinzugefügt werden kann.
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    // Damit die Datei nicht mehrmals geladen wird, wird ein Cache für sie erstellt
    private static final ConcurrentMap<String, byte[]> soundCache = new ConcurrentHashMap<>();
    // Cache für das konvertierte AudioFormat jeder Sound-Datei (wird beim Laden ermittelt und gespeichert)
    private final ConcurrentMap<String, AudioFormat> formatCache = new ConcurrentHashMap<>();

    private volatile Clip currentClip = null;  // Speichert den gerade laufenden Clip

    /**
     * Observer-Funktion. Wenn eine Reihe gefüllt wurde ("scored"), wird der Sound abgespielt.
     * Wenn das Spiel zu Ende ist, werden alle Threads geschlossen.
     */
    @Override
    public void update(String event) {
        if ("scored".equals(event)) {
            playSoundAsync("8-bit-explosion.wav");
        } else if ("gameOver".equals(event)) {
            shutdown();
        }
    }

    /**
     * Sound wird asynchron abgespielt, damit der aufrufende Thread nicht blockiert wird.
     */
    private void playSoundAsync(String resourceName) {
        pool.submit(() -> playSound(resourceName));
    }

    /**
     * Spielt den Sound synchron in einem separaten Thread ab.
     * Lädt den Sound bei Bedarf in den Cache und gibt ihn über javax.sound.sampled wieder.
     */
    private void playSound(String resourceName) {
        try {
            // Lädt den Sound aus dem Cache oder vom Dateisystem (computeIfAbsent sorgt für Thread-Sicherheit)
            byte[] data = soundCache.computeIfAbsent(resourceName, this::loadSound);

            // Falls ein alter Clip läuft, wird dieser gestoppt und geschlossen.
            // Somit wird immer nur ein Sound abgespielt, auch wenn mehrere Reihen vervollständigt wurden.
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
            }

            // Erstelle einen AudioInputStream
            AudioInputStream ais = new AudioInputStream(
                    new java.io.ByteArrayInputStream(data),
                    getAudioFormat(resourceName),
                    data.length
            );

            // Abspielen des Clips
            currentClip = AudioSystem.getClip();
            currentClip.open(ais);
            currentClip.start();

            // Nachdem der Clip abgespielt wurde, wird er geschlossen.
            currentClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    currentClip.close();
                    // Falls ein neuer Clip abgespielt wurde, während der andere noch lief, muss dieser null werden.
                    if (currentClip == this.currentClip) {
                        this.currentClip = null;  // Nur aufräumen, wenn es noch der aktuelle ist
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt den Sound aus dem sounds-Ordner und gibt ihn als byte[] zurück
     * Gibt die Rohdaten als byte[] zurück und speichert das resultierende Format im formatCache.
     */
    private byte[] loadSound(String sound) {
        try {
            // Sound im /sounds-Verzeichnis suchen (muss im JAR unter /sounds/ liegen)
            URL url = getClass().getResource("/sounds/" + sound);
            if (url == null) throw new IllegalArgumentException("Sound nicht gefunden: " + sound);

            // Erstellen des AudioInputStreams
            try (AudioInputStream ais = AudioSystem.getAudioInputStream(url)) {
                // Format der Datei speichern
                AudioFormat format = ais.getFormat();

                // Alle Audiodaten auf einmal in ein byte[] einlesen
                byte[] data = ais.readAllBytes();

                // Format für die spätere Wiedergabe in Cache speichern
                formatCache.put(sound, format);

                return data;
            }

        } catch (Exception e) {
            throw new RuntimeException("Konnte Sound nicht laden: " + sound, e);
        }
    }


    /**
     * Liefert das zuvor beim Laden gespeicherte konvertierte AudioFormat zurück.
     */
    private AudioFormat getAudioFormat(String resourceName) {
        return formatCache.get(resourceName);
    }

    /**
     * Schließt alle Threads. Wird bei Spielende aufgerufen.
     */
    public static void shutdown() {
        pool.shutdownNow();
    }
}

