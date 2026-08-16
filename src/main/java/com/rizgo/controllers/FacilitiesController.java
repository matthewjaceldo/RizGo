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

public class FacilitiesController {

    @FXML
    private TextField searchField;

    @FXML
    private VBox facilityList;

    // CUSTOM POPUP

    @FXML
    private StackPane facilityPopupOverlay;

    @FXML
    private VBox facilityPopupCard;

    @FXML
    private Label facilityPopupTitle;

    @FXML
    private Label facilityPopupMessage;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        if (facilityPopupOverlay != null) {
            facilityPopupOverlay.setVisible(false);
            facilityPopupOverlay.setManaged(false);
        }
    }


    // =========================================================
    // SEARCH
    // =========================================================

    @FXML
    private void searchFacilities() {

        if (searchField == null || facilityList == null) {
            return;
        }

        String search =
                searchField.getText()
                        .trim()
                        .toLowerCase();

        for (Node node : facilityList.getChildren()) {

            if (node instanceof Button button) {

                StringBuilder facilityName =
                        new StringBuilder();

                if (button.getGraphic() instanceof HBox row) {

                    for (Node child : row.getChildren()) {

                        if (child instanceof VBox textBox) {

                            for (Node textNode :
                                    textBox.getChildren()) {

                                if (textNode instanceof Label label) {

                                    facilityName
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
                                || facilityName
                                .toString()
                                .contains(search);

                button.setVisible(match);
                button.setManaged(match);
            }
        }
    }


    // =========================================================
    // FACILITY DETAILS
    // =========================================================

    @FXML
    private void openLibrary() {

        showFacilityPopup(
                "Library",
                "A learning facility where students may read, study, "
                        + "and access available academic resources."
        );
    }


    @FXML
    private void openGymnasium() {

        showFacilityPopup(
                "Gymnasium",
                "A sports and activity facility used for physical education, "
                        + "athletic activities, and school programs."
        );
    }


    @FXML
    private void openCoveredCourt() {

        showFacilityPopup(
                "Covered Court",
                "A covered activity area used for sports, gatherings, "
                        + "and school activities."
        );
    }


    @FXML
    private void openCanteen() {

        showFacilityPopup(
                "Canteen",
                "A school food facility where students and staff may purchase "
                        + "meals and refreshments."
        );
    }


    @FXML
    private void openScienceLab() {

        showFacilityPopup(
                "Science Laboratory",
                "An academic laboratory used for science experiments "
                        + "and practical learning activities."
        );
    }


    @FXML
    private void openComputerLab() {

        showFacilityPopup(
                "Computer Laboratory",
                "A technology facility equipped for computer-based learning "
                        + "and ICT activities."
        );
    }


    // =========================================================
    // SHOW CUSTOM POPUP
    // =========================================================

    private void showFacilityPopup(
            String title,
            String information) {

        facilityPopupTitle.setText(title);
        facilityPopupMessage.setText(information);

        facilityPopupOverlay.setManaged(true);
        facilityPopupOverlay.setVisible(true);
        facilityPopupOverlay.toFront();

        facilityPopupOverlay.setOpacity(0);

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(180),
                        facilityPopupOverlay
                );

        fade.setFromValue(0);
        fade.setToValue(1);

        facilityPopupCard.setScaleX(0.92);
        facilityPopupCard.setScaleY(0.92);

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(180),
                        facilityPopupCard
                );

        scale.setFromX(0.92);
        scale.setFromY(0.92);

        scale.setToX(1);
        scale.setToY(1);

        fade.play();
        scale.play();
    }


    // =========================================================
    // CLOSE CUSTOM POPUP
    // =========================================================

    @FXML
    private void closeFacilityPopup() {

        if (facilityPopupOverlay == null) {
            return;
        }

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(140),
                        facilityPopupOverlay
                );

        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(event -> {

            facilityPopupOverlay.setVisible(false);
            facilityPopupOverlay.setManaged(false);

            facilityPopupOverlay.setOpacity(1);
        });

        fade.play();
    }


    // =========================================================
    // NAVIGATION
    // =========================================================

    @FXML
    private void goHome(ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }


    @FXML
    private void openMap(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Map.fxml",
                (Node) event.getSource()
        );
    }


    @FXML
    private void openChat(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Chatbot.fxml",
                (Node) event.getSource()
        );
    }


    @FXML
    private void openProfile(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Profile.fxml",
                (Node) event.getSource()
        );
    }


    @FXML
    private void goBack(ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }
}