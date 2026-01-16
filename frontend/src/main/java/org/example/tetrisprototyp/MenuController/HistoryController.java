package org.example.tetrisprototyp.MenuController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.tetrisprototyp.History.GameHistoryDTO;
import org.example.tetrisprototyp.History.HistoryLoader;
import org.example.tetrisprototyp.History.HistoryService;
import org.example.tetrisprototyp.UserManagement.UserSession;

import java.util.List;


public class HistoryController {


    @FXML
    private ListView<String> historyList;

    private HistoryLoader historyLoader;


    @FXML
    public void initialize() {
        UserSession session = UserSession.getInstance();
        historyList.getItems().clear();

        if (session.getJwt() == null || session.getPlayerId() == null) {
            historyList.getItems().add("Bitte einloggen, um die Historie zu sehen.");
            return;
        }

        HistoryService historyService = new HistoryService();
        historyLoader = new HistoryLoader(historyService);

        List<GameHistoryDTO> history = historyLoader.loadHistory(session.getPlayerId(), session.getJwt());

        if (history.isEmpty()) {
            historyList.getItems().add("Keine Historie vorhanden.");
            return;
        }

        for (GameHistoryDTO dto : history) {
            historyList.getItems().add(formatHistory(dto));
        }
    }



    private String formatHistory(GameHistoryDTO h) {
        String playedAt = h.getPlayedAt() != null ? h.getPlayedAt() : "unbekannt";
        return "Spieler: " + h.getUsername() +
                " | Punkte: " + h.getScore() +
                " | Level: " + h.getLevel() +
                " | Reihen: " + h.getRowsCleared() +
                " | Schwierigkeit: " + h.getDifficulty() +
                " | Gespielt: " + playedAt;
    }


    @FXML
    private void backToMenu(ActionEvent event) {
        System.out.println("Zurück zum Hauptmenü...");
        ControllerUtils.loadView(event, "MainMenuView.fxml", "Tetris - Hauptmenu");
    }
}
