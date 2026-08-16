package com.rizgo.controllers;

import utils.MapRequest;
import com.rizgo.utils.PageTransition;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ChatbotController {

    @FXML
    private TextField messageField;

    @FXML
    private VBox chatMessages;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        addBotMessage(
                "Hello! I'm RizGo Chatbot. Ask me where a campus building is located."
        );
    }


    // =========================================================
    // SEND MESSAGE
    // =========================================================

    @FXML
    private void sendMessage(ActionEvent event) {

        String original =
                messageField.getText().trim();

        if (original.isEmpty()) {
            return;
        }

        addUserMessage(original);

        messageField.clear();

        String message =
                original.toLowerCase();


        // =====================================================
        // BUILDING DETECTION
        // =====================================================

        String location =
                detectLocation(message);


        if (location != null) {

            addBotMessage(
                    "I found " + location
                            + ". I'll pinpoint it on the Campus Map."
            );


            MapRequest.setRequestedLocation(
                    location
            );


            PauseTransition delay =
                    new PauseTransition(
                            Duration.millis(450)
                    );


            delay.setOnFinished(e -> {

                PageTransition.slideTo(
                        "/fxml/Map.fxml",
                        messageField
                );
            });


            delay.play();

            return;
        }


        // =====================================================
        // GENERAL QUESTIONS
        // =====================================================

        if (message.contains("hello")
                || message.equals("hi")
                || message.contains("hey")) {

            addBotMessage(
                    "Hello! You can ask me where a building or facility is located."
            );

            return;
        }


        if (message.contains("registrar")) {

            addBotMessage(
                    "The Registrar's Office handles student records and enrollment-related concerns."
            );

            return;
        }


        if (message.contains("guidance")) {

            addBotMessage(
                    "The Guidance Office provides counseling and student support."
            );

            return;
        }


        if (message.contains("library")) {

            addBotMessage(
                    "The Library is a campus learning facility. "
                            + "You can also open the Campus Map to help locate it."
            );

            return;
        }


        addBotMessage(
                "I couldn't identify that location yet. "
                        + "Try asking something like "
                        + "\"Where is the Science Building?\""
        );
    }


    // =========================================================
    // LOCATION DETECTION
    // =========================================================

    private String detectLocation(
            String message) {


        if (message.contains("admin")) {

            return "Admin Office";
        }


        if (message.contains("alumni")) {

            return "Alumni Building";
        }


        if (message.contains("amang")
                || message.contains("rodriguez")) {

            return "Amang Building";
        }


        if (message.contains("caruncho")
                || message.contains("gymnasium")
                || message.contains("gym")) {

            return "Caruncho Gym";
        }


        if (message.contains("eusebio")
                || message.contains("ebec")
                || message.contains("rce")) {

            return "Eusebio Building";
        }


        if (message.contains("jovito")
                || message.contains("salonga")) {

            return "Jovito Salonga Building";
        }


        if (message.contains("m.a.e")
                || message.contains("mae")) {

            return "M.A.E. Building";
        }


        if (message.contains("neptali")) {

            return "Neptali Building";
        }


        if (message.contains("r.h")
                || message.matches(".*\\brh\\b.*")) {

            return "R.H. Building";
        }


        if (message.contains("r.j")
                || message.matches(".*\\brj\\b.*")) {

            return "R.J. Building";
        }


        if (message.contains("science")
                || message.contains("sce")) {

            return "Science Building";
        }


        if (message.contains("tanghalang")) {

            return "Tanghalang Rizal";
        }


        if (message.contains("oval")) {

            return "RHS Oval";
        }


        if (message.contains("chapel")) {

            return "RHS Chapel";
        }


        if (message.contains("ir building")
                || message.contains("i.r")
                || message.contains("isidro")) {

            return "I.R. Building";
        }


        if (message.contains("main building")
                || message.contains("computer building")) {

            return "Main / Computer Building";
        }


        return null;
    }


    // =========================================================
    // USER MESSAGE
    // =========================================================

    private void addUserMessage(
            String message) {

        Label label =
                new Label(message);

        label.setWrapText(true);
        label.setMaxWidth(290);

        label.getStyleClass().add(
                "chatUserBubble"
        );


        HBox row =
                new HBox(label);

        row.setMaxWidth(Double.MAX_VALUE);

        row.setAlignment(
                javafx.geometry.Pos.CENTER_RIGHT
        );


        chatMessages
                .getChildren()
                .add(row);
    }


    // =========================================================
    // BOT MESSAGE
    // =========================================================

    private void addBotMessage(
            String message) {

        Label label =
                new Label(message);

        label.setWrapText(true);
        label.setMaxWidth(290);

        label.getStyleClass().add(
                "chatBotBubble"
        );


        HBox row =
                new HBox(label);

        row.setMaxWidth(Double.MAX_VALUE);

        row.setAlignment(
                javafx.geometry.Pos.CENTER_LEFT
        );


        chatMessages
                .getChildren()
                .add(row);
    }


    // =========================================================
    // SUGGESTIONS
    // =========================================================

    @FXML
    private void askScience() {

        messageField.setText(
                "Where is the Science Building?"
        );

        sendMessage(
                new ActionEvent(
                        messageField,
                        messageField
                )
        );
    }


    @FXML
    private void askGym() {

        messageField.setText(
                "Where is the Gymnasium?"
        );

        sendMessage(
                new ActionEvent(
                        messageField,
                        messageField
                )
        );
    }


    @FXML
    private void askAdmin() {

        messageField.setText(
                "Where is the Admin Office?"
        );

        sendMessage(
                new ActionEvent(
                        messageField,
                        messageField
                )
        );
    }


    // =========================================================
    // RESET
    // =========================================================

    @FXML
    private void resetChat() {

        chatMessages.getChildren().clear();

        addBotMessage(
                "Hello! I'm RizGo Chatbot. Ask me where a campus building is located."
        );
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
    // BOTTOM NAVIGATION
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
    private void openProfile(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Profile.fxml",
                (Node) event.getSource()
        );
    }
}