package de.ostfalia.tetris.history;

public class HistoryResponse {

    private String username;
    private int score;
    private int level;
    private int rowsCleared;
    private int difficulty;
    private String playedAt;

    public HistoryResponse() {
    }

    public HistoryResponse(String username,
                           int score,
                           int level,
                           int rowsCleared,
                           int difficulty,
                           String playedAt) {
        this.username = username;
        this.score = score;
        this.level = level;
        this.rowsCleared = rowsCleared;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
    }

    public static HistoryResponse from(History history) {
        String username = history.getPlayer() != null ? history.getPlayer().getUsername() : null;
        String playedAt = history.getPlayedAt() != null ? history.getPlayedAt().toString() : null;

        return new HistoryResponse(
                username,
                history.getScore(),
                history.getLevel(),
                history.getRowsCleared(),
                history.getDifficulty(),
                playedAt
        );
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
