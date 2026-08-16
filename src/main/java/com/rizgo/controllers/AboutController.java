package com.rizgo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AboutController {

    // =========================================================
    // BACK
    // =========================================================

    @FXML
    private void goBack(ActionEvent event) {
        goTo("/fxml/Dashboard.fxml", event);
    }


    // =========================================================
    // HOME
    // =========================================================

    @FXML
    private void goHome(ActionEvent event) {
        goTo("/fxml/Dashboard.fxml", event);
    }


    // =========================================================
    // MAP
    // =========================================================

    @FXML
    private void openMap(ActionEvent event) {
        goTo("/fxml/Map.fxml", event);
    }


    // =========================================================
    // CHAT
    // =========================================================

    @FXML
    private void openChat(ActionEvent event) {
        goTo("/fxml/Chatbot.fxml", event);
    }


    // =========================================================
    // PROFILE
    // =========================================================

    @FXML
    private void openProfile(ActionEvent event) {
        goTo("/fxml/Profile.fxml", event);
    }


    // =========================================================
    // NAVIGATION
    // =========================================================

    private void goTo(
            String fxmlFile,
            ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(fxmlFile)
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