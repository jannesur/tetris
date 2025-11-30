package de.ostfalia.tetris.history;


public class HistoryRequest {

    private int score;
    private int level;
    private Long playerId;

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

    public Long getPlayerId() { 
        return playerId; 
    }

    public void setPlayerId(Long playerId) { 
        this.playerId = playerId; 
    }
}

