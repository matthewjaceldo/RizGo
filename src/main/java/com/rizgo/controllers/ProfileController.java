package com.rizgo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.rizgo.utils.PageTransition;
import javafx.scene.Node;

public class ProfileController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;


    @FXML
    private void initialize() {

        // Temporary information for the UI.
        // We will connect this to the database later.

        nameField.setText("RizGo User");
        emailField.setText("user@example.com");
        usernameField.setText("rizgo_user");
    }


    // =========================
    // EDIT PROFILE
    // =========================

    @FXML
    private void editProfile() {

        nameField.setEditable(true);
        emailField.setEditable(true);
        usernameField.setEditable(true);

        nameField.requestFocus();
    }

    // =========================
    // LOGOUT
    // =========================

    @FXML
    private void logout(ActionEvent event) {

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle("Logout");
        alert.setHeaderText("Logout from RizGo?");
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL)
                == ButtonType.OK) {

            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/Login.fxml")
                );

                Scene scene = new Scene(loader.load());

                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {

                e.printStackTrace();

            }
        }
    }


    // =========================
    // BACK
    // =========================
    @FXML
    private void goBack(ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }

    // =========================
    // HOME
    // =========================

    @FXML
    private void goHome(ActionEvent event) {

        goTo("/fxml/Dashboard.fxml", event);
    }


    // =========================
    // MAP
    // =========================

    @FXML
    private void openMap(ActionEvent event) {

        goTo("/fxml/Map.fxml", event);
    }


    // =========================
    // CHATBOT
    // =========================

    @FXML
    private void openChat(ActionEvent event) {

        goTo("/fxml/Chatbot.fxml", event);
    }


    // =========================
    // PAGE NAVIGATION
    // =========================

    private void goTo(String fxmlFile, ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlFile)
            );

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}