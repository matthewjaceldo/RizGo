package com.rizgo.controllers;

import com.rizgo.utils.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RoleSelectionController {

    // =========================================================
    // GUEST
    // =========================================================

    @FXML
    private void selectGuest(ActionEvent event) {

        UserSession.setSelectedRole("GUEST");

        goTo(
                "/fxml/Dashboard.fxml",
                event
        );
    }


    // =========================================================
    // STUDENT
    // =========================================================

    @FXML
    private void selectStudent(ActionEvent event) {

        UserSession.setSelectedRole("STUDENT");

        goTo(
                "/fxml/Login.fxml",
                event
        );
    }


    // =========================================================
    // TEACHER
    // =========================================================

    @FXML
    private void selectTeacher(ActionEvent event) {

        UserSession.setSelectedRole("TEACHER");

        goTo(
                "/fxml/Login.fxml",
                event
        );
    }


    // =========================================================
    // SCHOOL STAFF
    // =========================================================

    @FXML
    private void selectStaff(ActionEvent event) {

        UserSession.setSelectedRole("SCHOOL STAFF");

        goTo(
                "/fxml/Login.fxml",
                event
        );
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

            System.err.println(
                    "Unable to open " + fxmlFile
            );

            e.printStackTrace();
        }
    }
}