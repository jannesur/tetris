package org.example.tetrisprototyp.GameEngine;


// Speichert den Schwierigkeitsgrad des Spiels, damit dieser von dem GameController verwendet werden kann.
public class Settings {
    private static int difficulty = 1;
    public static int getDifficulty() { return difficulty; }
    public static void setDifficulty(int d) { difficulty = d; }
}

