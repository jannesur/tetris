package org.example.tetrisprototyp.History;

public class HistoryRequestDTO {

    private Long playerId;
    private int score;
    private int level;
    private int rowsCleared;
    private int difficulty;
    private String playedAt;

    public HistoryRequestDTO() {
    }

    public HistoryRequestDTO(Long playerId,
                             int score,
                             int level,
                             int rowsCleared,
                             int difficulty,
                             String playedAt) {
        this.playerId = playerId;
        this.score = score;
        this.level = level;
        this.rowsCleared = rowsCleared;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRowsCleared() {
        return rowsCleared;
    }

    public void setRowsCleared(int rowsCleared) {
        this.rowsCleared = rowsCleared;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(String playedAt) {
        this.playedAt = playedAt;
    }
}
