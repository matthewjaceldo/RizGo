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
import javafx.scene.image.ImageView;
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

    @FXML
    private ImageView campusMapImageView;


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
    // SELECTED LOCATION & RATIOS
    // =========================================================

    private String selectedBuilding = "";
    private String selectedCategory = "";
    private String selectedFloor = "";
    private String selectedStatus = "";

    private double targetRatioX = -1;
    private double targetRatioY = -1;
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
    // LOCATE BUILDING (Using precise percentage ratios 0.0 to 1.0)
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

                    0.505,
                    0.655,

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

                    0.531,
                    0.389,

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

                    0.802,
                    0.664,

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

                    0.776,
                    0.434,

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

                    0.391,
                    0.655,

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

                    0.479,
                    0.150,

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

                    0.802,
                    0.752,

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

                    0.406,
                    0.221,

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

                    0.167,
                    0.628,

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
                    0.628,

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

                    0.292,
                    0.823,

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

                    0.156,
                    0.168,

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
                    0.434,

                    openZoom
            );

            return;
        }


        // =====================================================
        // R.H.
        // =====================================================

        if (search.contains("r.h")
                || search.matches(".*\\brh\\b.*")) {

            selectBuilding(
                    "R.H. Building",
                    "Academic Building",
                    "3 Floors",
                    "Open",

                    0.906,
                    0.434,

                    openZoom
            );

            return;
        }


        // =====================================================
        // ROMULO / NEW BUILDING
        // =====================================================

        if (search.contains("romulo") || search.contains("new building")) {

            selectBuilding(
                    "Romulo Building",
                    "Academic Building",
                    "4 Floors",
                    "Open",

                    0.750,
                    0.080,

                    openZoom
            );

            return;
        }


        // =====================================================
        // CHAPEL
        // =====================================================

        if (search.contains("chapel")) {

            selectBuilding(
                    "RHS Chapel",
                    "Religious Facility",
                    "1 Floor",
                    "Open",

                    0.604,
                    0.212,

                    openZoom
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
    // SELECT BUILDING WITH RATIOS
    // =========================================================

    private void selectBuilding(
            String name,
            String category,
            String floor,
            String status,
            double ratioX,
            double ratioY,
            boolean openZoom) {

        selectedBuilding = name;
        selectedCategory = category;
        selectedFloor = floor;
        selectedStatus = status;

        nameLabel.setText(name);
        categoryLabel.setText(category);
        floorLabel.setText(floor);
        statusLabel.setText(status);

        targetRatioX = ratioX;
        targetRatioY = ratioY;

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

        targetRatioX = -1;
        targetRatioY = -1;
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


        if (targetRatioX < 0
                || targetRatioY < 0) {

            pinpointMessage.setText(
                    "Exact map position is not configured for this building yet."
            );

            return;
        }


        openZoomedMap();
    }


    // =========================================================
    // OPEN ZOOMED MAP (Dynamic Layout Bounds Mapping)
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


        if (targetRatioX >= 0
                && targetRatioY >= 0
                && campusMapImageView != null) {

            // Dynamically evaluate actual rendered bounds on screen
            double currentImgWidth = campusMapImageView.getBoundsInLocal().getWidth();
            double currentImgHeight = campusMapImageView.getBoundsInLocal().getHeight();

            selectedPinX = targetRatioX * currentImgWidth;
            selectedPinY = targetRatioY * currentImgHeight;

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
             * Move the scroll viewport toward
             * the selected location.
             */

            Platform.runLater(() -> {

                zoomMapScroll.setHvalue(
                        clamp(targetRatioX)
                );

                zoomMapScroll.setVvalue(
                        clamp(targetRatioY)
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