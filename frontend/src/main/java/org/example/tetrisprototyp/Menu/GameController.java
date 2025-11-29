package org.example.tetrisprototyp.Menu;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import org.example.tetrisprototyp.GameEngine.GameEngine;
import org.example.tetrisprototyp.GameEngine.GameInputHandler;

public class GameController {

    @FXML private Canvas gameCanvas;
    @FXML private Label scoreLabel;
    @FXML private Label levelLabel;
    @FXML private Label linesLabel;

    private GameEngine engine;


    // Diese Methode wird in der loadView()-Methode aufgerufen, nachdem die View gesetzt wurde
    public void startGameNow() {
        // Erstellt die GameEngine
        engine = new GameEngine(gameCanvas);
        // Erstellt den inputHandler für die Verarbeitung der Nutzereingaben
        GameInputHandler inputHandler = new GameInputHandler(engine);
        inputHandler.registerEvents(gameCanvas.getScene());

        engine.startGameLoop();
    }

    // Wird von GameEngine aufgerufen, wenn sich was ändert
    public void updateScore(int score, int level, int lines) {
        scoreLabel.setText(String.format("%06d", score));
        levelLabel.setText(String.valueOf(level));
        linesLabel.setText(String.valueOf(lines));
    }

    @FXML
    private void exitToMenu(ActionEvent event) {
        ControllerUtils.loadView(event, "MainMenuView.fxml", "TETRIS - Hauptmenu");
    }
}