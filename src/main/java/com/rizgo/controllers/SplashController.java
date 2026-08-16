package com.rizgo.controllers;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashController {

    // =========================================================
    // FXML COMPONENTS
    // =========================================================

    @FXML
    private ProgressBar progressBar;

    @FXML
    private StackPane locationLogo;

    @FXML
    private Label loadingLabel;


    // =========================================================
    // VARIABLES
    // =========================================================

    private Timeline loadingTimeline;

    private SequentialTransition pinBounceAnimation;

    private double progress = 0.0;


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    private void initialize() {

        progressBar.setProgress(0.0);

        // Start slightly transparent for smooth appearance
        progressBar.setOpacity(0.0);
        loadingLabel.setOpacity(0.0);

        Platform.runLater(() -> {

            animateSplashEntrance();

            startPinBounce();

            startLoading();
        });
    }


    // =========================================================
    // SPLASH ENTRANCE ANIMATION
    // =========================================================

    private void animateSplashEntrance() {

        // -----------------------------------------------------
        // LOGO FADE IN
        // -----------------------------------------------------

        FadeTransition logoFade =
                new FadeTransition(
                        Duration.millis(650),
                        locationLogo.getParent()
                );

        logoFade.setFromValue(0.0);
        logoFade.setToValue(1.0);


        // -----------------------------------------------------
        // LOGO SLIDE UP
        // -----------------------------------------------------

        TranslateTransition logoMove =
                new TranslateTransition(
                        Duration.millis(650),
                        locationLogo.getParent()
                );

        logoMove.setFromY(25);
        logoMove.setToY(0);

        logoMove.setInterpolator(
                Interpolator.EASE_OUT
        );


        // -----------------------------------------------------
        // PROGRESS BAR FADE IN
        // -----------------------------------------------------

        FadeTransition progressFade =
                new FadeTransition(
                        Duration.millis(500),
                        progressBar
                );

        progressFade.setFromValue(0.0);
        progressFade.setToValue(1.0);

        progressFade.setDelay(
                Duration.millis(350)
        );


        // -----------------------------------------------------
        // LOADING TEXT FADE IN
        // -----------------------------------------------------

        FadeTransition textFade =
                new FadeTransition(
                        Duration.millis(500),
                        loadingLabel
                );

        textFade.setFromValue(0.0);
        textFade.setToValue(1.0);

        textFade.setDelay(
                Duration.millis(500)
        );


        // -----------------------------------------------------
        // PLAY TOGETHER
        // -----------------------------------------------------

        ParallelTransition entrance =
                new ParallelTransition(
                        logoFade,
                        logoMove,
                        progressFade,
                        textFade
                );

        entrance.play();
    }


    // =========================================================
    // LOCATION PIN BOUNCE
    // =========================================================

    private void startPinBounce() {

        // Move upward
        TranslateTransition bounceUp =
                new TranslateTransition(
                        Duration.millis(300),
                        locationLogo
                );

        bounceUp.setFromY(0);
        bounceUp.setToY(-16);

        bounceUp.setInterpolator(
                Interpolator.EASE_OUT
        );


        // Drop down
        TranslateTransition bounceDown =
                new TranslateTransition(
                        Duration.millis(260),
                        locationLogo
                );

        bounceDown.setFromY(-16);
        bounceDown.setToY(0);

        bounceDown.setInterpolator(
                Interpolator.EASE_IN
        );


        // Slight squash when landing
        ScaleTransition squash =
                new ScaleTransition(
                        Duration.millis(90),
                        locationLogo
                );

        squash.setFromX(1.0);
        squash.setFromY(1.0);

        squash.setToX(1.08);
        squash.setToY(0.90);


        // Return to normal
        ScaleTransition restore =
                new ScaleTransition(
                        Duration.millis(120),
                        locationLogo
                );

        restore.setFromX(1.08);
        restore.setFromY(0.90);

        restore.setToX(1.0);
        restore.setToY(1.0);


        // Small pause
        PauseTransition pause =
                new PauseTransition(
                        Duration.millis(300)
                );


        pinBounceAnimation =
                new SequentialTransition(
                        bounceUp,
                        bounceDown,
                        squash,
                        restore,
                        pause
                );


        pinBounceAnimation.setCycleCount(
                Animation.INDEFINITE
        );

        pinBounceAnimation.play();
    }


    // =========================================================
    // LOADING
    // =========================================================

    private void startLoading() {

        loadingTimeline =
                new Timeline(

                        new KeyFrame(
                                Duration.millis(55),

                                event -> {

                                    progress += 0.015;

                                    if (progress >= 1.0) {

                                        progress = 1.0;

                                        progressBar.setProgress(
                                                1.0
                                        );

                                        loadingLabel.setText(
                                                "Ready!"
                                        );


                                        loadingTimeline.stop();


                                        if (pinBounceAnimation != null) {

                                            pinBounceAnimation.stop();
                                        }


                                        animateFinish();

                                        return;
                                    }


                                    progressBar.setProgress(
                                            progress
                                    );


                                    updateLoadingText();
                                }
                        )
                );


        loadingTimeline.setCycleCount(
                Animation.INDEFINITE
        );

        loadingTimeline.play();
    }


    // =========================================================
    // LOADING TEXT ANIMATION
    // =========================================================

    private void updateLoadingText() {

        if (progress < 0.25) {

            loadingLabel.setText(
                    "Loading."
            );

        } else if (progress < 0.50) {

            loadingLabel.setText(
                    "Loading.."
            );

        } else if (progress < 0.75) {

            loadingLabel.setText(
                    "Loading..."
            );

        } else {

            loadingLabel.setText(
                    "Almost ready..."
            );
        }
    }


    // =========================================================
    // FINISH ANIMATION
    // =========================================================

    private void animateFinish() {

        // -----------------------------------------------------
        // PIN POP
        // -----------------------------------------------------

        ScaleTransition pinPop =
                new ScaleTransition(
                        Duration.millis(180),
                        locationLogo
                );

        pinPop.setToX(1.18);
        pinPop.setToY(1.18);

        pinPop.setAutoReverse(true);
        pinPop.setCycleCount(2);


        // -----------------------------------------------------
        // READY TEXT PULSE
        // -----------------------------------------------------

        ScaleTransition readyPulse =
                new ScaleTransition(
                        Duration.millis(180),
                        loadingLabel
                );

        readyPulse.setToX(1.08);
        readyPulse.setToY(1.08);

        readyPulse.setAutoReverse(true);
        readyPulse.setCycleCount(2);


        ParallelTransition finishEffect =
                new ParallelTransition(
                        pinPop,
                        readyPulse
                );


        finishEffect.setOnFinished(event -> {

            PauseTransition shortPause =
                    new PauseTransition(
                            Duration.millis(250)
                    );

            shortPause.setOnFinished(
                    e -> openLogin()
            );

            shortPause.play();
        });


        finishEffect.play();
    }


    // =========================================================
    // OPEN LOGIN
    // =========================================================

    private void openLogin() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/fxml/Login.fxml"
                            )
                    );

            Parent root =
                    loader.load();


            Scene currentScene =
                    progressBar.getScene();


            if (currentScene == null) {

                System.err.println(
                        "Splash scene is null."
                );

                return;
            }


            // IMPORTANT:
            // We only replace the content.
            // We DO NOT change window/frame dimensions.

            currentScene.setRoot(root);


            Stage stage =
                    (Stage) currentScene.getWindow();


            if (stage != null) {

                stage.setTitle(
                        "RizGo"
                );
            }


        } catch (Exception e) {

            System.err.println(
                    "FAILED TO OPEN LOGIN PAGE"
            );

            e.printStackTrace();
        }
    }
}