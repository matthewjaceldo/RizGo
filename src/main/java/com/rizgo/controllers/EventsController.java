package com.rizgo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.rizgo.utils.PageTransition;
import javafx.scene.Node;

public class EventsController {

    @FXML
    private TextField searchField;

    @FXML
    private VBox eventList;


    // =========================================================
    // SEARCH
    // =========================================================

    @FXML
    private void searchEvents() {

        String search =
                searchField.getText()
                        .trim()
                        .toLowerCase();

        for (Node node : eventList.getChildren()) {

            if (node instanceof VBox card) {

                StringBuilder text =
                        new StringBuilder();

                collectText(
                        card,
                        text
                );

                boolean match =
                        search.isEmpty()
                                || text.toString()
                                .toLowerCase()
                                .contains(search);

                card.setVisible(match);
                card.setManaged(match);
            }
        }
    }


    // =========================================================
    // COLLECT CARD TEXT
    // =========================================================

    private void collectText(
            javafx.scene.Parent parent,
            StringBuilder builder) {

        for (Node child :
                parent.getChildrenUnmodifiable()) {

            if (child instanceof Label label) {

                builder.append(
                        label.getText()
                ).append(" ");

            } else if (child instanceof javafx.scene.Parent nestedParent) {

                collectText(
                        nestedParent,
                        builder
                );
            }
        }
    }


    // =========================================================
    // NAVIGATION
    // =========================================================



    @FXML
    private void goHome(ActionEvent event) {
        goTo("/fxml/Dashboard.fxml", event);
    }


    @FXML
    private void openMap(ActionEvent event) {
        goTo("/fxml/Map.fxml", event);
    }


    @FXML
    private void openChat(ActionEvent event) {
        goTo("/fxml/Chatbot.fxml", event);
    }

    @FXML
    private void goBack(ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }

    @FXML
    private void openProfile(ActionEvent event) {
        goTo("/fxml/Profile.fxml", event);
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