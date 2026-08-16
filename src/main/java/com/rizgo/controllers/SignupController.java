package com.rizgo.controllers;

import database.Database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignupController {

    // =========================================================
    // INPUT FIELDS
    // =========================================================

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField visiblePasswordField;

    @FXML
    private TextField visibleConfirmPasswordField;

    @FXML
    private CheckBox showPasswordCheckBox;


    // =========================================================
    // PASSWORD REQUIREMENT LABELS
    // =========================================================

    @FXML
    private Label lengthRequirement;

    @FXML
    private Label upperRequirement;

    @FXML
    private Label lowerRequirement;

    @FXML
    private Label specialRequirement;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        // =====================================================
        // SYNCHRONIZE PASSWORD FIELDS
        // =====================================================

        visiblePasswordField
                .textProperty()
                .bindBidirectional(
                        passwordField.textProperty()
                );

        visibleConfirmPasswordField
                .textProperty()
                .bindBidirectional(
                        confirmPasswordField.textProperty()
                );


        // =====================================================
        // PASSWORD CHARACTER LIMIT
        // =====================================================

        addCharacterLimit(passwordField);
        addCharacterLimit(visiblePasswordField);

        addCharacterLimit(confirmPasswordField);
        addCharacterLimit(visibleConfirmPasswordField);


        // =====================================================
        // LIVE PASSWORD REQUIREMENTS
        // =====================================================

        passwordField
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                updatePasswordRequirements(newValue)
                );


        // =====================================================
        // ROLE OPTIONS
        // =====================================================

        roleComboBox.getItems().addAll(
                "Student",
                "Teacher",
                "School Staff"
        );


        // =====================================================
        // INITIAL PASSWORD REQUIREMENT STATE
        // =====================================================

        updatePasswordRequirements("");
    }


    // =========================================================
    // CHARACTER LIMIT
    // =========================================================

    private void addCharacterLimit(
            TextInputControl field) {

        field.setTextFormatter(
                new TextFormatter<String>(
                        change -> {

                            if (change
                                    .getControlNewText()
                                    .length() <= 20) {

                                return change;
                            }

                            return null;
                        }
                )
        );
    }


    // =========================================================
    // PASSWORD REQUIREMENTS
    // =========================================================

    private void updatePasswordRequirements(
            String password) {

        boolean validLength =
                password.length() >= 8
                        && password.length() <= 20;

        boolean hasUpper =
                password.matches(
                        ".*[A-Z].*"
                );

        boolean hasLower =
                password.matches(
                        ".*[a-z].*"
                );

        boolean hasSpecial =
                password.matches(
                        ".*[^A-Za-z0-9].*"
                );


        updateRequirement(
                lengthRequirement,
                validLength,
                "8-20 characters"
        );

        updateRequirement(
                upperRequirement,
                hasUpper,
                "Uppercase"
        );

        updateRequirement(
                lowerRequirement,
                hasLower,
                "Lowercase"
        );

        updateRequirement(
                specialRequirement,
                hasSpecial,
                "Special character"
        );
    }


    // =========================================================
    // UPDATE REQUIREMENT LABEL
    // =========================================================

    private void updateRequirement(
            Label label,
            boolean valid,
            String text) {

        label.getStyleClass().removeAll(
                "passwordRequirement",
                "passwordRequirementValid"
        );


        if (valid) {

            label.setText(
                    "✓ " + text
            );

            label.getStyleClass().add(
                    "passwordRequirementValid"
            );

        } else {

            label.setText(
                    "○ " + text
            );

            label.getStyleClass().add(
                    "passwordRequirement"
            );
        }
    }


    // =========================================================
    // SHOW / HIDE PASSWORD
    // =========================================================

    @FXML
    private void togglePassword() {

        boolean show =
                showPasswordCheckBox.isSelected();


        // PASSWORD

        passwordField.setVisible(!show);
        passwordField.setManaged(!show);

        visiblePasswordField.setVisible(show);
        visiblePasswordField.setManaged(show);


        // CONFIRM PASSWORD

        confirmPasswordField.setVisible(!show);
        confirmPasswordField.setManaged(!show);

        visibleConfirmPasswordField.setVisible(show);
        visibleConfirmPasswordField.setManaged(show);


        // KEEP FOCUS NATURAL

        if (show) {

            visiblePasswordField.requestFocus();

            visiblePasswordField.positionCaret(
                    visiblePasswordField
                            .getText()
                            .length()
            );

        } else {

            passwordField.requestFocus();

            passwordField.positionCaret(
                    passwordField
                            .getText()
                            .length()
            );
        }
    }


    // =========================================================
    // SIGN UP
    // =========================================================

    @FXML
    private void handleSignup(
            ActionEvent event) {

        String fullName =
                fullNameField
                        .getText()
                        .trim();

        String username =
                usernameField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String role =
                roleComboBox
                        .getValue();

        String password =
                passwordField
                        .getText();

        String confirmPassword =
                confirmPasswordField
                        .getText();


        // =====================================================
        // FULL NAME
        // =====================================================

        if (fullName.isEmpty()) {

            showError(
                    "Please enter your full name."
            );

            fullNameField.requestFocus();

            return;
        }


        // =====================================================
        // USERNAME
        // =====================================================

        if (username.isEmpty()) {

            showError(
                    "Please enter a username."
            );

            usernameField.requestFocus();

            return;
        }


        // =====================================================
        // EMAIL
        // =====================================================

        if (email.isEmpty()) {

            showError(
                    "Please enter your email."
            );

            emailField.requestFocus();

            return;
        }


        if (!email.contains("@")
                || !email.contains(".")) {

            showError(
                    "Please enter a valid email address."
            );

            emailField.requestFocus();

            return;
        }


        // =====================================================
        // ROLE
        // =====================================================

        if (role == null
                || role.isBlank()) {

            showError(
                    "Please select your role."
            );

            roleComboBox.requestFocus();

            return;
        }


        // =====================================================
        // PASSWORD
        // =====================================================

        if (password.isEmpty()) {

            showError(
                    "Please create a password."
            );

            passwordField.requestFocus();

            return;
        }


        if (!validPassword(password)) {

            showError(
                    "Your password does not meet all requirements."
            );

            return;
        }


        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        if (confirmPassword.isEmpty()) {

            showError(
                    "Please confirm your password."
            );

            confirmPasswordField.requestFocus();

            return;
        }


        if (!password.equals(
                confirmPassword)) {

            showError(
                    "Passwords do not match."
            );

            return;
        }


        // =====================================================
        // DUPLICATE EMAIL
        // =====================================================

        if (Database.emailExists(email)) {

            showError(
                    "That email is already registered."
            );

            return;
        }


        // =====================================================
        // DUPLICATE USERNAME
        // =====================================================

        if (Database.usernameExists(username)) {

            showError(
                    "That username is already taken."
            );

            return;
        }


        // =====================================================
        // SAVE USER
        // =====================================================
        //
        // NOTE:
        // Role is NOT being saved yet.
        //
        // Next step:
        // Database.registerUser(
        //     fullName,
        //     username,
        //     email,
        //     role,
        //     password
        // );
        //
        // =====================================================

        boolean registered =
                Database.registerUser(
                        fullName,
                        username,
                        email,
                        password
                );


        if (!registered) {

            showError(
                    "Unable to create your account."
            );

            return;
        }


        // =====================================================
        // SUCCESS
        // =====================================================

        Alert success =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        success.setTitle(
                "RizGo"
        );

        success.setHeaderText(
                "Account Created"
        );

        success.setContentText(
                "Your RizGo account has been created successfully.\n\n"
                        + "Role: "
                        + role
        );

        success.showAndWait();


        // =====================================================
        // RETURN TO LOGIN
        // =====================================================

        goTo(
                "/fxml/Login.fxml",
                event
        );
    }


    // =========================================================
    // VALIDATE PASSWORD
    // =========================================================

    private boolean validPassword(
            String password) {

        boolean validLength =
                password.length() >= 8
                        && password.length() <= 20;

        boolean hasUpper =
                password.matches(
                        ".*[A-Z].*"
                );

        boolean hasLower =
                password.matches(
                        ".*[a-z].*"
                );

        boolean hasSpecial =
                password.matches(
                        ".*[^A-Za-z0-9].*"
                );


        return validLength
                && hasUpper
                && hasLower
                && hasSpecial;
    }


    // =========================================================
    // BACK TO LOGIN
    // =========================================================

    @FXML
    private void backToLogin(
            ActionEvent event) {

        goTo(
                "/fxml/Login.fxml",
                event
        );
    }


    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private void showError(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "RizGo"
        );

        alert.setHeaderText(
                "Registration Failed"
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
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