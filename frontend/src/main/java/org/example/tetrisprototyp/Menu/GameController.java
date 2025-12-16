package org.example.tetrisprototyp.Menu;


import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.tetrisprototyp.GameEngine.GameEngine;
import org.example.tetrisprototyp.GameEngine.GameInputHandler;
import org.example.tetrisprototyp.GameEngine.Observer;
import org.example.tetrisprototyp.GameEngine.Settings;

public class GameController implements Observer {

    @FXML private Canvas gameCanvas;
    @FXML private Label scoreLabel;
    @FXML private Label levelLabel;
    @FXML private Label linesLabel;

    private int score;
    private int level = 1;
    private int linesScored;

    private GameEngine engine;

    private Stage currentStage;


    // Diese Methode wird in der loadView()-Methode aufgerufen, nachdem die View gesetzt wurde
    public void startGameNow() {
        // Speichert die Stage, damit diese bei Game Over in der update-Methode für das Laden
        // des Hauptmenus verwendet werden kann
        currentStage = (Stage) gameCanvas.getScene().getWindow();
        // Erstellt die GameEngine
        engine = new GameEngine(gameCanvas, Settings.getDifficulty());
        // Erstellt den inputHandler für die Verarbeitung der Nutzereingaben
        GameInputHandler inputHandler = new GameInputHandler(engine);
        inputHandler.registerEvents(gameCanvas.getScene());

        // GameController registriert sich selber als Observer, damit die Punktzahl in der View geändert werden kann
        engine.addObserver(this);

        engine.startGameLoop();
    }


    @FXML
    private void exitToMenu(ActionEvent event) {
        engine.stopGameLoop();
        ControllerUtils.loadView(event, "MainMenuView.fxml", "TETRIS - Hauptmenu");
    }


    @Override
    public void update(String event) {

        if (event.equals("scored")) {

            System.out.println("Punkte erzielt!");

            score += 100;

            if (Settings.getDifficulty() == 3){
                linesScored++;
                if (linesScored % 3 == 0) {
                    level++;
                }
            } else {
                linesScored++;
                if (linesScored % 5 == 0) {
                    level++;
                }
            }

            scoreLabel.setText(String.format("%06d", score));
            levelLabel.setText(String.valueOf(level));
            linesLabel.setText(String.valueOf(linesScored));
        }

        if (event.equals("gameOver")) {
            // 1 Sekunde Delay, bevor ins Hauptmenu gewechselt wird
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                ControllerUtils.loadView(currentStage, "MainMenuView.fxml", "TETRIS - Hauptmenu");
            });
            pause.play();
        }

    }
}