package com.rizgo.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import com.rizgo.utils.PageTransition;
import javafx.scene.Node;

import java.util.Optional;

public class SettingsController {

    // =========================================================
    // FXML COMPONENTS
    // =========================================================

    @FXML
    private CheckBox notificationsCheckBox;

    @FXML
    private ComboBox<String> languageComboBox;


    // =========================================================
    // SAVED SETTINGS
    // =========================================================

    private static boolean notificationsEnabled = true;

    private static String selectedLanguage = "English";


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        // Languages
        languageComboBox.setItems(
                FXCollections.observableArrayList(
                        "English",
                        "Filipino"
                )
        );

        // Restore previously selected settings
        languageComboBox.setValue(selectedLanguage);

        notificationsCheckBox.setSelected(
                notificationsEnabled
        );


        // Listen for notification changes
        notificationsCheckBox
                .selectedProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            notificationsEnabled = newValue;

                            if (newValue) {

                                System.out.println(
                                        "RizGo notifications enabled."
                                );

                            } else {

                                System.out.println(
                                        "RizGo notifications disabled."
                                );
                            }
                        }
                );


        // Listen for language changes
        languageComboBox.setOnAction(event -> {

            String language =
                    languageComboBox.getValue();

            if (language != null) {

                selectedLanguage = language;

                showLanguageMessage(language);
            }
        });
    }


    // =========================================================
    // LANGUAGE
    // =========================================================

    private void showLanguageMessage(
            String language) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("RizGo");

        alert.setHeaderText(
                "Language Updated"
        );

        if (language.equals("Filipino")) {

            alert.setContentText(
                    "Napili ang Filipino bilang wika.\n\n" +
                            "Ang buong pagsasalin ng RizGo interface " +
                            "ay maaaring idagdag sa susunod."
            );

        } else {

            alert.setContentText(
                    "English has been selected as the RizGo language."
            );
        }

        alert.showAndWait();
    }


    // =========================================================
    // APP PERMISSIONS
    // =========================================================

    @FXML
    private void openPermissions() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("RizGo");

        alert.setHeaderText(
                "App Permissions"
        );

        alert.setContentText(
                """
                RizGo currently uses local application features.

                Current functions include:

                • Campus Map access
                • Building information
                • AI Campus Assistant
                • Offices and facilities directory
                • Announcements and events

                RizGo does not currently request camera, microphone, or location permissions.
                """
        );

        alert.showAndWait();
    }


    // =========================================================
    // FEEDBACK FORM
    // =========================================================

    @FXML
    private void openFeedback() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "RizGo Feedback"
        );

        dialog.setHeaderText(
                "Send Feedback"
        );


        ButtonType submitButton =
                new ButtonType(
                        "Submit",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        submitButton,
                        ButtonType.CANCEL
                );


        // Feedback category
        ComboBox<String> categoryBox =
                new ComboBox<>();

        categoryBox.setItems(
                FXCollections.observableArrayList(
                        "Suggestion",
                        "Problem",
                        "Campus Information",
                        "Map",
                        "AI Chatbot",
                        "Other"
                )
        );

        categoryBox.setValue(
                "Suggestion"
        );

        categoryBox.setMaxWidth(
                Double.MAX_VALUE
        );


        // Feedback message
        TextArea feedbackArea =
                new TextArea();

        feedbackArea.setPromptText(
                "Tell us what you think about RizGo..."
        );

        feedbackArea.setWrapText(true);

        feedbackArea.setPrefRowCount(6);


        GridPane grid =
                new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(
                new Label("Category:"),
                0,
                0
        );

        grid.add(
                categoryBox,
                1,
                0
        );

        grid.add(
                new Label("Message:"),
                0,
                1
        );

        grid.add(
                feedbackArea,
                1,
                1
        );

        GridPane.setHgrow(
                categoryBox,
                Priority.ALWAYS
        );

        GridPane.setHgrow(
                feedbackArea,
                Priority.ALWAYS
        );


        dialog.getDialogPane()
                .setContent(grid);


        Optional<ButtonType> result =
                dialog.showAndWait();


        if (result.isPresent()
                && result.get() == submitButton) {

            String feedback =
                    feedbackArea
                            .getText()
                            .trim();

            if (feedback.isEmpty()) {

                showInfo(
                        "Feedback",
                        "Please enter your feedback before submitting."
                );

                return;
            }


            System.out.println(
                    "RizGo Feedback"
            );

            System.out.println(
                    "Category: "
                            + categoryBox.getValue()
            );

            System.out.println(
                    "Message: "
                            + feedback
            );


            Alert success =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            success.setTitle("RizGo");

            success.setHeaderText(
                    "Feedback Submitted"
            );

            success.setContentText(
                    "Thank you for helping improve RizGo."
            );

            success.showAndWait();
        }
    }


    // =========================================================
    // USER GUIDE
    // =========================================================

    @FXML
    private void openUserGuide() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("RizGo");

        alert.setHeaderText(
                "RizGo User Guide"
        );

        alert.setContentText(
                """
                HOME
                View quick access options, announcements, and campus information.

                CAMPUS MAP
                Search for buildings and facilities around Rizal High School.

                AI CHATBOT
                Ask RizGo about buildings, offices, and campus locations.

                OFFICES
                View available school offices and their information.

                FACILITIES
                Find facilities such as the library, laboratories, and sports areas.

                SERVICES
                View available student and school services.

                DIRECTORY
                Search offices, facilities, and services in one place.

                ANNOUNCEMENTS
                Read notices, updates, and school announcements.

                EVENTS
                View upcoming campus activities.

                EMERGENCY
                Access emergency information and important campus assistance.
                """
        );

        alert.getDialogPane()
                .setPrefWidth(450);

        alert.showAndWait();
    }


    // =========================================================
    // ABOUT RIZGO
    // =========================================================

    @FXML
    private void openAbout(
            ActionEvent event) {

        goTo(
                "/fxml/About.fxml",
                event
        );
    }


    // =========================================================
    // PRIVACY POLICY
    // =========================================================

    @FXML
    private void openPrivacy() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("RizGo");

        alert.setHeaderText(
                "Privacy Policy"
        );

        alert.setContentText(
                """
                RizGo is designed to provide campus information and navigation assistance.

                The current version of RizGo does not automatically collect sensitive personal information through the campus map, directory, or chatbot interface.

                User account and privacy handling can be expanded when the login and database systems are finalized.

                Information shown in RizGo should only be used for legitimate school and campus-related purposes.
                """
        );

        alert.getDialogPane()
                .setPrefWidth(430);

        alert.showAndWait();
    }


    // =========================================================
    // TERMS OF USE
    // =========================================================

    @FXML
    private void openTerms() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("RizGo");

        alert.setHeaderText(
                "Terms of Use"
        );

        alert.setContentText(
                """
                By using RizGo, users agree to use the application responsibly.

                RizGo provides campus navigation and school information for educational and informational purposes.

                Users should not intentionally misuse the system, submit harmful information, or interfere with application functionality.

                Campus information may be updated when official school information changes.

                RizGo should not replace instructions provided directly by authorized school personnel.
                """
        );

        alert.getDialogPane()
                .setPrefWidth(430);

        alert.showAndWait();
    }


    // =========================================================
    // INFO DIALOG
    // =========================================================

    private void showInfo(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "RizGo"
        );

        alert.setHeaderText(
                title
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
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
    private void goHome(
            ActionEvent event) {

        goTo(
                "/fxml/Dashboard.fxml",
                event
        );
    }


    // =========================================================
    // MAP
    // =========================================================

    @FXML
    private void openMap(
            ActionEvent event) {

        goTo(
                "/fxml/Map.fxml",
                event
        );
    }


    // =========================================================
    // CHAT
    // =========================================================

    @FXML
    private void openChat(
            ActionEvent event) {

        goTo(
                "/fxml/Chatbot.fxml",
                event
        );
    }


    // =========================================================
    // PROFILE
    // =========================================================

    @FXML
    private void openProfile(
            ActionEvent event) {

        goTo(
                "/fxml/Profile.fxml",
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


            // Keep the existing RizGo window size.
            currentScene.setRoot(root);


        } catch (Exception e) {

            System.err.println(
                    "Unable to open "
                            + fxmlFile
            );

            e.printStackTrace();
        }
    }
}