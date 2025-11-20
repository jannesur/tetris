package org.example.tetrisprototyp;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameInputHandler {

    private final GameEngine engine;

    public GameInputHandler(GameEngine engine) {
        this.engine = engine;
    }


    public void registerEvents(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.LEFT) {
                engine.moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                engine.moveRight();
            } else if (event.getCode() == KeyCode.UP) {
                engine.rotateTetromino("clockwise");
            } else if (event.getCode() == KeyCode.DOWN) {
                engine.rotateTetromino("counterclockwise");
            }
        });

    }


}
