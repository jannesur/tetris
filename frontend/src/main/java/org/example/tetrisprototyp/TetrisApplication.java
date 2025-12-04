package org.example.tetrisprototyp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class TetrisApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Lädt den Startbildschirm
        FXMLLoader fxmlLoader = new FXMLLoader(TetrisApplication.class.getResource("WelcomeView.fxml"));
        // Erstellt eine Scene, in der die UI-Elemente der fxml-Datei gepackt wreden
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Tetris");
        // Die fxml-Datei wird ins Hauptfenster gesetzt
        stage.setScene(scene);
        // Lädt die CSS-Datei, welche Hovereffekte für alle Buttons erstellt
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }
}
