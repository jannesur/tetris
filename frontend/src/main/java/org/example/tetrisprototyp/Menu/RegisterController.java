package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.example.tetrisprototyp.TetrisApplication;

import java.io.IOException;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField passwordConfirmField;
    @FXML private Label messageLabel;

    @FXML
    private void performRegister(ActionEvent event) {
        String username = usernameField.getText().trim();
        String pass1 = passwordField.getText();
        String pass2 = passwordConfirmField.getText();

        // Validierung
        if (username.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            showMessage("Bitte alle Felder ausfüllen!", "#ff3b30");
            return;
        }

        if (!pass1.equals(pass2)) {
            showMessage("Die Passwörter stimmen nicht überein!", "#ff3b30");
            return;
        }

        if (username.length() < 3 || pass1.length() < 4) {
            showMessage("Benutzername ≥ 3 Zeichen\nPasswort ≥ 4 Zeichen", "#ff3b30");
            return;
        }

        // Für die Uni-Abgabe: Nur simulierte Registrierung
        // Später kannst du hier z. B. in eine JSON-Datei oder DB schreiben
        showMessage("Registrierung erfolgreich!\nDu kannst dich jetzt einloggen.", "#00ff88");

        // Optional: nach 2 Sekunden automatisch zum Login weiterleiten
        // (kannst du auskommentieren, wenn du nicht willst)
        /*
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> loadView(event, "Login.fxml", "TETRIS - Login"));
        pause.play();
        */
    }

    @FXML
    private void goBack(ActionEvent event) {
        //loadView(event, "WelcomeView.fxml", "TETRIS - Willkommen");
        ControllerUtils.loadView(event, "WelcomeView.fxml", "TETRIS - Willkommen");
    }

    private void showMessage(String text, String color) {
        messageLabel.setText(text);
        messageLabel.setStyle("-fx-text-fill: " + color + ";");
        messageLabel.setVisible(true);
    }

}