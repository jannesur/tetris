package de.ostfalia.tetris.history;

public class HistoryResponse {

    private String username;
    private int score;
    private int level;
    private int rowsCleared;
    private int difficulty;
    private String playedAt;

    public HistoryResponse(History history) {
        this.username = history.getPlayer().getUsername();
        this.score = history.getScore();
        this.level = history.getLevel();
        this.rowsCleared = history.getRowsCleared();
        this.difficulty = history.getDifficulty();
        this.playedAt = history.getPlayedAt() != null ? history.getPlayedAt().toString() : null;
    }

    public String getUsername() {
        return username;
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
