package com.stockcontrol.controller;

import java.util.Map;

import com.stockcontrol.modelo.User;
import com.stockcontrol.modelo.userDataBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    private boolean found = false;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userField;

    @FXML
    void handleCancelAction(ActionEvent event) {
        ControllerTransitions.loadView("/xml/login.fxml", "Login", event);
    }

    @FXML
    void handleRegistroAction(ActionEvent event) {
        found = false;
        for (Map.Entry<String, User> entry : userDataBase.getUsers().entrySet()) {
            if(entry.getKey().equals(userField.getText())) found = true;
        }
        if(!found) {
            userDataBase.addUser(userField.getText(), passwordField.getText());
            ControllerTransitions.loadView("/xml/login.fxml", "Login", event);
        } else {
            ControllerTransitions.showErrorAlert("Error", "El usuario ya existe");
        }
    }

}

