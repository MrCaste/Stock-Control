package com.stockcontrol.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.stockcontrol.modelo.userDataBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userField;

    @FXML
    void handleLoginAction(ActionEvent event) {
        if(userDataBase.authenticate(userField.getText(), passwordField.getText())) {
            ControllerTransitions.loadView("/xml/Stock.fxml", "Stock control", event);
        } else {
            ControllerTransitions.showErrorAlert("Error", "Usuario o contraseña incorrectos");
        }
    }

    @FXML
    void handleRegisterAction(ActionEvent event) {
        ControllerTransitions.loadView("/xml/registro.fxml", "Registro usuario", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /****Inicializa la cuenta de administrador ****/
        userDataBase.addUser("admin", "Admin");
    }

}
