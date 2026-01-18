package org.example.tetrisprototyp.MenuController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.tetrisprototyp.UserManagement.AuthService;
import org.example.tetrisprototyp.UserManagement.LoginResponseDTO;
import org.example.tetrisprototyp.UserManagement.UserSession;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @FXML
    private void performLogin(ActionEvent event) {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            showError("Bitte Benutzername und Passwort eingeben");
            return;
        }

        AuthService authService = new AuthService();

        authService.loginAsync(user, pass)
                .thenAccept(jwt -> Platform.runLater(() -> {
                    UserSession.getInstance().login(user, jwt);
                    errorLabel.setVisible(false);
                    ControllerUtils.loadView(event, "MainMenuView.fxml", "TETRIS - MainMenu");
                }))
                .exceptionally(ex -> {
                    Platform.runLater(() -> showError("Falscher Benutzername oder Passwort"));
                    return null;
                });
    }

    @FXML
    private void goBack(ActionEvent event) {
        ControllerUtils.loadView(event, "WelcomeView.fxml", "TETRIS - Wilkommen");
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}