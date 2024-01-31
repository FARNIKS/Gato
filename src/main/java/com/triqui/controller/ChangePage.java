package com.triqui.controller;

import com.triqui.Main;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ChangePage {

    public ChangePage() {
    }
    protected void loadStage(String url, Event event,String state){
        Image logo = new Image(Main.class.getResourceAsStream("/img/logo.png"));
        try {
            // Get the source of the event and the current scene
            Object eventSource =event.getSource();
            Node sourseAsNode =(Node) eventSource;
            Scene oldScene=sourseAsNode.getScene();

            // Get the current Stage and hide it
            Window window= oldScene.getWindow();
            Stage stage=(Stage) window;
            stage.hide();

            // Load the FXML file using a FXMLLoader
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(url));
            Scene scene = new Scene(fxmlLoader.load(), 360, 561);
            stage.setScene(scene);

            // Create a new Stage and configure its appearance
            Stage newStage = new Stage();
            newStage.getIcons().add(logo);
            newStage.setResizable(false);
            newStage.setTitle("Gato-"+state);
            newStage.setScene(scene);
            newStage.show();

            // Set up a handler to close the new Stage
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Platform.exit();
                }
            });

        }catch (IOException ex){
            // Handle exceptions related to loading the FXML file
            System.err.println("Error al cargar el archivo FXML: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
