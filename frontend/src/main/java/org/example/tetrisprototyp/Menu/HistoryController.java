package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.tetrisprototyp.History.GameHistoryDTO;
import org.example.tetrisprototyp.History.HistoryLoader;


public class HistoryController {


    @FXML
    private ListView<String> historyList;

    private HistoryLoader historyLoader;


    @FXML
    public void initialize() {

        // Logik für abrufen der Historie aus der Datenbank
        /*
        HistoryService historyService = new HistoryService();
        historyLoader = new HistoryLoader(historyService);

        List<GameHistoryDTO> history = historyLoader.loadHistory("test");

        historyList.getItems().clear();
        for (GameHistoryDTO dto : history) {
            historyList.getItems().add(formatHistory(dto));
        }

         */

        loadDummyHistory();
    }



    private String formatHistory(GameHistoryDTO h) {
        return "👤 Spieler: " + h.getUsername() +
                " | Punkte: " + h.getScore() +
                " | Level: " + h.getLevel() +
                " | Reihen: " + h.getRowsCleared() +
                " | Schwierigkeit: " + h.getDifficulty();
    }


    private void loadDummyHistory() {
        historyList.getItems().addAll(
                "👤 Spieler: LangerBenutzername123456789   |  Punkte: 12.300  | Level: 8  | Reihen: 34 | Schwierigkeit: Mittel",
                "👤 Spieler: LangerBenutzername123456789  |  Punkte: 8.950   | Level: 6  | Reihen: 27 | Schwierigkeit: Leicht",
                "👤 Spieler: LangerBenutzername123456789  |  Punkte: 22.410  | Level: 12 | Reihen: 48 | Schwierigkeit: Schwer",
                "👤 Spieler: LangerBenutzername123456789    |  Punkte: 4.200   | Level: 3  | Reihen: 11 | Schwierigkeit: Leicht",
                "👤 Spieler: LangerBenutzername123456789    |  Punkte: 4.200   | Level: 3  | Reihen: 11 | Schwierigkeit: Leicht",
                "👤 Spieler: LangerBenutzername123456789    |  Punkte: 4.200   | Level: 3  | Reihen: 11 | Schwierigkeit: Leicht",
                "👤 Spieler: LangerBenutzername123456789    |  Punkte: 4.200   | Level: 3  | Reihen: 11 | Schwierigkeit: Leicht",
                "👤 Spieler: LangerBenutzername123456789    |  Punkte: 4.200   | Level: 3  | Reihen: 11 | Schwierigkeit: Leicht"
        );
    }


    @FXML
    private void backToMenu(ActionEvent event) {
        System.out.println("Zurück zum Hauptmenü...");
        ControllerUtils.loadView(event, "MainMenuView.fxml", "Tetris - Hauptmenu");
    }
}
