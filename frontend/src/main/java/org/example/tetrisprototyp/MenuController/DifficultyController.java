package org.example.tetrisprototyp.MenuController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.canvas.Canvas;
import org.example.tetrisprototyp.GameEngine.Settings;

public class DifficultyController {

    @FXML private Slider difficultySlider;
    @FXML private Label difficultyLabel;

    // Canvas wird vom GameController oder Loader gesetzt
    private Canvas gameCanvas;

    @FXML
    public void initialize() {
        // Slider auf ganze Zahlen festlegen: 1, 2, 3
        difficultySlider.setMin(1);
        difficultySlider.setMax(3);
        difficultySlider.setValue(1);
        difficultySlider.setMajorTickUnit(1);
        difficultySlider.setBlockIncrement(1);
        difficultySlider.setMinorTickCount(0);
        difficultySlider.setSnapToTicks(true);

        // Listener, um die Anzeige zu aktualisieren
        difficultySlider.valueProperty().addListener((obs, oldVal, newVal) -> updateDifficultyLabel(newVal.intValue()));
        updateDifficultyLabel((int) difficultySlider.getValue());
    }

    private void updateDifficultyLabel(int value) {
        switch (value) {
            case 1 -> difficultyLabel.setText("Leicht");
            case 2 -> difficultyLabel.setText("Mittel");
            case 3 -> difficultyLabel.setText("Schwer");
        }
    }

    @FXML
    private void startGameWithDifficulty(ActionEvent event) {
        int level = (int) difficultySlider.getValue();

        // In Settings speichern
        Settings.setDifficulty(level);

        // Scene auf die GameView wechseln
        ControllerUtils.loadView(event, "GameView.fxml", "TETRIS - Spiel");
    }

    @FXML
    private void backToMenu(ActionEvent event) {
        ControllerUtils.loadView(event, "MainMenuView.fxml", "TETRIS - Hauptmenu");
    }
}
