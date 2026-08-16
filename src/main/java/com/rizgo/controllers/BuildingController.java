package com.rizgo.controllers;

import com.rizgo.utils.PageTransition;
import utils.MapRequest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class BuildingController {

    // =========================================================
    // SELECTED BUILDING
    // =========================================================

    public static String selectedBuilding = "";


    // =========================================================
    // FXML
    // =========================================================

    @FXML
    private Label buildingNameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label floorLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label additionalInfoLabel;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        loadBuildingInformation();
    }


    // =========================================================
    // LOAD BUILDING INFORMATION
    // =========================================================

    private void loadBuildingInformation() {

        if (selectedBuilding == null
                || selectedBuilding.isBlank()) {

            showUnknownBuilding();

            return;
        }


        switch (selectedBuilding) {


            // =================================================
            // ADMIN
            // =================================================

            case "Admin Office",
                 "Admin Building" -> {

                setBuildingInformation(
                        "Admin Office",

                        "The Admin Office handles administrative functions and school management services.",

                        "Administration",

                        "6 Floor",

                        "Open",

                        "Main Campus",

                        "Students may visit the Admin Office for administrative concerns and school-related inquiries."
                );
            }


            // =================================================
            // ALUMNI
            // =================================================

            case "Alumni Building" -> {

                setBuildingInformation(
                        "Alumni Building",

                        "The Alumni Building supports alumni activities, school programs, and related functions.",

                        "Club / Museum Facility",

                        "3 Floors",

                        "Open",

                        "Main Campus",

                        "The building is used for alumni-related programs and school activities."
                );
            }


            // =================================================
            // AMANG
            // =================================================

            case "Amang Building" -> {

                setBuildingInformation(
                        "Amang Building",

                        "The Amang Building is one of the academic buildings within Rizal High School.",

                        "Academic Building",

                        "3 Floors",

                        "Open",

                        "Main Campus",

                        "The building contains areas used for academic activities."
                );
            }


            // =================================================
            // CARUNCHO
            // =================================================

            case "Caruncho Gym" -> {

                setBuildingInformation(
                        "Caruncho Gym",

                        "Caruncho Gym is a sports and event facility used for school athletic activities and programs.",

                        "Sports Facility",

                        "1 Floor",

                        "Closed",

                        "Main Campus",

                        "The gymnasium is currently closed."
                );
            }


            // =================================================
            // EUSEBIO
            // =================================================

            case "Eusebio Building",
                 "Eusebio Building (EBEC)" -> {

                setBuildingInformation(
                        "Eusebio Building (EBEC)",

                        "The Eusebio Building is used for academic activities and student classes.",

                        "Academic Building",

                        "4 Floors",

                        "Open",

                        "Main Campus",

                        "The building contains classrooms and academic spaces."
                );
            }


            // =================================================
            // IR
            // =================================================

            case "I.R. Building" -> {

                setBuildingInformation(
                        "I.R. Building",

                        "The I.R. Building is the Isidro Rodriguez Building and is used for academic activities.",

                        "Academic Building",

                        "3 Floors",

                        "Open",

                        "Main Campus",

                        "The building contains classrooms and academic facilities."
                );
            }


            // =================================================
            // JOVITO SALONGA
            // =================================================

            case "Jovito Salonga Building" -> {

                setBuildingInformation(
                        "Jovito Salonga Building",

                        "The Jovito Salonga Building is an academic building located within the Rizal High School campus.",

                        "Academic Building",

                        "4 Floors",

                        "Open",

                        "Main Campus",

                        "The building is primarily used for academic activities."
                );
            }


            // =================================================
            // MAE
            // =================================================

            case "M.A.E. Building" -> {

                setBuildingInformation(
                        "M.A.E. Building",

                        "The M.A.E. Building is the Mariano Alcantara Eusebio Building and is used for academic activities.",

                        "Senior High School Building",

                        "6 Floors",

                        "Open",

                        "Main Campus",

                        "The building contains classrooms and facilities used by Senior High School students."
                );
            }


            // =================================================
            // NEPTALI
            // =================================================

            case "Neptali Building" -> {

                setBuildingInformation(
                        "Neptali Building",

                        "The Neptali Building is one of the academic buildings located on campus.",

                        "Academic Building",

                        "4 Floors",

                        "Open",

                        "Main Campus",

                        "The building is used for classroom and academic activities."
                );
            }


            // =================================================
            // RH
            // =================================================

            case "R.H. Building" -> {

                setBuildingInformation(
                        "R.H. Building",

                        "The R.H. Building is used for student academic activities.",

                        "Academic Building",

                        "3 Floors",

                        "Open",

                        "Main Campus",

                        "The building contains classrooms and learning areas."
                );
            }


            // =================================================
            // RJ
            // =================================================

            case "R.J. Building" -> {

                setBuildingInformation(
                        "R.J. Building",

                        "The R.J. Building is the Rufino Javier Building and is used for academic activities.",

                        "Academic Building",

                        "3 Floors",

                        "Open",

                        "Main Campus",

                        "The building contains classrooms and other academic facilities."
                );
            }


            // =================================================
            // CHAPEL
            // =================================================

            case "RHS Chapel" -> {

                setBuildingInformation(
                        "RHS Chapel",

                        "The RHS Chapel is a religious facility within Rizal High School.",

                        "Religious Facility",

                        "1 Floor",

                        "Open",

                        "Main Campus",

                        "The chapel is available for religious and school-related activities."
                );
            }


            // =================================================
            // OVAL
            // =================================================

            case "RHS Oval" -> {

                setBuildingInformation(
                        "RHS Oval",

                        "The RHS Oval is an outdoor sports and activity area within the school campus.",

                        "Outdoor Facility",

                        "Open Field",

                        "Open",

                        "Main Campus",

                        "The oval is used for sports, physical activities, and school events."
                );
            }


            // =================================================
            // ROMULO
            // =================================================

            case "Romulo Building" -> {

                setBuildingInformation(
                        "Romulo Building",

                        "The Romulo Building is an academic building used for student classes.",

                        "Academic Building",

                        "4 Floors",

                        "Open",

                        "Main Campus",

                        "The building contains classrooms used for academic activities."
                );
            }


            // =================================================
            // SCIENCE
            // =================================================

            case "Science Building",
                 "Science & SCE Building" -> {

                setBuildingInformation(
                        "Science & SCE Building",

                        "The Science & SCE Building is an academic building used for science-related classes and activities.",

                        "Campus Building",

                        "6 Floors",

                        "Open",

                        "Main Campus",

                        "The building contains academic spaces used for science and related subjects."
                );
            }


            // =================================================
            // TANGHALANG
            // =================================================

            case "Tanghalang Rizal" -> {

                setBuildingInformation(
                        "Tanghalang Rizal",

                        "Tanghalang Rizal is an auditorium and event facility used for school programs and activities.",

                        "Events Facility",

                        "2 Floor",

                        "Open",

                        "Main Campus",

                        "The facility hosts school events, performances, meetings, and programs."
                );
            }


            // =================================================
            // UNKNOWN
            // =================================================

            default -> showUnknownBuilding();
        }
    }


    // =========================================================
    // SET INFORMATION
    // =========================================================

    private void setBuildingInformation(
            String name,
            String description,
            String category,
            String floor,
            String status,
            String location,
            String additionalInfo) {

        buildingNameLabel.setText(name);

        descriptionLabel.setText(description);

        categoryLabel.setText(category);

        floorLabel.setText(floor);

        statusLabel.setText(status);

        locationLabel.setText(location);

        additionalInfoLabel.setText(
                additionalInfo
        );
    }


    // =========================================================
    // UNKNOWN
    // =========================================================

    private void showUnknownBuilding() {

        buildingNameLabel.setText(
                "Unknown Building"
        );

        descriptionLabel.setText(
                "No information available."
        );

        categoryLabel.setText("-");
        floorLabel.setText("-");
        statusLabel.setText("-");
        locationLabel.setText("-");

        additionalInfoLabel.setText(
                "No additional information available."
        );
    }


    // =========================================================
    // GET DIRECTIONS
    // =========================================================

    @FXML
    private void openMap(
            ActionEvent event) {

        if (selectedBuilding != null
                && !selectedBuilding.isBlank()) {

            MapRequest.setRequestedLocation(
                    selectedBuilding
            );
        }


        PageTransition.slideBack(
                "/fxml/Map.fxml",
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
                "/fxml/Map.fxml",
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