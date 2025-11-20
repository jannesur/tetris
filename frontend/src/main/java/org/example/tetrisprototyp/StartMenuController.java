package org.example.tetrisprototyp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartMenuController {

    @FXML
    private Button startButton;  // Optional: Wenn du den Button manipulieren möchtest

    @FXML
    private Button exitButton;

    public void startGame(ActionEvent event) {
        System.out.println("Spiel starten!");  // Hier später zur Spielszene wechseln
        // Beispiel: Deaktiviere Buttons, während das Spiel lädt
        //startButton.setDisable(true);
        //exitButton.setDisable(true);

        GameEngineStarter gameEngineStarter = new GameEngineStarter();
        gameEngineStarter.startGame(event);

    }

    public void exitGame(ActionEvent event) {
        System.out.println("App beenden.");
        Platform.exit();  // Schließt die JavaFX-Anwendung sauber
    }
}