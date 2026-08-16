package com.rizgo.controllers;

import com.rizgo.utils.PageTransition;
import utils.MapRequest;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.layout.VBox;

public class MapController {

    // =========================================================
    // FXML
    // =========================================================

    @FXML
    private TextField searchField;

    @FXML
    private Label nameLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label floorLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label pinpointMessage;


    // =========================================================
    // ZOOM MAP
    // =========================================================

    @FXML
    private StackPane zoomMapOverlay;

    @FXML
    private VBox zoomMapCard;

    @FXML
    private ScrollPane zoomMapScroll;

    @FXML
    private Label zoomPin;

    @FXML
    private Label zoomBuildingName;


    // =========================================================
    // SELECTED LOCATION
    // =========================================================

    private String selectedBuilding = "";
    private String selectedCategory = "";
    private String selectedFloor = "";
    private String selectedStatus = "";

    /*
     * The zoom map canvas is:
     *
     * WIDTH  = 760
     * HEIGHT = 652
     *
     * Coordinates below are based on your campus_map.png.png.
     */

    private double selectedPinX = -1;
    private double selectedPinY = -1;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        if (zoomMapOverlay != null) {

            zoomMapOverlay.setVisible(false);
            zoomMapOverlay.setManaged(false);
        }

        if (zoomPin != null) {

            zoomPin.setVisible(false);
            zoomPin.setManaged(false);
        }


        /*
         * If the Chatbot sent a building request,
         * automatically select it and open the enlarged map.
         */

