package org.example.tetrisprototyp.History;

public class HistoryRequestDTO {

    private int score;
    private int level;
    private int rowsCleared;
    private int difficulty;
    private String playedAt;

    public HistoryRequestDTO(int score, int level, int rowsCleared,
                             int difficulty, String playedAt) {
        this.score = score;
        this.level = level;
        this.rowsCleared = rowsCleared;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getRowsCleared() {
        return rowsCleared;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getPlayedAt() {
        return playedAt;
    }
}

