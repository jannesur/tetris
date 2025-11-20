package org.example.tetrisprototyp;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


// Wird in StartMenuController aufgerufen, wenn "Spiel starten" gedrückt wurde. Erstellt ein GameEngine Objekt, welches
// die GameLoop und somit das Spiel startet.
public class GameEngineStarter {

    private final GameEngine engine;

    public GameEngineStarter() {
        this.engine = new GameEngine();
    }

    public void startGame(ActionEvent event) {

        engine.renderBoard();
        engine.startGameLoop();

        StackPane gameRoot = new StackPane(engine.getCanvas());
        Scene gameScene = new Scene(gameRoot);

        // InputHandler aktivieren
        GameInputHandler inputHandler = new GameInputHandler(engine);
        inputHandler.registerEvents(gameScene);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(gameScene);
        stage.setTitle("Tetris Prototype");
        stage.show();

    }


}
