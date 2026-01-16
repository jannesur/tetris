package org.example.tetrisprototyp.MenuController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void performLogin(ActionEvent event) {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            showError("Bitte Benutzername und Passwort eingeben");
            return;
        }

        if ("123".equals(user) && "123".equals(pass)) {
            errorLabel.setVisible(false);
            ControllerUtils.loadView(event, "MainMenuView.fxml", "TETRIS - MainMenu");
        } else {
            showError("Falscher Benutzername oder Passwort!\n(Tipp: Beides ist 123)");
        }

         // --------------------------------------------------
        // 🔐 ECHTER LOGIN ÜBER BACKEND (VORBEREITET)
        // --------------------------------------------------
        /*
        try {
            AuthService authService = new AuthService();

            // JWT vom Server holen
            String jwt = authService.login(user, pass);

            // Session setzen
            UserSession.getInstance().login(user, jwt);

            errorLabel.setVisible(false);

            // Weiter ins Hauptmenü
            ControllerUtils.loadView(
                    event,
                    "MainMenuView.fxml",
                    "TETRIS - MainMenu"
            );

        } catch (Exception e) {
            showError("Falscher Benutzername oder Passwort");
        }
        */

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