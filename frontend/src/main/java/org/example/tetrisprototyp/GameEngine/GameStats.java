package org.example.tetrisprototyp.GameEngine;

public class GameStats {


    private static int score;
    private static int level;
    private static int linesScored;
    private static int difficulty;


    // --- Getter ---
    public static int getScore() {
        return score;
    }

    public static int getLevel() {
        return level;
    }

    public static int getLinesScored() {
        return linesScored;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    // --- Setter / Update-Methoden ---
    public static void setScore(int score) {
        GameStats.score = score;
    }

    public static void setLevel(int level) {
        GameStats.level = level;
    }

    public static void setLinesScored(int linesScored) {
        GameStats.linesScored = linesScored;
    }

    public static void setDifficulty(int difficulty) {
        GameStats.difficulty = difficulty;
    }




}
