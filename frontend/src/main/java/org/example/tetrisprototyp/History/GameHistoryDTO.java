package org.example.tetrisprototyp.History;


/*
Beispiel JSON-Datei:
{
  "username": "LangerBenutzername123456789",
  "score": 12300,
  "level": 8,
  "rowsCleared": 34,
  "difficulty": "Mittel",
  "playedAt": "2025-01-10T14:32:00"
}
 */

public class GameHistoryDTO {

    private String username;
    private int score;
    private int level;
    private int rowsCleared;
    private int difficulty;
    private String playedAt; // ISO-8601 String

    public GameHistoryDTO() {
    }

    // Konstruktor
    public GameHistoryDTO(String username, int score, int level,
                          int rowsCleared, int difficulty, String playedAt) {
        this.username = username;
        this.score = score;
        this.level = level;
        this.rowsCleared = rowsCleared;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
    }

    // Getter & Setter (wichtig für JSON)

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
