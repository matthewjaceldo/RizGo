package com.rizgo.controllers;

import com.rizgo.utils.PageTransition;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ServicesController {

    @FXML
    private TextField searchField;

    @FXML
    private VBox serviceList;


    // =========================================================
    // CUSTOM POPUP
    // =========================================================

    @FXML
    private StackPane servicePopupOverlay;

    @FXML
    private VBox servicePopupCard;

    @FXML
    private Label servicePopupTitle;

    @FXML
    private Label servicePopupMessage;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        if (servicePopupOverlay != null) {

            servicePopupOverlay.setVisible(false);
            servicePopupOverlay.setManaged(false);
        }
    }


    // =========================================================
    // SEARCH
    // =========================================================

    @FXML
    private void searchServices() {

        if (searchField == null || serviceList == null) {
            return;
        }


        String search =
                searchField.getText()
                        .trim()
                        .toLowerCase();


        for (Node node :
                serviceList.getChildren()) {


            if (node instanceof Button button) {

                StringBuilder serviceText =
                        new StringBuilder();


                if (button.getGraphic()
                        instanceof HBox row) {


                    for (Node child :
                            row.getChildren()) {


                        if (child instanceof VBox textBox) {


                            for (Node textNode :
                                    textBox.getChildren()) {


                                if (textNode instanceof Label label) {

                                    serviceText
                                            .append(
                                                    label.getText()
                                                            .toLowerCase()
                                            )
                                            .append(" ");
                                }
                            }
                        }
                    }
                }


                boolean match =
                        search.isEmpty()
                                || serviceText
                                .toString()
                                .contains(search);


                button.setVisible(match);

                button.setManaged(match);
            }
        }
    }


    // =========================================================
    // ENROLLMENT
    // =========================================================

    @FXML
    private void openEnrollment() {

        showServicePopup(
                "Enrollment",
                "Provides information related to student registration "
                        + "and enrollment procedures."
        );
    }


    // =========================================================
    // STUDENT RECORDS
    // =========================================================

    @FXML
    private void openStudentRecords() {

        showServicePopup(
                "Student Records",
                "Provides assistance for academic records "
                        + "and student document requests."
        );
    }


    // =========================================================
    // GUIDANCE SERVICES
    // =========================================================

    @FXML
    private void openGuidanceServices() {

        showServicePopup(
                "Guidance Services",
                "Provides counseling, guidance, and "
                        + "student support services."
        );
    }


    // =========================================================
    // MEDICAL SERVICES
    // =========================================================

    @FXML
    private void openMedicalServices() {

        showServicePopup(
                "Medical Services",
                "Provides basic health assistance "
                        + "and school medical support."
        );
    }


    // =========================================================
    // LIBRARY SERVICES
    // =========================================================

    @FXML
    private void openLibraryServices() {

        showServicePopup(
                "Library Services",
                "Provides access to available learning resources, "
                        + "reading materials, and study support."
        );
    }


    // =========================================================
    // IT SERVICES
    // =========================================================

    @FXML
    private void openITServices() {

        showServicePopup(
                "IT Services",
                "Provides assistance with school technology, "
                        + "computer facilities, and basic ICT support."
        );
    }


    // =========================================================
    // SHOW CUSTOM POPUP
    // =========================================================

    private void showServicePopup(
            String title,
            String information) {


        servicePopupTitle.setText(
                title
        );


        servicePopupMessage.setText(
                information
        );


        servicePopupOverlay.setManaged(
                true
        );

        servicePopupOverlay.setVisible(
                true
        );

        servicePopupOverlay.toFront();


        // =====================================================
        // FADE
        // =====================================================

        servicePopupOverlay.setOpacity(
                0
        );


        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(180),
                        servicePopupOverlay
                );


        fade.setFromValue(
                0
        );

        fade.setToValue(
                1
        );


        // =====================================================
        // SCALE
        // =====================================================

        servicePopupCard.setScaleX(
                0.92
        );

        servicePopupCard.setScaleY(
                0.92
        );


        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(180),
                        servicePopupCard
                );


        scale.setFromX(
                0.92
        );

        scale.setFromY(
                0.92
        );


        scale.setToX(
                1
        );

        scale.setToY(
                1
        );


        fade.play();

        scale.play();
    }


    // =========================================================
    // CLOSE POPUP
    // =========================================================

    @FXML
    private void closeServicePopup() {

        if (servicePopupOverlay == null) {
            return;
        }


        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(140),
                        servicePopupOverlay
                );


        fade.setFromValue(
                1
        );

        fade.setToValue(
                0
        );


        fade.setOnFinished(
                event -> {

                    servicePopupOverlay.setVisible(
                            false
                    );

                    servicePopupOverlay.setManaged(
                            false
                    );

                    servicePopupOverlay.setOpacity(
                            1
                    );
                }
        );


        fade.play();
    }


    // =========================================================
    // BACK
    // =========================================================

    @FXML
    private void goBack(
            ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // HOME
    // =========================================================

    @FXML
    private void goHome(
            ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // MAP
    // =========================================================

    @FXML
    private void openMap(
            ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Map.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // CHAT
    // =========================================================

    @FXML
    private void openChat(
            ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Chatbot.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // PROFILE
    // =========================================================

    @FXML
    private void openProfile(
            ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Profile.fxml",
                (Node) event.getSource()
        );
    }
}