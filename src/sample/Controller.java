package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML
    private Button btnNewGame, btnTop10;
    @FXML
    private Label player1Score, player2Score, countdownLabel;
    @FXML
    private Slider difficultySlider;
    @FXML
    private AnchorPane arena;
    @FXML
    private Circle blue, red, click;

    private int interval;
    private int level;
    private float time;
    private float cyklus;
    private boolean run = false;
    private Timer timerGame = new Timer(),
            timerClick = new Timer(),
            timerPosition = new Timer();

    private Pocitac pocitac = new Pocitac();
    private Hrac hrac = new Hrac();
    private Top10Controller top10 = new Top10Controller();


    @FXML
    public void countdown() {

        time = 0;
        cyklus = 0;

        player1Score.setText("0");
        player2Score.setText("0");

        level = (int) difficultySlider.getValue();
        int priemer = 50 - 3 * level;
        blue.setRadius(priemer);
        red.setRadius(priemer);
        red.setLayoutX(413);
        red.setLayoutY(142);

        level = (int) (((float)priemer / 35) * 10);
        enemy();

        interval = 6 * priemer;

        countdownLabel.setText("" + interval / 60 + ":" + interval % 60);

        System.out.println(difficultySlider.getValue());
        if (!run) {
            timerGame.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        run = true;
                        cyklus = (float) (cyklus + 0.1);
                        time = (float) (time + 0.1);
                        //System.out.println((int) (cyklus * 10));

                        if ((int) (cyklus * 10) == level) {
                            pocitac.visibleRed(red);
                            cyklus = 0;
                        }

                        //System.out.println(time);

                        if ((int) (time * 10) == 10) {
                            interval--;
                            String seconds;
                            if (interval % 60<10){
                                seconds = "0"+interval % 60;
                            }else seconds = ""+interval % 60;
                            countdownLabel.setText("" + interval / 60 + ":" + seconds);
                            time = 0;

                        }
                    });
                }

            }, 0, 100);
        }

        if (interval == 0) {
            run = false;
            int skore1=Integer.parseInt(player1Score.getText());;
            int skore2= Integer.parseInt(player2Score.getText());
            if (skore1>skore2){
                top10.sendSkore(skore1);
            }
            else {
                top10.sendSkore(skore2);
            }
        }

    }


    @FXML
    public void blueMouseDragged() {
        hrac.moveBlue(run,blue);
    }

    //pripocita skore hracovi
    @FXML
    public void clickRed() {
        player1Score.setText(hrac.pripocitajBod(run , player1Score));
    }

    //pripocita skore pc
    @FXML
    public void clickBlue() {
        player2Score.setText(hrac.pripocitajBod(run , player2Score));
    }

    private double x,y;
    public void enemy() {
        if (!run) {
            //ak sa kruhy pretinaju klikne na hraca + hybe s ciernym kruhom
            timerClick.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        click.setRadius(10);
                        click.setLayoutX(x);
                        click.setLayoutY(y);

                        boolean intersects = Math.hypot(x-blue.getLayoutX(), y-blue.getLayoutY()) <= (click.getRadius() + blue.getRadius());//vzorec na zistenie ci sa kruhy pretinaju

                        if (intersects){
                            clickBlue();
                            System.out.println("klik");
                        }
                    });
                }
            }, 1000, 150 * level);

            //vracia poziciu hraca s delayom
            timerPosition.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(()->{
                        x = blue.getLayoutX();
                        y = blue.getLayoutY();
                    });
                }
            },2000, 150*level);
        }
    }

    public void openTop10(){
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("Top10.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Top10");
            stage.setScene(new Scene(root1));
            stage.setWidth(400);
            stage.setHeight(600);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e){

        }
    }



}

