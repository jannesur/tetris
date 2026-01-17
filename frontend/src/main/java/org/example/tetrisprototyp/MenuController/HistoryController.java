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
        HistoryService historyService = new HistoryService();
        historyLoader = new HistoryLoader(historyService);

        String jwt = UserSession.getInstance().getJwt();
        List<GameHistoryDTO> history = historyLoader.loadHistory(jwt);

        historyList.getItems().clear();
        for (GameHistoryDTO dto : history) {
            historyList.getItems().add(formatHistory(dto));
        }
    }

    private String formatHistory(GameHistoryDTO h) {
        return "👤 Spieler: " + h.getUsername() +
                " | Punkte: " + h.getScore() +
                " | Level: " + h.getLevel() +
                " | Reihen: " + h.getRowsCleared() +
                " | Schwierigkeit: " + h.getDifficulty();
    }

    @FXML
    private void backToMenu(ActionEvent event) {
        System.out.println("Zurück zum Hauptmenü...");
        ControllerUtils.loadView(event, "MainMenuView.fxml", "Tetris - Hauptmenu");
    }
}
