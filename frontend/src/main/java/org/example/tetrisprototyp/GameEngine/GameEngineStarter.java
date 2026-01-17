package org.example.tetrisprototyp.GameEngine;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;

public class GameEngineStarter {

    private GameEngine engine;

    public void startGame(ActionEvent event, Canvas canvas, int difficulty) {
        engine = new GameEngine(canvas, difficulty);

        engine.renderBoard();
        engine.startGameLoop();

        GameInputHandler inputHandler = new GameInputHandler(engine);
        inputHandler.registerEvents(canvas.getScene());
    }
}
