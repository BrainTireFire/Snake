package sample.model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SnakeSubScene extends SubScene {

    private final static String backgroundImage = "sample/model/resources/background.jpg";

    public SnakeSubScene() {
        super(new AnchorPane(), 600, 600);
        prefWidth(600);
        prefHeight(600);

        BackgroundImage image = new BackgroundImage(new Image(backgroundImage, 600, 600,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(image));

        setLayoutX(1000);
        setLayoutY(180);
    }

    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        transition.setToX(-1000);
        transition.setToY(-180);

        transition.play();
    }
}
