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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DirectoryController {

    // =========================================================
    // SEARCH
    // =========================================================

    @FXML
    private TextField searchField;


    // =========================================================
    // DIRECTORY LIST
    // =========================================================

    @FXML
    private VBox directoryList;


    // =========================================================
    // FILTER BUTTONS
    // =========================================================

    @FXML
    private ToggleButton allFilter;

    @FXML
    private ToggleButton officeFilter;

    @FXML
    private ToggleButton facilityFilter;

    @FXML
    private ToggleButton serviceFilter;


    // =========================================================
    // CUSTOM POPUP
    // =========================================================

    @FXML
    private StackPane directoryPopupOverlay;

    @FXML
    private VBox directoryPopupCard;

    @FXML
    private Label directoryPopupTitle;

    @FXML
    private Label directoryPopupCategory;

    @FXML
    private Label directoryPopupMessage;


    private String activeFilter = "all";


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        if (directoryPopupOverlay != null) {

            directoryPopupOverlay.setVisible(false);
            directoryPopupOverlay.setManaged(false);
        }

        updateFilterButtons();
    }


    // =========================================================
    // SEARCH
    // =========================================================

    @FXML
    private void searchDirectory() {

        updateDirectory();
    }


    // =========================================================
    // FILTER ALL
    // =========================================================

    @FXML
    private void filterAll() {

        activeFilter = "all";

        updateFilterButtons();

        updateDirectory();
    }


    // =========================================================
    // FILTER OFFICES
    // =========================================================

    @FXML
    private void filterOffices() {

        activeFilter = "office";

        updateFilterButtons();

        updateDirectory();
    }


    // =========================================================
    // FILTER FACILITIES
    // =========================================================

    @FXML
    private void filterFacilities() {

        activeFilter = "facility";

        updateFilterButtons();

        updateDirectory();
    }


    // =========================================================
    // FILTER SERVICES
    // =========================================================

    @FXML
    private void filterServices() {

        activeFilter = "service";

        updateFilterButtons();

        updateDirectory();
    }


    // =========================================================
    // UPDATE FILTER BUTTONS
    // =========================================================

    private void updateFilterButtons() {

        updateButtonStyle(
                allFilter,
                activeFilter.equals("all")
        );

        updateButtonStyle(
                officeFilter,
                activeFilter.equals("office")
        );

        updateButtonStyle(
                facilityFilter,
                activeFilter.equals("facility")
        );

        updateButtonStyle(
                serviceFilter,
                activeFilter.equals("service")
        );
    }


    private void updateButtonStyle(
            ToggleButton button,
            boolean active) {

        if (button == null) {
            return;
        }

        button.getStyleClass().removeAll(
                "directoryFilter",
                "directoryFilterActive"
        );

        if (active) {

            button.getStyleClass().add(
                    "directoryFilterActive"
            );

        } else {

            button.getStyleClass().add(
                    "directoryFilter"
            );
        }

        button.setSelected(active);
    }


    // =========================================================
    // UPDATE DIRECTORY LIST
    // =========================================================

    private void updateDirectory() {

        if (searchField == null
                || directoryList == null) {

            return;
        }


        String search =
                searchField.getText()
                        .trim()
                        .toLowerCase();


        for (Node node :
                directoryList.getChildren()) {


            if (!(node instanceof Button button)) {
                continue;
            }


            String category =
                    String.valueOf(
                            button.getUserData()
                    ).toLowerCase();


            String text =
                    extractButtonText(
                            button
                    ).toLowerCase();


            boolean categoryMatch =
                    activeFilter.equals("all")
                            || category.equals(
                            activeFilter
                    );


            boolean searchMatch =
                    search.isEmpty()
                            || text.contains(
                            search
                    );


            boolean visible =
                    categoryMatch
                            && searchMatch;


            button.setVisible(
                    visible
            );

            button.setManaged(
                    visible
            );
        }
    }


    // =========================================================
    // EXTRACT TEXT FROM DIRECTORY CARD
    // =========================================================

    private String extractButtonText(
            Button button) {

        StringBuilder builder =
                new StringBuilder();


        if (button.getGraphic()
                instanceof HBox row) {


            for (Node child :
                    row.getChildren()) {


                if (child instanceof Label label) {

                    builder.append(
                            label.getText()
                    ).append(" ");


                } else if (child instanceof VBox box) {


                    for (Node sub :
                            box.getChildren()) {


                        if (sub instanceof Label label) {

                            builder.append(
                                    label.getText()
                            ).append(" ");
                        }
                    }
                }
            }
        }


        return builder.toString();
    }


    // =========================================================
    // REGISTRAR
    // =========================================================

    @FXML
    private void openRegistrar() {

        showDirectoryPopup(
                "Registrar's Office",
                "Office",
                "Handles student records, enrollment documents, "
                        + "and academic record requests."
        );
    }


    // =========================================================
    // GUIDANCE
    // =========================================================

    @FXML
    private void openGuidance() {

        showDirectoryPopup(
                "Guidance Office",
                "Office",
                "Provides counseling, student support, "
                        + "and guidance services."
        );
    }


    // =========================================================
    // LIBRARY
    // =========================================================

    @FXML
    private void openLibrary() {

        showDirectoryPopup(
                "Library",
                "Facility",
                "A learning facility for reading, studying, "
                        + "and accessing academic resources."
        );
    }


    // =========================================================
    // GYMNASIUM
    // =========================================================

    @FXML
    private void openGym() {

        showDirectoryPopup(
                "Gymnasium",
                "Facility",
                "A sports and activity facility used for "
                        + "physical education and school programs."
        );
    }


    // =========================================================
    // ENROLLMENT
    // =========================================================

    @FXML
    private void openEnrollment() {

        showDirectoryPopup(
                "Enrollment",
                "Service",
                "Provides student registration and "
                        + "enrollment-related assistance."
        );
    }


    // =========================================================
    // STUDENT RECORDS
    // =========================================================

    @FXML
    private void openStudentRecords() {

        showDirectoryPopup(
                "Student Records",
                "Service",
                "Provides assistance with student academic "
                        + "records and document requests."
        );
    }


    // =========================================================
    // SHOW CUSTOM POPUP
    // =========================================================

    private void showDirectoryPopup(
            String title,
            String category,
            String message) {


        directoryPopupTitle.setText(
                title
        );


        directoryPopupCategory.setText(
                "Category: " + category
        );


        directoryPopupMessage.setText(
                message
        );


        directoryPopupOverlay.setManaged(
                true
        );

        directoryPopupOverlay.setVisible(
                true
        );

        directoryPopupOverlay.toFront();


        // =====================================================
        // FADE ANIMATION
        // =====================================================

        directoryPopupOverlay.setOpacity(
                0
        );


        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(180),
                        directoryPopupOverlay
                );

        fade.setFromValue(
                0
        );

        fade.setToValue(
                1
        );


        // =====================================================
        // POPUP SCALE ANIMATION
        // =====================================================

        directoryPopupCard.setScaleX(
                0.92
        );

        directoryPopupCard.setScaleY(
                0.92
        );


        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(180),
                        directoryPopupCard
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
    // CLOSE CUSTOM POPUP
    // =========================================================

    @FXML
    private void closeDirectoryPopup() {

        if (directoryPopupOverlay == null) {
            return;
        }


        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(140),
                        directoryPopupOverlay
                );


        fade.setFromValue(
                1
        );

        fade.setToValue(
                0
        );


        fade.setOnFinished(
                event -> {

                    directoryPopupOverlay.setVisible(
                            false
                    );

                    directoryPopupOverlay.setManaged(
                            false
                    );

                    directoryPopupOverlay.setOpacity(
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
    // CHATBOT
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