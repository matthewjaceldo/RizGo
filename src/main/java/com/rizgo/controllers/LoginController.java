package com.rizgo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField visiblePasswordField;

    @FXML
    private CheckBox showPasswordCheckBox;


    @FXML
    private void initialize() {

        visiblePasswordField
                .textProperty()
                .bindBidirectional(
                        passwordField.textProperty()
                );
    }


    @FXML
    private void togglePassword() {

        boolean show =
                showPasswordCheckBox.isSelected();

        visiblePasswordField.setVisible(show);
        visiblePasswordField.setManaged(show);

        passwordField.setVisible(!show);
        passwordField.setManaged(!show);
    }


    @FXML
    private void handleLogin(ActionEvent event) {

        // TEMPORARY / your existing login logic here

        goTo(
                "/fxml/Dashboard.fxml",
                event
        );
    }


    @FXML
    private void continueAsGuest(ActionEvent event) {

        goTo(
                "/fxml/Dashboard.fxml",
                event
        );
    }


    @FXML
    private void openSignup(ActionEvent event) {

        goTo(
                "/fxml/Signup.fxml",
                event
        );
    }


    private void goTo(
            String fxmlFile,
            ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    fxmlFile
                            )
                    );

            Parent root =
                    loader.load();

            Stage stage =
                    (Stage) ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            Scene currentScene =
                    stage.getScene();

            currentScene.setRoot(root);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}