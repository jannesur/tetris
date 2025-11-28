package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.example.tetrisprototyp.TetrisApplication;

import java.io.IOException;

public class HistoryController {

    // Der Zurück-Button aus der FXML
    @FXML
    private void backToMenu(ActionEvent event) {
        System.out.println("Zurück zum Hauptmenü...");

        // Stage aus dem Event holen
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            // FXML laden – mit explizitem Parent-Cast (funktioniert immer)
            Parent root = FXMLLoader.load(
                    TetrisApplication.class.getResource("MainMenuView.fxml")
            );

            // Scene ersetzen
            stage.getScene().setRoot(root);

            // Titel und Größe anpassen
            stage.setTitle("TETRIS - Hauptmenü");
            stage.sizeToScene();           // passt Fenster an neue Scene an
            stage.centerOnScreen();        // optional: zentriert das Fenster neu

        } catch (IOException e) {
            e.printStackTrace();
            // Bei schwerem Fehler: Programm beenden oder Fehlermeldung zeigen
            System.err.println("Konnte StartMenu.fxml nicht laden!");
            stage.close();
        }
    }
}
