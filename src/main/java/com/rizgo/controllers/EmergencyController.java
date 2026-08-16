package com.rizgo.controllers;

import com.rizgo.utils.PageTransition;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class EmergencyController {

    // =========================================================
    // CUSTOM POPUP
    // =========================================================

    @FXML
    private StackPane emergencyPopupOverlay;

    @FXML
    private VBox emergencyPopupCard;

    @FXML
    private Label emergencyPopupTitle;

    @FXML
    private Label emergencyPopupMessage;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        if (emergencyPopupOverlay != null) {

            emergencyPopupOverlay.setVisible(false);
            emergencyPopupOverlay.setManaged(false);
        }
    }


    // =========================================================
    // SCHOOL CLINIC
    // =========================================================

    @FXML
    private void openClinic() {

        showEmergencyPopup(
                "School Clinic",
                "The School Clinic provides basic health and medical assistance "
                        + "for students and staff."
        );
    }


    // =========================================================
    // GUIDANCE
    // =========================================================

    @FXML
    private void openGuidance() {

        showEmergencyPopup(
                "Guidance Office",
                "The Guidance Office provides student support, counseling, "
                        + "and assistance for urgent student concerns."
        );
    }


    // =========================================================
    // ADMIN OFFICE
    // =========================================================

    @FXML
    private void openAdmin() {

        showEmergencyPopup(
                "Admin Office",
                "The Admin Office can assist with administrative "
                        + "and campus-related concerns."
        );
    }


    // =========================================================
    // SHOW POPUP
    // =========================================================

    private void showEmergencyPopup(
            String title,
            String message) {

        emergencyPopupTitle.setText(title);
        emergencyPopupMessage.setText(message);

        emergencyPopupOverlay.setManaged(true);
        emergencyPopupOverlay.setVisible(true);
        emergencyPopupOverlay.toFront();

        emergencyPopupOverlay.setOpacity(0);

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(180),
                        emergencyPopupOverlay
                );

        fade.setFromValue(0);
        fade.setToValue(1);

        emergencyPopupCard.setScaleX(0.92);
        emergencyPopupCard.setScaleY(0.92);

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(180),
                        emergencyPopupCard
                );

        scale.setFromX(0.92);
        scale.setFromY(0.92);

        scale.setToX(1);
        scale.setToY(1);

        fade.play();
        scale.play();
    }


    // =========================================================
    // CLOSE POPUP
    // =========================================================

    @FXML
    private void closeEmergencyPopup() {

        if (emergencyPopupOverlay == null) {
            return;
        }

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(140),
                        emergencyPopupOverlay
                );

        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(event -> {

            emergencyPopupOverlay.setVisible(false);
            emergencyPopupOverlay.setManaged(false);
            emergencyPopupOverlay.setOpacity(1);
        });

        fade.play();
    }


    // =========================================================
    // BACK
    // =========================================================

    @FXML
    private void goBack(ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // HOME
    // =========================================================

    @FXML
    private void goHome(ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // MAP
    // =========================================================

    @FXML
    private void openMap(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Map.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // CHAT
    // =========================================================

    @FXML
    private void openChat(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Chatbot.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // PROFILE
    // =========================================================

    @FXML
    private void openProfile(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Profile.fxml",
                (Node) event.getSource()
        );
    }
}