package com.triqui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;


public class HomeController {
    @FXML
    private ImageView imgStart,imgMusic;
    @FXML
    private Button btnStart,btnMusic;
    @FXML
    private final ChangePage changePage = new ChangePage();
    private MediaPlayer mediaPlayer;
    private boolean mute =false;
    private String currentPlayerImageUrl = "/img/Music.png";

    public HomeController() {
        String musicFile = "/media/StartMenu.mp3";
        URL resource = getClass().getResource(musicFile);

        if (resource != null) {
            Media sound = new Media(((URL) resource).toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.1);
            // Play the music when you start the driver instance
            mediaPlayer.play();
        } else {
            System.err.println("No se pudo encontrar el archivo de música.");
        }

    }
    public void mouseEnterButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        if (sourceButton == btnStart) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/StartSelected.png")));
            imgStart.setImage(image);
        }else if (sourceButton==btnMusic) {
            //changes the image if music is mute or unmute
            if (mute) {
                currentPlayerImageUrl = "/img/MusicMute.png";
            }
            else {
                currentPlayerImageUrl = "/img/MusicSelected.png";
            }
            Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
            imgMusic.setImage(testImage);
        }
    }

    public void mouseExitButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        if (sourceButton == btnStart) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Start.png")));
            imgStart.setImage(image);
        }else if (sourceButton==btnMusic) {
            //changes the image if music is mute or unmute
            if (mute) {
                currentPlayerImageUrl = "/img/MusicMute.png";
            }
            else {
                currentPlayerImageUrl = "/img/Music.png";
            }

            Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
            imgMusic.setImage(testImage);

        }
    }
    public void eventsButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        if (sourceButton == btnStart) {
            changePage.loadStage("Mode.fxml", mouseEvent, "Game Mode");
            mediaPlayer.stop();
        }else if (sourceButton == btnMusic) {
            muteMusicEvent(mouseEvent);
        }
    }


    public void muteMusicEvent(MouseEvent mouseEvent) {
        if (mute) {
            currentPlayerImageUrl = "/img/MusicSelected.png";
            mediaPlayer.setVolume(0.1);
            //mediaPlayer.play();
            mute =false;
        }
        else{
            currentPlayerImageUrl = "/img/MusicMute.png";
            mediaPlayer.setVolume(0);
            //mediaPlayer.stop();
            mute = true;
        }
        // Upload the image manually
        Image currentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
        imgMusic.setImage(currentImage);

    }

}
