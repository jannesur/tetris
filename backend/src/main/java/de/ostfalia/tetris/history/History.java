package de.ostfalia.tetris.history;

import java.time.LocalDate;
import java.time.LocalDateTime;

import de.ostfalia.tetris.player.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class History {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private int level;
    private LocalDate historyDate;
    private int rowsCleared;
    private int difficulty;
    private LocalDateTime playedAt;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public History() {

    }

    public History(Long id, int score, int level, int rowsCleared, int difficulty,
                   LocalDateTime playedAt, Player player) {
        this.id = id;
        this.score = score;
        this.level = level;
        this.rowsCleared = rowsCleared;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "History [id=" + id
                + ", score=" + score
                + ", level=" + level
                + ", rowsCleared=" + rowsCleared
                + ", difficulty=" + difficulty
                + ", playedAt=" + playedAt + "]";
    }



}
