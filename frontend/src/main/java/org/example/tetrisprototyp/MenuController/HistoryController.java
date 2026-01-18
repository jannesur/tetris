package org.example.tetrisprototyp.MenuController;

import javafx.application.Platform;
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
    @FXML private Label statsLabel;
    @FXML
    private final HistoryService historyService = new HistoryService();

    @FXML
    public void initialize() {
        String jwt = UserSession.getInstance().getJwt();

        statsLabel.setText("Historie wird geladen...");
        historyList.getItems().clear();

        historyService.loadHistoryAsync(jwt)
                .thenAccept(history -> Platform.runLater(() -> {
                    historyList.getItems().clear();
                    for (GameHistoryDTO dto : history) {
                        historyList.getItems().add(formatHistory(dto));
                    }
                    statsLabel.setText(buildStats(history));
                }))
                .exceptionally(ex -> {
                    Platform.runLater(() -> statsLabel.setText("Historie konnte nicht geladen werden"));
                    return null;
                });
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
            return "Spiele: 0 | Bestscore: 0 | Best-Level: 0";
        }
        int games = history.size();
        int bestScore = history.stream().mapToInt(GameHistoryDTO::getScore).max().orElse(0);
        int bestLevel = history.stream().mapToInt(GameHistoryDTO::getLevel).max().orElse(0);
        int totalLines = history.stream().mapToInt(GameHistoryDTO::getRowsCleared).sum();

        return "Spiele: " + games
                + " | Bestscore: " + bestScore
                + " | Reihen gesamt: " + totalLines
                + " | Best-Level: " + bestLevel;
    }

    @FXML
    private void backToMenu(ActionEvent event) {
        ControllerUtils.loadView(event, "MainMenuView.fxml", "Tetris - Hauptmenu");
    }
}