        Platform.runLater(() -> {

            if (MapRequest.hasRequest()) {

                String request =
                        MapRequest.getRequestedLocation();

                locateBuilding(
                        request,
                        true
                );

                MapRequest.clear();
            }
        });
    }


    // =========================================================
    // SEARCH
    // =========================================================

    @FXML
    private void searchBuilding() {

        String search =
                searchField.getText()
                        .trim();

        if (search.isEmpty()) {
            return;
        }

        locateBuilding(
                search,
                true
        );
    }


    // =========================================================
    // LOCATE BUILDING
    // =========================================================

    private void locateBuilding(
            String searchText,
            boolean openZoom) {

        String search =
                searchText
                        .trim()
                        .toLowerCase();


        // =====================================================
        // ADMIN
        // =====================================================

        if (search.contains("admin")) {

            selectBuilding(
                    "Admin Office",
                    "Administration",
                    "6 Floor",
                    "Open",

                    0.516,
                    0.723,

                    openZoom
            );

            return;
        }


        // =====================================================
        // ALUMNI
        // =====================================================

        if (search.contains("alumni")) {

            selectBuilding(
                    "Alumni Building",
                    "Club / Museum Facility",
                    "3 Floors",
                    "Open",

                    0.617,
                    0.435,

                    openZoom
            );

            return;
        }


        // =====================================================
        // AMANG
        // =====================================================

        if (search.contains("amang")
                || search.contains("rodriguez")) {

            selectBuilding(
                    "Amang Building",
                    "Academic Building",
                    "3 Floors",
                    "Open",

                    0.815,
                    0.682,

                    openZoom
            );

            return;
        }


        // =====================================================
        // GYM
        // =====================================================

        if (search.contains("caruncho")
                || search.contains("gymnasium")
                || search.equals("gym")
                || search.contains(" gym")) {

            selectBuilding(
                    "Caruncho Gym",
                    "Sports Facility",
                    "1 Floor",
                    "Closed",

                    0.786,
                    0.422,

                    openZoom
            );

            return;
        }


        // =====================================================
        // EUSEBIO / EBEC
        // =====================================================

        if (search.contains("eusebio")
                || search.contains("ebec")) {

            selectBuilding(
                    "Eusebio Building (EBEC)",
                    "Academic Building",
                    "4 Floors",
                    "Open",

                    0.393,
                    0.743,

                    openZoom
            );

            return;
        }


        // =====================================================
        // I.R.
        // =====================================================

        if (search.contains("i.r")
                || search.contains("ir building")
                || search.contains("isidro")) {

            selectBuilding(
                    "I.R. Building",
                    "Academic Building",
                    "3 Floors",
                    "Open",

                    0.375,
                    0.131,

                    openZoom
            );

            return;
        }


        // =====================================================
        // JOVITO SALONGA
        // =====================================================

        if (search.contains("jovito")
                || search.contains("salonga")) {

            selectBuilding(
                    "Jovito Salonga Building",
                    "Academic Building",
                    "4 Floors",
                    "Open",

                    0.876,
                    0.756,

                    openZoom
            );

            return;
        }


        // =====================================================
        // M.A.E.
        // =====================================================

        if (search.contains("m.a.e")
                || search.contains("mae")
                || search.contains("maribel")) {

            selectBuilding(
                    "M.A.E. Building",
                    "Senior High School Building",
                    "6 Floors",
                    "Open",

                    0.378,
                    0.206,

                    openZoom
            );

            return;
        }


        // =====================================================
        // NEPTALI
        // =====================================================

        if (search.contains("neptali")) {

            selectBuilding(
                    "Neptali Building",
                    "Academic Building",
                    "4 Floors",
                    "Open",

                    0.125,
                    0.649,

                    openZoom
            );

            return;
        }


        // =====================================================
        // R.J.
        // =====================================================

        if (search.contains("r.j")
                || search.matches(".*\\brj\\b.*")) {

            selectBuilding(
                    "R.J. Building",
                    "Academic Building",
                    "3 Floors",
                    "Open",

                    0.286,
                    0.649,

                    openZoom
            );

            return;
        }


        // =====================================================
        // SCIENCE
        // =====================================================

        if (search.contains("science")
                || search.contains("sce")) {

            selectBuilding(
                    "Science & SCE Building",
                    "Campus Building",
                    "6 Floors",
                    "Open",

                    0.264,
                    0.834,

                    openZoom
            );

            return;
        }


        // =====================================================
        // TANGHALANG RIZAL
        // =====================================================

        if (search.contains("tanghalang")) {

            selectBuilding(
                    "Tanghalang Rizal",
                    "Events Facility",
                    "2 Floor",
                    "Open",

                    0.067,
                    0.149,

                    openZoom
            );

            return;
        }


        // =====================================================
        // OVAL
        // =====================================================

        if (search.contains("oval")) {

            selectBuilding(
                    "RHS Oval",
                    "Outdoor Facility",
                    "Open Field",
                    "Open",

                    0.307,
                    0.441,

                    openZoom
            );

            return;
        }


        // =====================================================
        // R.H.
        //
        // Exact position is not clearly labelled in the
        // current campus map image.
        // =====================================================

        if (search.contains("r.h")
                || search.matches(".*\\brh\\b.*")) {

            selectBuildingWithoutPin(
                    "R.H. Building",
                    "Academic Building",
                    "3 Floors",
                    "Open"
            );

            return;
        }


        // =====================================================
        // ROMULO
        // =====================================================

        if (search.contains("romulo")) {

            selectBuildingWithoutPin(
                    "Romulo Building",
                    "Academic Building",
                    "4 Floors",
                    "Open"
            );

            return;
        }


        // =====================================================
        // CHAPEL
        // =====================================================

        if (search.contains("chapel")) {

            selectBuildingWithoutPin(
                    "RHS Chapel",
                    "Religious Facility",
                    "1 Floor",
                    "Open"
            );

            return;
        }


        // =====================================================
        // NOT FOUND
        // =====================================================

        selectedBuilding = "";

        nameLabel.setText(
                "Location Not Found"
        );

        categoryLabel.setText("-");
        floorLabel.setText("-");
        statusLabel.setText("-");

        pinpointMessage.setText(
                "Try searching for another campus building."
        );
    }


    // =========================================================
    // SELECT BUILDING WITH PIN
    // =========================================================

    private void selectBuilding(
            String name,
            String category,
            String floor,
            String status,
            double normalizedX,
            double normalizedY,
            boolean openZoom) {

        selectedBuilding = name;
        selectedCategory = category;
        selectedFloor = floor;
        selectedStatus = status;


        nameLabel.setText(name);
        categoryLabel.setText(category);
        floorLabel.setText(floor);
        statusLabel.setText(status);


        /*
         * Convert normalized campus-map position
         * to our 760 x 652 zoom-map canvas.
         */

        selectedPinX =
                normalizedX * 760;

        selectedPinY =
                normalizedY * 652;


        pinpointMessage.setText(
                "Location ready: " + name
        );


        if (openZoom) {

            openZoomedMap();
        }
    }


    // =========================================================
    // SELECT BUILDING WITHOUT PIN
    // =========================================================

    private void selectBuildingWithoutPin(
            String name,
            String category,
            String floor,
            String status) {

        selectedBuilding = name;
        selectedCategory = category;
        selectedFloor = floor;
        selectedStatus = status;

        selectedPinX = -1;
        selectedPinY = -1;


        nameLabel.setText(name);
        categoryLabel.setText(category);
        floorLabel.setText(floor);
        statusLabel.setText(status);


        pinpointMessage.setText(
                "Building selected. Exact map position is not configured yet."
        );
    }


    // =========================================================
    // PINPOINT BUTTON
    // =========================================================

    @FXML
    private void pinpointSelectedBuilding() {

        if (selectedBuilding == null
                || selectedBuilding.isBlank()) {

            pinpointMessage.setText(
                    "Select a building first."
            );

            return;
        }


        if (selectedPinX < 0
                || selectedPinY < 0) {

            pinpointMessage.setText(
                    "Exact map position is not configured for this building yet."
            );

            return;
        }


        openZoomedMap();
    }


    // =========================================================
    // OPEN ZOOMED MAP
    // =========================================================

    @FXML
    private void openZoomedMap() {

        if (selectedBuilding == null
                || selectedBuilding.isBlank()) {

            pinpointMessage.setText(
                    "Select a building first."
            );

            return;
        }


        zoomMapOverlay.setManaged(true);
        zoomMapOverlay.setVisible(true);
        zoomMapOverlay.toFront();


        zoomBuildingName.setText(
                selectedBuilding
        );


        if (selectedPinX >= 0
                && selectedPinY >= 0) {

            zoomPin.setLayoutX(
                    selectedPinX - 15
            );

            zoomPin.setLayoutY(
                    selectedPinY - 34
            );

            zoomPin.setVisible(true);
            zoomPin.setManaged(true);

            zoomPin.toFront();


            // =================================================
            // PIN PULSE
            // =================================================

            ScaleTransition pulse =
                    new ScaleTransition(
                            Duration.millis(500),
                            zoomPin
                    );

            pulse.setFromX(0.85);
            pulse.setFromY(0.85);

            pulse.setToX(1.25);
            pulse.setToY(1.25);

            pulse.setAutoReverse(true);
            pulse.setCycleCount(8);

            pulse.play();


            /*
             * Move the scroll viewport roughly toward
             * the selected location.
             */

            Platform.runLater(() -> {

                zoomMapScroll.setHvalue(
                        clamp(
                                selectedPinX / 760.0
                        )
                );

                zoomMapScroll.setVvalue(
                        clamp(
                                selectedPinY / 652.0
                        )
                );
            });

        } else {

            zoomPin.setVisible(false);
            zoomPin.setManaged(false);
        }


        // =====================================================
        // POPUP ANIMATION
        // =====================================================

        zoomMapOverlay.setOpacity(0);

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(180),
                        zoomMapOverlay
                );

        fade.setFromValue(0);
        fade.setToValue(1);


        zoomMapCard.setScaleX(0.96);
        zoomMapCard.setScaleY(0.96);

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(180),
                        zoomMapCard
                );

        scale.setToX(1);
        scale.setToY(1);


        fade.play();
        scale.play();
    }


    private double clamp(
            double value) {

        return Math.max(
                0,
                Math.min(
                        1,
                        value
                )
        );
    }


    // =========================================================
    // CLOSE ZOOMED MAP
    // =========================================================

    @FXML
    private void closeZoomedMap() {

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(140),
                        zoomMapOverlay
                );

        fade.setFromValue(1);
        fade.setToValue(0);


        fade.setOnFinished(event -> {

            zoomMapOverlay.setVisible(false);
            zoomMapOverlay.setManaged(false);

            zoomMapOverlay.setOpacity(1);
        });


        fade.play();
    }


    // =========================================================
    // BUILDING BUTTONS
    // =========================================================

    @FXML
    private void openAdmin() {

        locateBuilding(
                "Admin Office",
                false
        );
    }


    @FXML
    private void openAlumni() {

        locateBuilding(
                "Alumni Building",
                false
        );
    }


    @FXML
    private void openAmang() {

        locateBuilding(
                "Amang Building",
                false
        );
    }


    @FXML
    private void openCarunchoGym() {

        locateBuilding(
                "Caruncho Gym",
                false
        );
    }


    @FXML
    private void openEusebio() {

        locateBuilding(
                "Eusebio Building (EBEC)",
                false
        );
    }


    @FXML
    private void openIR() {

        locateBuilding(
                "I.R. Building",
                false
        );
    }


    @FXML
    private void openJovitoSalonga() {

        locateBuilding(
                "Jovito Salonga Building",
                false
        );
    }


    @FXML
    private void openMAE() {

        locateBuilding(
                "M.A.E. Building",
                false
        );
    }


    @FXML
    private void openNeptali() {

        locateBuilding(
                "Neptali Building",
                false
        );
    }


    @FXML
    private void openRH() {

        locateBuilding(
                "R.H. Building",
                false
        );
    }


    @FXML
    private void openRJ() {

        locateBuilding(
                "R.J. Building",
                false
        );
    }


    @FXML
    private void openRHSChapel() {

        locateBuilding(
                "RHS Chapel",
                false
        );
    }


    @FXML
    private void openRHSOval() {

        locateBuilding(
                "RHS Oval",
                false
        );
    }


    @FXML
    private void openRomulo() {

        locateBuilding(
                "Romulo Building",
                false
        );
    }


    @FXML
    private void openScience() {

        locateBuilding(
                "Science Building",
                false
        );
    }


    @FXML
    private void openTanghalangRizal() {

        locateBuilding(
                "Tanghalang Rizal",
                false
        );
    }


    // =========================================================
    // VIEW BUILDING DETAILS
    // =========================================================

    @FXML
    private void viewBuildingDetails(
            ActionEvent event) {

        if (selectedBuilding == null
                || selectedBuilding.isBlank()) {

            pinpointMessage.setText(
                    "Select a building first."
            );

            return;
        }


        BuildingController.selectedBuilding =
                selectedBuilding;


        PageTransition.slideTo(
                "/fxml/Building.fxml",
                (Node) event.getSource()
        );
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