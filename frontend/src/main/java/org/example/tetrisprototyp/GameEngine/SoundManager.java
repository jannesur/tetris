package org.example.tetrisprototyp.GameEngine;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SoundManager implements Observer {

    private static final ExecutorService pool = Executors.newCachedThreadPool();

    // Cache: Sounddatei als byte[]
    private static final ConcurrentMap<String, byte[]> soundCache = new ConcurrentHashMap<>();

    @Override
    public void update(String event) {
        if ("scored".equals(event)) {
            playSoundAsync("8-bit-explosion.wav");
        } else if ("gameOver".equals(event)) {
            shutdown();
        }
    }

    private void playSoundAsync(String resourceName) {
        pool.submit(() -> playSound(resourceName));
    }

    private void playSound(String resourceName) {
        try {
            byte[] data = soundCache.computeIfAbsent(resourceName, this::loadSound);
            AudioInputStream ais = new AudioInputStream(
                    new java.io.ByteArrayInputStream(data),
                    getAudioFormat(resourceName),
                    data.length
            );

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lädt Sound in byte[] und merkt sich das Format
    private byte[] loadSound(String resourceName) {
        try {
            URL url = getClass().getResource("/sounds/" + resourceName);
            if (url == null) throw new IllegalArgumentException("Sound nicht gefunden: " + resourceName);

            try (AudioInputStream ais = AudioSystem.getAudioInputStream(url)) {
                AudioFormat baseFormat = ais.getFormat();
                AudioFormat targetFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        baseFormat.getSampleRate(),
                        16,
                        baseFormat.getChannels(),
                        baseFormat.getChannels() * 2,
                        baseFormat.getSampleRate(),
                        false
                );
                try (AudioInputStream converted = AudioSystem.getAudioInputStream(targetFormat, ais)) {
                    byte[] data = converted.readAllBytes();
                    // Speichere Format für später
                    formatCache.put(resourceName, targetFormat);
                    return data;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Konnte Sound nicht laden: " + resourceName, e);
        }
    }

    private final ConcurrentMap<String, AudioFormat> formatCache = new ConcurrentHashMap<>();

    private AudioFormat getAudioFormat(String resourceName) {
        return formatCache.get(resourceName);
    }

    public static void shutdown() {
        pool.shutdownNow();
    }
}

