package sample.model;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SnakeGameScene extends SubScene {

    private final static String backgroundImage = "sample/model/resources/background.jpg";
    private static final int widthGameScene = 600;
    private static final int heightGameScene = 600;
    private static final int rows = 20;
    private static final int colums = rows;
    private static final int backgroundSquareSize = widthGameScene / rows;
    private static final String[] foodsSnake = new String[]{"/sample/view/resources/apple.png", "/sample/view/resources/strawberry.png"};

    private static final int movmentSpeedUp = 2;
    private static final int movmentSpeedDown = 3;
    private static final int movmentSpeedRight = 0;
    private static final int movmentSpeedLeft = 1;

    private GraphicsContext graphicsContext;
    //private List<Point> snakeBody = new ArrayList();
    //private Point snakeHead;
    private Image foodImage;
    private int foodLocationX;
    private int foodLocationY;
    private boolean gameOver;
    private int currentDirection;


    public SnakeGameScene() {
        super(new AnchorPane(), 600, 600);
        prefWidth(widthGameScene);
        prefHeight(heightGameScene);

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

    public void initlizaction(){
        Group root = new Group();
        Canvas canvas = new Canvas(widthGameScene, heightGameScene);
        root.getChildren().add(canvas);
    }

}
