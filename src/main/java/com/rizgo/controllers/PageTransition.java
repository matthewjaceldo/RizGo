package com.rizgo.utils;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PageTransition {

    // =========================================================
    // OPEN PAGE - SLIDE FROM RIGHT
    // =========================================================

    public static void slideTo(
            String fxmlFile,
            Node sourceNode) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            PageTransition.class
                                    .getResource(fxmlFile)
                    );

            Parent newRoot =
                    loader.load();

            Scene scene =
                    sourceNode
                            .getScene();

            Stage stage =
                    (Stage) scene.getWindow();


            double width =
                    scene.getWidth();


            // New page starts outside right side
            newRoot.setTranslateX(width);


            // Replace current page
            scene.setRoot(newRoot);


            // Slide new page into view
            TranslateTransition transition =
                    new TranslateTransition(
                            Duration.millis(280),
                            newRoot
                    );

            transition.setFromX(width);
            transition.setToX(0);

            transition.play();


        } catch (Exception e) {

            System.err.println(
                    "Unable to open: "
                            + fxmlFile
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // BACK PAGE - SLIDE FROM LEFT
    // =========================================================

    public static void slideBack(
            String fxmlFile,
            Node sourceNode) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            PageTransition.class
                                    .getResource(fxmlFile)
                    );

            Parent newRoot =
                    loader.load();

            Scene scene =
                    sourceNode
                            .getScene();

            Stage stage =
                    (Stage) scene.getWindow();


            double width =
                    scene.getWidth();


            // Previous page starts outside left
            newRoot.setTranslateX(-width);


            // Replace current page
            scene.setRoot(newRoot);


            // Slide into view from left
            TranslateTransition transition =
                    new TranslateTransition(
                            Duration.millis(280),
                            newRoot
                    );

            transition.setFromX(-width);
            transition.setToX(0);

            transition.play();


        } catch (Exception e) {

            System.err.println(
                    "Unable to return to: "
                            + fxmlFile
            );

            e.printStackTrace();
        }
    }
}