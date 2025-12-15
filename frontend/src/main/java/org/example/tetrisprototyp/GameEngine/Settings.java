package org.example.tetrisprototyp.GameEngine;

// Klasse für das Speichern der Schwierigkeit.
public class Settings {
    private static int difficulty = 1;
    public static int getDifficulty() { return difficulty; }
    public static void setDifficulty(int d) { difficulty = d; }
}

