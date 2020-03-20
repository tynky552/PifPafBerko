package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Hrac {
    
    public String pripocitajBod(Boolean run, Label player1Score){
        String skoreString = null;
        if (run) {
            int skore = Integer.parseInt(player1Score.getText());
            skore++;
            skoreString= String.valueOf(skore);
        }
        return skoreString;
    }

    public void moveBlue(Boolean run , Circle blue){
        if (run) {
            final Rozdiel rozdiel = new Rozdiel();
            blue.setOnMousePressed(new EventHandler<MouseEvent>() {

                public void handle(MouseEvent mouseEvent) {
                    // record a delta distance for the drag and drop operation.
                    rozdiel.x = blue.getLayoutX() - mouseEvent.getSceneX();
                    rozdiel.y = blue.getLayoutY() - mouseEvent.getSceneY();
                    blue.setCursor(Cursor.MOVE);
                }
            });

            blue.setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    blue.setCursor(Cursor.HAND);
                }
            });

            blue.setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getSceneX() + rozdiel.x < 1000 - blue.getRadius() && mouseEvent.getSceneX() + rozdiel.x > 0 + blue.getRadius())
                        blue.setLayoutX(mouseEvent.getSceneX() + rozdiel.x);
                    if (mouseEvent.getSceneY() + rozdiel.y < 485 - blue.getRadius() && mouseEvent.getSceneY() + rozdiel.y > 0 + blue.getRadius())
                        blue.setLayoutY(mouseEvent.getSceneY() + rozdiel.y);
                }
            });

            blue.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    blue.setCursor(Cursor.HAND);
                }
            });

        }
    }

}

//public class Rozdiel {
//    double x, y;
//}
