package org.example.tetrisprototyp.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class WelcomeController {

    @FXML
    private void openLogin(ActionEvent event) {
        ControllerUtils.loadView(event, "LoginView.fxml", "TETRIS - Login");
    }

    @FXML
    private void openRegister(ActionEvent event) {
        ControllerUtils.loadView(event, "RegisterView.fxml", "TETRIS - Register");
    }




}