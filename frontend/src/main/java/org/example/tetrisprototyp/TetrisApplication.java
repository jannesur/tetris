package org.example.tetrisprototyp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TetrisApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // NEU MainMenu
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(TetrisApplication.class.getResource("MainMenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Tetris Prototyp");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
         */
        // Neu Welcome
        FXMLLoader fxmlLoader = new FXMLLoader(TetrisApplication.class.getResource("WelcomeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Tetris Prototyp");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }
}
