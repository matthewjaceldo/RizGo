package com.rizgo.controllers;

import com.rizgo.utils.PageTransition;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DashboardController {

    // =========================================================
    // FXML COMPONENTS
    // =========================================================

    @FXML
    private VBox sideMenu;

    @FXML
    private Pane menuOverlay;

    @FXML
    private TextField homeSearchField;


    // =========================================================
    // OPEN SIDE MENU
    // =========================================================

    @FXML
    private void openMenu() {

        if (sideMenu == null || menuOverlay == null) {
            return;
        }

        menuOverlay.setManaged(true);
        menuOverlay.setVisible(true);

        sideMenu.setManaged(true);
        sideMenu.setVisible(true);

        menuOverlay.toFront();
        sideMenu.toFront();

        sideMenu.setTranslateX(-275);

        TranslateTransition transition =
                new TranslateTransition(
                        Duration.millis(220),
                        sideMenu
                );

        transition.setFromX(-275);
        transition.setToX(0);

        transition.play();
    }


    // =========================================================
    // CLOSE SIDE MENU
    // =========================================================

    @FXML
    private void closeMenu() {

        if (sideMenu == null || menuOverlay == null) {
            return;
        }

        if (!sideMenu.isVisible()) {
            return;
        }

        TranslateTransition transition =
                new TranslateTransition(
                        Duration.millis(180),
                        sideMenu
                );

        transition.setFromX(0);
        transition.setToX(-275);

        transition.setOnFinished(event -> {

            sideMenu.setVisible(false);
            sideMenu.setManaged(false);

            menuOverlay.setVisible(false);
            menuOverlay.setManaged(false);

            sideMenu.setTranslateX(0);
        });

        transition.play();
    }


    // =========================================================
    // HOME
    // =========================================================

    @FXML
    private void openHome() {

        /*
         * We are already on Dashboard,
         * so just close the side menu.
         */

        closeMenu();
    }


    // =========================================================
    // CAMPUS MAP
    // =========================================================

    @FXML
    private void openMap(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Map.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // CHATBOT
    // =========================================================

    @FXML
    private void openChat(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Chatbot.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // OFFICES
    // =========================================================

    @FXML
    private void openOffices(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Offices.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // FACILITIES
    // =========================================================

    @FXML
    private void openFacilities(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Facilities.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // SERVICES
    // =========================================================

    @FXML
    private void openServices(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Services.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // DIRECTORY
    // =========================================================

    @FXML
    private void openDirectory(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Directory.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // ANNOUNCEMENTS
    // =========================================================

    @FXML
    private void openAnnouncements(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Announcements.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // EVENTS
    // =========================================================

    @FXML
    private void openEvents(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Events.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // EMERGENCY
    // =========================================================

    @FXML
    private void openEmergency(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Emergency.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // ABOUT RIZGO
    // =========================================================

    @FXML
    private void openAbout(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/About.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // SETTINGS
    // =========================================================

    @FXML
    private void openSettings(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Settings.fxml",
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


    // =========================================================
    // HOME SEARCH
    // =========================================================

    @FXML
    private void searchHome(ActionEvent event) {

        if (homeSearchField == null) {
            return;
        }

        String originalSearch =
                homeSearchField
                        .getText()
                        .trim();

        String search =
                originalSearch
                        .toLowerCase();


        // =====================================================
        // EMPTY SEARCH
        // =====================================================

        if (search.isEmpty()) {

            showSearchMessage(
                    "Please enter something to search."
            );

            return;
        }


        // =====================================================
        // CHATBOT
        // =====================================================

        if (search.contains("chatbot")
                || search.equals("chat")
                || search.contains("assistant")) {

            PageTransition.slideTo(
                    "/fxml/Chatbot.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // OFFICES
        // =====================================================

        if (search.contains("registrar")
                || search.contains("guidance office")
                || search.contains("principal")
                || search.contains("accounting")
                || search.contains("cashier")
                || search.contains("admin office")
                || search.contains("clinic")
                || search.equals("office")
                || search.equals("offices")) {

            PageTransition.slideTo(
                    "/fxml/Offices.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // FACILITIES
        // =====================================================

        if (search.contains("library")
                || search.contains("gym")
                || search.contains("gymnasium")
                || search.contains("caruncho")
                || search.contains("canteen")
                || search.contains("science laboratory")
                || search.contains("science lab")
                || search.contains("computer laboratory")
                || search.contains("computer lab")
                || search.contains("covered court")
                || search.equals("facility")
                || search.equals("facilities")) {

            PageTransition.slideTo(
                    "/fxml/Facilities.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // SERVICES
        // =====================================================

        if (search.contains("enrollment")
                || search.contains("student record")
                || search.contains("records")
                || search.contains("medical service")
                || search.contains("guidance service")
                || search.contains("library service")
                || search.contains("it service")
                || search.equals("service")
                || search.equals("services")) {

            PageTransition.slideTo(
                    "/fxml/Services.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // CAMPUS MAP / BUILDINGS
        // =====================================================

        if (search.contains("campus map")
                || search.equals("map")
                || search.contains("building")

                || search.contains("admin building")

                || search.contains("alumni")

                || search.contains("amang")

                || search.contains("eusebio")
                || search.contains("ebec")

                || search.contains("jovito")
                || search.contains("salonga")

                || search.contains("m.a.e")
                || search.equals("mae")

                || search.contains("neptali")

                || search.contains("r.h")
                || search.equals("rh")

                || search.contains("r.j")
                || search.equals("rj")

                || search.contains("chapel")

                || search.contains("oval")

                || search.contains("romulo")

                || search.contains("science & sce")
                || search.contains("science and sce")
                || search.contains("sce building")

                || search.contains("tanghalang")) {

            PageTransition.slideTo(
                    "/fxml/Map.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // DIRECTORY
        // =====================================================

        if (search.contains("directory")) {

            PageTransition.slideTo(
                    "/fxml/Directory.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // ANNOUNCEMENTS
        // =====================================================

        if (search.contains("announcement")
                || search.contains("notice")
                || search.contains("update")) {

            PageTransition.slideTo(
                    "/fxml/Announcements.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // EVENTS
        // =====================================================

        if (search.contains("event")
                || search.contains("intramural")
                || search.contains("activity")
                || search.contains("program")) {

            PageTransition.slideTo(
                    "/fxml/Events.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // EMERGENCY
        // =====================================================

        if (search.contains("emergency")) {

            PageTransition.slideTo(
                    "/fxml/Emergency.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // PROFILE
        // =====================================================

        if (search.contains("profile")) {

            PageTransition.slideTo(
                    "/fxml/Profile.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // SETTINGS
        // =====================================================

        if (search.contains("setting")) {

            PageTransition.slideTo(
                    "/fxml/Settings.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // ABOUT
        // =====================================================

        if (search.contains("about rizgo")
                || search.equals("about")) {

            PageTransition.slideTo(
                    "/fxml/About.fxml",
                    (Node) event.getSource()
            );

            return;
        }


        // =====================================================
        // NO RESULT
        // =====================================================

        showSearchMessage(
                "No result found for \""
                        + originalSearch
                        + "\".\n\n"
                        + "Try searching for a building, office, "
                        + "facility, service, event, announcement, "
                        + "or Chatbot."
        );
    }


    // =========================================================
    // SEARCH ALERT
    // =========================================================

    private void showSearchMessage(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "RizGo"
        );

        alert.setHeaderText(
                "Search"
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}