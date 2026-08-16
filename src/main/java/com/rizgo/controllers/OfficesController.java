package com.rizgo.controllers;

import com.rizgo.utils.PageTransition;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.layout.VBox;

public class OfficesController {

    // =========================================================
    // FXML COMPONENTS
    // =========================================================

    @FXML
    private TextField officeSearchField;

    @FXML
    private StackPane registrarCard;


    @FXML
    private StackPane guidanceCard;

    @FXML
    private StackPane principalCard;

    @FXML
    private StackPane accountingCard;

    @FXML
    private StackPane cashierCard;

    @FXML
    private StackPane clinicCard;


    // =========================================================
    // CUSTOM POPUP
    // =========================================================

    @FXML
    private StackPane officePopupOverlay;

    @FXML
    private VBox officePopupCard;

    @FXML
    private Label officePopupTitle;

    @FXML
    private Label officePopupMessage;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        if (officePopupOverlay != null) {

            officePopupOverlay.setVisible(false);
            officePopupOverlay.setManaged(false);
        }
    }


    // =========================================================
    // SEARCH
    // =========================================================

    @FXML
    private void searchOffice() {

        if (officeSearchField == null) {
            return;
        }

        String search =
                officeSearchField
                        .getText()
                        .trim()
                        .toLowerCase();


        if (search.isEmpty()) {

            showAllOffices();

            return;
        }


        setCardVisible(
                registrarCard,
                search.contains("registrar")
                        || "registrar's office".contains(search)
        );


        setCardVisible(
                guidanceCard,
                search.contains("guidance")
                        || "guidance office".contains(search)
        );


        setCardVisible(
                principalCard,
                search.contains("principal")
                        || "principal's office".contains(search)
        );


        setCardVisible(
                accountingCard,
                search.contains("account")
                        || "accounting office".contains(search)
        );


        setCardVisible(
                cashierCard,
                search.contains("cashier")
                        || "cashier".contains(search)
        );


        setCardVisible(
                clinicCard,
                search.contains("clinic")
                        || "clinic".contains(search)
        );
    }


    private void setCardVisible(
            Node card,
            boolean visible) {

        if (card == null) {
            return;
        }

        card.setVisible(visible);
        card.setManaged(visible);
    }


    private void showAllOffices() {

        setCardVisible(registrarCard, true);
        setCardVisible(guidanceCard, true);
        setCardVisible(principalCard, true);
        setCardVisible(accountingCard, true);
        setCardVisible(cashierCard, true);
        setCardVisible(clinicCard, true);
    }


    // =========================================================
    // REGISTRAR
    // =========================================================

    @FXML
    private void openRegistrar() {

        showOfficePopup(
                "Registrar's Office",
                "Handles student records, enrollment documents, "
                        + "transcripts, certifications, and other "
                        + "academic record requests."
        );
    }


    // =========================================================
    // GUIDANCE
    // =========================================================

    @FXML
    private void openGuidance() {

        showOfficePopup(
                "Guidance Office",
                "Provides counseling, student guidance, academic "
                        + "support, and other student assistance services."
        );
    }


    // =========================================================
    // PRINCIPAL
    // =========================================================

    @FXML
    private void openPrincipal() {

        showOfficePopup(
                "Principal's Office",
                "Handles school administration, official concerns, "
                        + "and matters that require the attention of "
                        + "the school principal."
        );
    }


    // =========================================================
    // ACCOUNTING
    // =========================================================

    @FXML
    private void openAccounting() {

        showOfficePopup(
                "Accounting Office",
                "Handles school financial records, accounting concerns, "
                        + "and other authorized financial transactions."
        );
    }


    // =========================================================
    // CASHIER
    // =========================================================

    @FXML
    private void openCashier() {

        showOfficePopup(
                "Cashier",
                "Handles authorized school payments, payment records, "
                        + "and other payment-related concerns."
        );
    }


    // =========================================================
    // CLINIC
    // =========================================================

    @FXML
    private void openClinic() {

        showOfficePopup(
                "Clinic",
                "Provides basic health assistance, first aid, "
                        + "and school medical services."
        );
    }


    // =========================================================
    // SHOW CUSTOM POPUP
    // =========================================================

    private void showOfficePopup(
            String title,
            String message) {

        officePopupTitle.setText(title);
        officePopupMessage.setText(message);

        officePopupOverlay.setManaged(true);
        officePopupOverlay.setVisible(true);

        officePopupOverlay.toFront();


        // Fade background in

        officePopupOverlay.setOpacity(0);

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(180),
                        officePopupOverlay
                );

        fade.setFromValue(0);
        fade.setToValue(1);


        // Small popup scale animation

        officePopupCard.setScaleX(0.92);
        officePopupCard.setScaleY(0.92);

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(180),
                        officePopupCard
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
    private void closeOfficePopup() {

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(150),
                        officePopupOverlay
                );

        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(event -> {

            officePopupOverlay.setVisible(false);
            officePopupOverlay.setManaged(false);

            officePopupOverlay.setOpacity(1);
        });

        fade.play();
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
    private void goHome(ActionEvent event) {

        PageTransition.slideBack(
                "/fxml/Dashboard.fxml",
                (Node) event.getSource()
        );
    }


    // =========================================================
    // MAP
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
    // PROFILE
    // =========================================================

    @FXML
    private void openProfile(ActionEvent event) {

        PageTransition.slideTo(
                "/fxml/Profile.fxml",
                (Node) event.getSource()
        );
    }
}