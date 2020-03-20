package sample;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class Pocitac {

    public void visibleRed(Circle red) {
        double randomX = Math.random() * (1000 - 2 * red.getRadius()) + red.getRadius();
        double randomY = Math.random() * (485 - 2 * red.getRadius()) + red.getRadius();

        red.setLayoutX(randomX);
        red.setLayoutY(randomY);
        red.setVisible(true);
    }


    public String pripocitajBod(Boolean run, Label player2Score){
        String skoreString = null;
        if (run) {
            int skore = Integer.parseInt(player2Score.getText());
            skore++;
            skoreString= String.valueOf(skore);
        }
        return skoreString;
    }

}
