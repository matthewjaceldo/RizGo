package com.rizgo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.rizgo.utils.PageTransition;
import javafx.scene.Node;

public class AnnouncementsController {

    @FXML
    private VBox announcementList;

    @FXML
    private ToggleButton allFilter;

    @FXML
    private ToggleButton eventsFilter;

    @FXML
    private ToggleButton noticesFilter;

    @FXML
    private ToggleButton updatesFilter;

    private String activeFilter = "all";


    // =========================================================
    // FILTERS
    // =========================================================

    @FXML
    private void goBack(ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }

    @FXML
    private void filterAll() {

        activeFilter = "all";

        updateFilterButtons();
        updateAnnouncements();
    }


    @FXML
    private void filterEvents() {

        activeFilter = "event";

        updateFilterButtons();
        updateAnnouncements();
    }


    @FXML
    private void filterNotices() {

        activeFilter = "notice";

        updateFilterButtons();
        updateAnnouncements();
    }


    @FXML
    private void filterUpdates() {

        activeFilter = "update";

        updateFilterButtons();
        updateAnnouncements();
    }


    // =========================================================
    // UPDATE FILTER BUTTONS
    // =========================================================

    private void updateFilterButtons() {

        updateButton(
                allFilter,
                activeFilter.equals("all")
        );

        updateButton(
                eventsFilter,
                activeFilter.equals("event")
        );

        updateButton(
                noticesFilter,
                activeFilter.equals("notice")
        );

        updateButton(
                updatesFilter,
                activeFilter.equals("update")
        );
    }


    private void updateButton(
            ToggleButton button,
            boolean active) {

        button.getStyleClass().removeAll(
                "announcementFilter",
                "announcementFilterActive"
        );

        if (active) {

            button.getStyleClass().add(
                    "announcementFilterActive"
            );

        } else {

            button.getStyleClass().add(
                    "announcementFilter"
            );
        }

        button.setSelected(active);
    }


    // =========================================================
    // UPDATE ANNOUNCEMENTS
    // =========================================================

    private void updateAnnouncements() {

        for (Node node :
                announcementList.getChildren()) {

            String category =
                    String.valueOf(
                            node.getUserData()
                    );

            boolean visible =
                    activeFilter.equals("all")
                            || activeFilter.equals(category);

            node.setVisible(visible);
            node.setManaged(visible);
        }
    }


    // =========================================================
    // NAVIGATION
    // =========================================================


    @FXML
    private void goHome(ActionEvent event) {

        goTo(
                "/fxml/Dashboard.fxml",
                event
        );
    }


    @FXML
    private void openMap(ActionEvent event) {

        goTo(
                "/fxml/Map.fxml",
                event
        );
    }


    @FXML
    private void openChat(ActionEvent event) {

        goTo(
                "/fxml/Chatbot.fxml",
                event
        );
    }


    @FXML
    private void openProfile(ActionEvent event) {

        goTo(
                "/fxml/Profile.fxml",
                event
        );
    }


    // =========================================================
    // PAGE NAVIGATION
    // =========================================================

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