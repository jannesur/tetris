package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class HistoryController {


    @FXML
    private void backToMenu(ActionEvent event) {
        System.out.println("Zurück zum Hauptmenü...");
        ControllerUtils.loadView(event, "MainMenuView.fxml", "Tetris - Hauptmenu");
    }
}
