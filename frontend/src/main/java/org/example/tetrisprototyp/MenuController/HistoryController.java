package org.example.tetrisprototyp.MenuController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.tetrisprototyp.History.GameHistoryDTO;
import org.example.tetrisprototyp.History.HistoryLoader;
import org.example.tetrisprototyp.History.HistoryService;
import org.example.tetrisprototyp.UserManagement.UserSession;
import javafx.scene.control.Label;


import java.util.List;


public class HistoryController {


    @FXML
    private ListView<String> historyList;
    @FXML
    private Label statsLabel;
    private HistoryLoader historyLoader;


    @FXML
    public void initialize() {
        HistoryService historyService = new HistoryService();
        historyLoader = new HistoryLoader(historyService);

        String jwt = UserSession.getInstance().getJwt();
        List<GameHistoryDTO> history = historyLoader.loadHistory(jwt);

        historyList.getItems().clear();
        for (GameHistoryDTO dto : history) {
            historyList.getItems().add(formatHistory(dto));
        }
        statsLabel.setText(buildStats(history));

    }

    private String formatHistory(GameHistoryDTO h) {
        return "👤 Spieler: " + h.getUsername() +
                " | Punkte: " + h.getScore() +
                " | Level: " + h.getLevel() +
                " | Reihen: " + h.getRowsCleared() +
                " | Schwierigkeit: " + h.getDifficulty();
    }


    private String buildStats(List<GameHistoryDTO> history) {
        if (history == null || history.isEmpty()) {
            return "Spiele: 0 | Bestscore: 0 | Durchschnitt: 0 | Best-Level: 0";
        }
        int games = history.size();
        int bestScore = history.stream().mapToInt(GameHistoryDTO::getScore).max().orElse(0);
        int bestLevel = history.stream().mapToInt(GameHistoryDTO::getLevel).max().orElse(0);
        int totalLines = history.stream().mapToInt(GameHistoryDTO::getRowsCleared).sum();
        double avgScore = history.stream().mapToInt(GameHistoryDTO::getScore).average().orElse(0);

        return "Spiele: " + games
                + " | Bestscore: " + bestScore
                + " | Durchschnitt: " + Math.round(avgScore)
                + " | Reihen gesamt: " + totalLines
                + " | Best-Level: " + bestLevel;
    }

    
    @FXML
    private void backToMenu(ActionEvent event) {
        System.out.println("Zurück zum Hauptmenü...");
        ControllerUtils.loadView(event, "MainMenuView.fxml", "Tetris - Hauptmenu");
    }
}
