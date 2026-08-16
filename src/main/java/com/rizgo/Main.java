package com.rizgo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/Splash.fxml"));

        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(
                getClass().getResource("/css/rizgo.css").toExternalForm());

        stage.setTitle("RizGo");

        stage.setResizable(false);

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}