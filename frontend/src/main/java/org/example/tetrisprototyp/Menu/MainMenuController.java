package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.tetrisprototyp.GameEngine.GameEngineStarter;


public class MainMenuController {

    // Die drei Buttons aus der FXML (fx:id müssen exakt übereinstimmen!)
    @FXML private Button startButton;
    @FXML private Button historyButton;
    @FXML private Button logoutButton;
    @FXML private Button exitButton;

    // ================================================================
    // 1. Spiel starten
    // ================================================================
    @FXML
    private void startGame(ActionEvent event) {
        System.out.println("Spiel starten!");  // Hier später zur Spielszene wechseln
        // Beispiel: Deaktiviere Buttons, während das Spiel lädt
        //startButton.setDisable(true);
        //exitButton.setDisable(true);

        //GameEngineStarter gameEngineStarter = new GameEngineStarter();
        //gameEngineStarter.startGame(event);
        //ControllerUtils.loadView(event, "GameView.fxml", "TETRIS - Historie");
        ControllerUtils.loadView(event, "DifficultyView.fxml", "Tetris");
    }

    // ================================================================
    // 2. Historie ansehen
    // ================================================================
    @FXML
    private void showHistory(ActionEvent event) {
        System.out.println("Historie wird angezeigt...");
        ControllerUtils.loadView(event, "HistoryView.fxml", "TETRIS - Historie");
    }

    // ================================================================
    // 3. Beenden → Programm komplett schließen
    // ================================================================
    @FXML
    private void logout(ActionEvent event) {
        System.out.println("Nutzer wird ausgeloggt");

        // Hier Logik für Logout

        ControllerUtils.loadView(event, "WelcomeView.fxml", "Tetris");

        // Falls du mehrere Stages hast oder sicher gehen willst:
        // Platform.exit();
        // System.exit(0);
    }

    @FXML
    private void exitApplication() {
        System.exit(0);
    }

    // ================================================================
    // Hilfsmethoden
    // ================================================================


    /** Alle Menü-Buttons temporär deaktivieren (z. B. während Spiel lädt) */
    private void setButtonsDisabled(boolean disabled) {
        startButton.setDisable(disabled);
        historyButton.setDisable(disabled);
        logoutButton.setDisable(disabled);
        exitButton.setDisable(disabled);
    }

    // Falls du nach dem Spiel zurückkommst und Buttons wieder aktivieren willst:
    public void enableButtons() {
        setButtonsDisabled(false);
    }
}