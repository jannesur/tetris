package org.example.tetrisprototyp.MenuController;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.example.tetrisprototyp.UserManagement.AuthService;
import org.example.tetrisprototyp.UserManagement.PlayerDTO;

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

        try {
            AuthService authService = new AuthService();

            PlayerDTO player = authService.register(username, pass1);
            showMessage(
                    "Registrierung erfolgreich!\nWillkommen " + player.getUsername(),
                    "#00ff88"
            );

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e ->
                    ControllerUtils.loadView(
                            event,
                            "LoginView.fxml",
                            "TETRIS - Login"
                    )
            );
            pause.play();

        } catch (Exception e) {
            showMessage(
                    "Registrierung fehlgeschlagen (Benutzername evtl. vergeben)",
                    "#ff3b30"
            );
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        ControllerUtils.loadView(event, "WelcomeView.fxml", "TETRIS - Willkommen");
    }

    private void showMessage(String text, String color) {
        messageLabel.setText(text);
        messageLabel.setStyle("-fx-text-fill: " + color + ";");
        messageLabel.setVisible(true);
    }
}