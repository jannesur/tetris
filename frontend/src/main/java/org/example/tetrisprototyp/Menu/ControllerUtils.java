package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.tetrisprototyp.TetrisApplication;
import java.io.IOException;

// Hilfsmethoden für die Übergänge zwischen den Views
public abstract class ControllerUtils {

    public static void loadView(ActionEvent event, String fxmlFile, String title) {
        // Holt das Fenster (Stage), indem die Szene gerade gezeigt wird
        Stage stage = getCurrentStage(event);

        try {
            // Loader für die fxml-Datei
            FXMLLoader loader = new FXMLLoader(TetrisApplication.class.getResource(fxmlFile));
            // Neues Hauptlayout der geladenen fxml-Datei
            Parent newRoot = loader.load();

            /*
               Da das Spiel erst gestartet werden darf, wenn die View vollständig geladen ist,
               muss zuerst die Scene Root gesetzt werden. Ansonsten würde keine Scene existieren, auf der
               das Spiel gestartet werden kann.
             */
            if (fxmlFile.contains("GameView.fxml")) {
                GameController controller = loader.getController();
                stage.getScene().setRoot(newRoot);
                controller.startGameNow();
            } else {
                // Für alle anderen Views (Menu)
                stage.getScene().setRoot(newRoot);
            }

            //stage.getScene().setRoot(newRoot);
            //stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.sizeToScene();
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(fxmlFile + " konnte nicht geladen werden!");
        }
    }

    /** Holt den aktuellen Stage aus einem Event  */
    public static Stage getCurrentStage(ActionEvent event) {
        // Holt zuerst das UI-Element des Events. Dann die Szene, in der der Button
        // sich befindet. Und dann das Fenster/Stage, in dem sich die Szene befindet.
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }



}
