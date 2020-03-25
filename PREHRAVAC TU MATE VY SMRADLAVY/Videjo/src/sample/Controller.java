package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.net.URI;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;


public class Controller {

    private String vurl = new File("src/videja/Berko.mp4").getAbsolutePath();
    private MediaPlayer mediaPlayer;

    @FXML
    public Button btnVideo1, btnVideo2,btnVideo3,btnPlay_Stop;
    @FXML
    public MediaView mvVideo;

    @FXML
    private void onBerkoBtnClicked(){
        vurl = new File("src/videja/Berko.mp4").getAbsolutePath();
        setVideo();
    }

    @FXML
    private void onBranciBtnClicked(){
        setVideo();
    }

    @FXML
    private void onHresoBtnClicked(){
        setVideo();
    }

    @FXML
    private void onPlayBtnClicked(){
        if (mediaPlayer.getStatus()== MediaPlayer.Status.PLAYING){
            mediaPlayer.stop();
        }
        else {
            mediaPlayer.play();
        }
    }

    private void setVideo(){
        Media media = new Media(new File(vurl).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mvVideo.setMediaPlayer(mediaPlayer);
    }


}
