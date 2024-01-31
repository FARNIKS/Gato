package com.triqui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 561);
        Image logo = new Image(Main.class.getResourceAsStream("/img/logo.png"));
        stage.getIcons().add(logo);
        stage.setResizable(false);
        stage.setTitle("Gato-Home");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}