package de.ostfalia.tetris.history;

import java.time.LocalDate;

import de.ostfalia.tetris.player.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class History {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private int level;
    private LocalDate historyDate;
    @ManyToOne
    private Player player;

    public History() {

    }

    public History(Long id, int score, int level, LocalDate historyDate, Player player) {
        this.id = id;
        this.score = score;
        this.level = level;
        this.historyDate = historyDate;
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

    public LocalDate getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(LocalDate historyDate) {
        this.historyDate = historyDate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "History [id=" + id + ", score=" + score + ", level=" + level + ", historyDate=" + historyDate
                + ", player=" + player + "]";
    }

}
