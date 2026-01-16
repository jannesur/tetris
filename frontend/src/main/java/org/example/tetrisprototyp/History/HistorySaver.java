package org.example.tetrisprototyp.History;

import org.example.tetrisprototyp.GameEngine.GameStats;
import org.example.tetrisprototyp.GameEngine.Observer;
import org.example.tetrisprototyp.UserManagement.UserSession;

import java.time.LocalDateTime;

public class HistorySaver implements Observer {

    private final HistoryService historyService = new HistoryService();
    private final UserSession session;

    public HistorySaver(UserSession session) {
        this.session = session;
    }

    @Override
    public void update(String event) {

        if (event.equals("gameOver")) {
            System.out.println("Historie wird gespeichert");

            if (session.getJwt() == null || session.getPlayerId() == null) {
                System.out.println("Keine aktive Session gefunden, Historie wird nicht gespeichert");
                return;
            }

            HistoryRequestDTO history = new HistoryRequestDTO(
                    session.getPlayerId(),
                    GameStats.getScore(),
                    GameStats.getLevel(),
                    GameStats.getLinesScored(),
                    GameStats.getDifficulty(),
                    LocalDateTime.now().toString()
            );

            System.out.println("PlayerId: " + history.getPlayerId());
            System.out.println("Score: " + history.getScore());
            System.out.println("Level: " + history.getLevel());
            System.out.println("Lines: " + history.getRowsCleared());
            System.out.println("Difficulty: " + history.getDifficulty());
            System.out.println("Played at: " + history.getPlayedAt());

            historyService.saveHistory(history, session.getJwt());


        }

    }

}
