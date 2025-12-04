package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class HistoryController {


    @FXML
    private ListView<String> historyList;

    @FXML
    public void initialize() {
        loadDummyHistory();
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
