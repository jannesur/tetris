package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.example.tetrisprototyp.TetrisApplication;

import java.io.IOException;

public abstract class ControllerUtils {

    public static void loadView(ActionEvent event, String fxmlFile, String title) {
        Stage stage = getCurrentStage(event);

        try {
            Parent root = FXMLLoader.load(
                    TetrisApplication.class.getResource(fxmlFile)
            );

            stage.getScene().setRoot(root);
            stage.setTitle(title);
            stage.sizeToScene();
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(fxmlFile + " konnte nicht geladen werden!");
        }
    }

    /** Holt den aktuellen Stage aus einem Event (funktioniert bei allen Nodes) */
    public static Stage getCurrentStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }



}
