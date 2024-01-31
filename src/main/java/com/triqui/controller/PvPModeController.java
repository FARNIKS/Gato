package com.triqui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;

public class PvPModeController {


    @FXML
    private ImageView space00, space01, space02, space10, space11, space12, space20, space21, space22, imgRestart,img,imgMusic,imgHome,imgExit,imgMode;
    @FXML
    private Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22, btnHome, btnMode, btnRestart, btnExit,btnMusic;
    @FXML
    private Label labelWinsX, labelWinsO;
    private final ChangePage changePage = new ChangePage();
    private final int rowsMap = 3;
    private final int columnsMap = 3;
    private final Button[][] map = new Button[rowsMap][columnsMap];
    private final ImageView[][] imgMap = new ImageView[rowsMap][columnsMap];
    private final int[][] boardGame =new int[3][3];
    private int contWinsX =0;
    private int contWinsO =0;
    private  int contTurnCpu =0;
    private int initialPlayer = 1;
    private MediaPlayer mediaPlayer;
    private String currentPlayerImageUrl = "/img/Music.png";
    private boolean mute =false;

    public PvPModeController() {
        String musicFile = "/media/PvP.mp3";
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
    public void initialize() {
        initializeMap();
        InitializeMapImg();

    }
    private void InitializeMapImg() {
        map[0][0] = btn00;
        map[0][1] = btn01;
        map[0][2] = btn02;
        map[1][0] = btn10;
        map[1][1] = btn11;
        map[1][2] = btn12;
        map[2][0] = btn20;
        map[2][1] = btn21;
        map[2][2] = btn22;
    }
    private void initializeMap() {
        //Botones
        imgMap[0][0] = space00;
        imgMap[0][1] = space01;
        imgMap[0][2] = space02;
        imgMap[1][0] = space10;
        imgMap[1][1] = space11;
        imgMap[1][2] = space12;
        imgMap[2][0] = space20;
        imgMap[2][1] = space21;
        imgMap[2][2] = space22;

    }
    public void mouseEnterButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        Image image;
        if (sourceButton == btnHome) {
            image= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/HomeSelected.png")));
            imgHome.setImage(image);
        }else if (sourceButton == btnMode) {
            image= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/ModeSelected.png")));
            imgMode.setImage(image);
        } else if (sourceButton==btnMusic) {
            //changes the image if music is mute or unmute
            if (mute) {
                currentPlayerImageUrl = "/img/MusicMute.png";
            }
            else {
                currentPlayerImageUrl = "/img/MusicSelected.png";
            }
            Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
            imgMusic.setImage(testImage);
        }else if (sourceButton==btnExit) {
            image= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/ExitSelected.png")));
            imgExit.setImage(image);
        }else if (sourceButton==btnRestart) {
            image= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/restartSelected.png")));
            imgRestart.setImage(image);
        }

    }

