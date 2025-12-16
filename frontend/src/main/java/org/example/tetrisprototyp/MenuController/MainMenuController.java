package org.example.tetrisprototyp.MenuController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.tetrisprototyp.UserManagement.UserSession;


public class MainMenuController {

    // Die drei Buttons aus der FXML (fx:id müssen exakt übereinstimmen!)
    @FXML private Button startButton;
    @FXML private Button historyButton;
    @FXML private Button logoutButton;
    @FXML private Button exitButton;

    //Wechsel zur Auswahl des Schwierigkeitsgrades
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

    // Wechsel zur HistoryView
    @FXML
    private void showHistory(ActionEvent event) {
        System.out.println("Historie wird angezeigt...");
        ControllerUtils.loadView(event, "HistoryView.fxml", "TETRIS - Historie");
    }

    // Logout und Zurückkehren zum StartMenü
    @FXML
    private void logout(ActionEvent event) {
        System.out.println("Nutzer wird ausgeloggt");

        // Wegen JWT muss das Logout nur Frontendseitig gemacht werden
        // JWT und User aus der Session entfernen
        UserSession.getInstance().logout();

        ControllerUtils.loadView(event, "WelcomeView.fxml", "Tetris");

        // Falls du mehrere Stages hast oder sicher gehen willst:
        // Platform.exit();
        // System.exit(0);
    }

    @FXML
    private void exitApplication() {
        System.exit(0);
    }




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