package com.triqui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModeController implements Initializable {

    @FXML
    private RadioButton rbEasy,rbMedium,rbImpossible;
    @FXML
    private ToggleGroup difficulty;
    @FXML
    private ImageView imgStartCpu,imgStartP2, imgDifficulty,imgPvP,imgMusic;
    @FXML
    private Button btnStartCpu,btnPvP,btnMusic;
    private final ChangePage changePage = new ChangePage();
    private String urlImgLoop = "/img/P1vsCpuFLoop.gif";
    private String urlImgStatic= "/img/P1vsCpuF.png";
    private String modeGame ="EasyCpuMode";
    private String pageTitle ="P1 vs Easy CPU";
    private MediaPlayer mediaPlayer;
    private boolean mute =false;
    private String currentPlayerImageUrl = "/img/Music.png";

    public ModeController() {
        String musicFile = "/media/SelectMode.mp3";
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
    public String getModeGame() {
        return modeGame;
    }

    public void setModeGame(String modeGame) {
        this.modeGame = modeGame;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //RADIOBUTTON
        // Method used to be able to change images with radio button and group
        // These are the ids of the names of the imgs
        rbEasy.setUserData("EASY");
        rbMedium.setUserData("MEDIUM");
        rbImpossible.setUserData("IMPOSSIBLE");

        difficulty.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                //Add a static image or a gif depending on what is selected in the radio buttons,
                // depending on whether the mouse is inside or outside the CPU start button
                if(difficulty.getSelectedToggle().getUserData().equals("EASY")){
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/P1vsCpuF.gif")));
                    imgDifficulty.setImage(image);
                    urlImgLoop = "/img/P1vsCpuFLoop.gif";
                    urlImgStatic= "/img/P1vsCpuF.png";
                    setModeGame("EasyCpuMode");
                    setPageTitle("P1 vs Easy CPU");
                } else if(difficulty.getSelectedToggle().getUserData().equals("MEDIUM")){
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/P1vsCpuM.gif")));
                    imgDifficulty.setImage(image);
                    urlImgLoop = "/img/P1vsCpuMLoop.gif";
                    urlImgStatic= "/img/P1vsCpuM.png";
                    setModeGame("MediumCpuMode");
                    setPageTitle("P1 vs Medium CPU");
                } else if(difficulty.getSelectedToggle().getUserData().equals("IMPOSSIBLE")){
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/P1vsImpossibleCpu.gif")));
                    imgDifficulty.setImage(image);
                    urlImgLoop = "/img/P1vsImpossibleCpuLoop.gif";
                    urlImgStatic= "/img/P1vsImpossibleCpu.png";
                    setModeGame("ImpossibleCpuMode");
                    setPageTitle("P1 vs Impossible CPU");
                }
            }
        });
    }
    public void mouseEnterButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        if (sourceButton == btnStartCpu) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/StartSelected.png")));
            imgStartCpu.setImage(image);
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(urlImgLoop)));
            imgDifficulty.setImage(image2);

        }else if (sourceButton == btnPvP) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/StartSelected.png")));
            imgStartP2.setImage(image);
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/P1vsP2Loop.gif")));
            imgPvP.setImage(image2);
        }else if (sourceButton==btnMusic) {
            //changes the image if music is mute or unmute
            if (mute) {
                currentPlayerImageUrl = "/img/MusicMute.png";
            }
            else {
                currentPlayerImageUrl = "/img/MusicSelected.png";
            }

            Image currentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
            imgMusic.setImage(currentImage);

        }
    }
    public void mouseExitButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        if (sourceButton == btnStartCpu) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Start.png")));
            imgStartCpu.setImage(image);
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(urlImgStatic)));
            imgDifficulty.setImage(image2);
        }else if (sourceButton == btnPvP) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Start.png")));
            imgStartP2.setImage(image);
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/P1vsP2.png")));
            imgPvP.setImage(image2);
        }else if (sourceButton==btnMusic) {
            //changes the image if music is mute or unmute
            if (mute) {
                currentPlayerImageUrl = "/img/MusicMute.png";
            }
            else {
                currentPlayerImageUrl = "/img/Music.png";
            }

            Image currentImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
            imgMusic.setImage(currentImage);

        }
    }
    public void eventButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();

        if (sourceButton == btnStartCpu) {
            changePage.loadStage(getModeGame()+".fxml",mouseEvent, getPageTitle());
            mediaPlayer.stop();
        } else if (sourceButton == btnPvP) {
            changePage.loadStage("PvPMode.fxml",mouseEvent,"P1 vs P2");
            mediaPlayer.stop();
        } else if (sourceButton==btnMusic){
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