    public void mouseExitButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        Image image;
        if (sourceButton == btnHome) {
            image= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Home.png")));
            imgHome.setImage(image);

        }else if (sourceButton == btnMode) {
            image= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Mode.png")));
            imgMode.setImage(image);

        } else if (sourceButton==btnMusic) {
            //changes the image if music is mute or unmute
            if (mute) {
                currentPlayerImageUrl = "/img/MusicMute.png";
            }
            else {
                currentPlayerImageUrl = "/img/Music.png";
            }

            Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
            imgMusic.setImage(testImage);

        }else if (sourceButton==btnExit) {
            image= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Exit.png")));
            imgExit.setImage(image);

        }else if (sourceButton==btnRestart) {
            image= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/restart.png")));
            imgRestart.setImage(image);

        }
    }

    public void eventButtons(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        if (sourceButton == btnHome) {
            changePage.loadStage("Home.fxml", mouseEvent,"Home");
            mediaPlayer.stop();
        }else if (sourceButton == btnMode) {
            changePage.loadStage("Mode.fxml", mouseEvent,"Game Mode");
            mediaPlayer.stop();
        }else if (sourceButton == btnMusic) {
            muteMusicEvent(mouseEvent);
        }else if (sourceButton == btnExit) {
            Platform.exit();
        } else if (sourceButton==btnRestart) {
            resetBoard();
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
        Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
        imgMusic.setImage(testImage);

    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/SpaceTransparent.png")));
                imgMap[i][j].setImage(testImage);
                map[i][j].setDisable(false);
                initialPlayer = 1;
                boardGame[i][j]=0;
                contTurnCpu =0;
                addImageCurrentStatusGame("/img/DefaultMap.png");
            }
        }
    }
    private void changePlayerImage() {
        if (initialPlayer == 1) {
            initialPlayer = 2;
            contTurnCpu++;
        } else {
            initialPlayer = 1;
        }
    }

    public void turnBasedLogic(MouseEvent mouseEvent) {
        Button sourceButton = (Button) mouseEvent.getSource();
        //Gets the specific coordinate of the button pressed by being in grid-pane
        int row = -1, col = -1;
        for (int i = 0; i < rowsMap; i++) {
            for (int j = 0; j < columnsMap; j++) {
                if (map[i][j] == sourceButton) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        if (row != -1 && col != -1) {
            // Get the ImageView corresponding to the button pressed
            ImageView imageView = imgMap[row][col];
            // get the image current
            String currentPlayerImageUrl = (initialPlayer == 1) ? "/img/Player1X.png" : "/img/Player2CpuO.png";

            // add image buttons
            Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentPlayerImageUrl)));
            imageView.setImage(testImage);

            changePlayerImage();

            //Disables the button where a move is added by the P1 or Cpu
            sourceButton.setDisable(true);

            printBoard(row,col);
            winsLogic();

        }

    }

    private void printBoard(int row, int col) {
        //Print the dashboard with related changes in real-time
        boardGame[row][col]=initialPlayer;
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                System.out.print("|" + boardGame[f][c]);
            }
            System.out.println("|");
        }
    }

    private void winsLogic() {
        int img00 = boardGame[0][0];
        int img01 = boardGame[0][1];
        int img02 = boardGame[0][2];
        int img10 = boardGame[1][0];
        int img11 = boardGame[1][1];
        int img12 = boardGame[1][2];
        int img20 = boardGame[2][0];
        int img21 = boardGame[2][1];
        int img22 = boardGame[2][2];
        if (img00==1&&img01==1&&img02==1||
                img10==1&&img11==1&&img12==1||
                img20==1&&img21==1&&img22==1||
                img00==1&&img10==1&&img20==1||
                img01==1&&img11==1&&img21==1||
                img02==1&&img12==1&&img22==1||
                img00==1&&img11==1&&img22==1||
                img02==1&&img11==1&&img20==1){

            System.out.println("Gano O");
            contWinsO++;
            labelWinsO.setText(String.valueOf(contWinsO));

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j].setDisable(true);

                }
            }
            addImageCurrentStatusGame("/img/P2Wins.gif");

        }
        if (img00==2&&img01==2&&img02==2||
                img10==2&&img11==2&&img12==2||
                img20==2&&img21==2&&img22==2||
                img00==2&&img10==2&&img20==2||
                img01==2&&img11==2&&img21==2||
                img02==2&&img12==2&&img22==2||
                img00==2&&img11==2&&img22==2||
                img02==2&&img11==2&&img20==2) {
            System.out.println("Gano X");
            contWinsX++;
            labelWinsX.setText(Integer.toString(contWinsX));


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j].setDisable(true);
                }
            }
            addImageCurrentStatusGame("/img/P1Wins.gif");
        }else if (contTurnCpu ==5) {

            addImageCurrentStatusGame("/img/Tie.gif");

        }

    }
    private void addImageCurrentStatusGame(String url) {
        Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(url)));
        img.setImage(testImage);
    }
}










