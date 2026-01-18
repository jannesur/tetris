package org.example.tetrisprototyp.History;

import org.example.tetrisprototyp.GameEngine.GameStats;
import org.example.tetrisprototyp.GameEngine.Observer;

import java.time.LocalDateTime;

public class HistorySaver implements Observer {

    private final HistoryService historyService = new HistoryService();
    private final String jwtToken;
    private final String username;

    public HistorySaver(String username, String jwtToken) {
        this.username = username;
        this.jwtToken = jwtToken;
    }

    @Override
    public void update(String event) {

        if (event.equals("gameOver")) {
            System.out.println("Historie wird gespeichert");


            GameHistoryDTO history = new GameHistoryDTO(
                    username,
                    GameStats.getScore(),
                    GameStats.getLevel(),
                    GameStats.getLinesScored(),
                    GameStats.getDifficulty(),
                    LocalDateTime.now().toString()
            );

            System.out.println("Username: " + history.getUsername());
            System.out.println("Score: " + history.getScore());
            System.out.println("Level: " + history.getLevel());
            System.out.println("Lines: " + history.getRowsCleared());
            System.out.println("Difficulty: " + history.getDifficulty());
            System.out.println("Played at: " + history.getPlayedAt());




            historyService.saveHistoryAsync(history, jwtToken)
                    .exceptionally(ex -> {
                        ex.printStackTrace();
                        return null;
                    });

        }

    }

}
