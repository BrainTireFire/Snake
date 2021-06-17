package sample.view;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class GameView {

    private Group gameGroup;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    private static final int widthGameScene = 600;
    private static final int heightGameScene = 600;
    private static final int rows = 20;
    private static final int colums = rows;
    private static final int backgroundSquareSize = widthGameScene / rows;
    private static final String[] foodsSnake = new String[]{"/sample/view/resources/apple.png", "/sample/view/resources/strawberry.png"};
    private static final int movementSpeed=5;

    private static final int movementRight = 0;
    private static final int movementLeft = 1;
    private static final int movementUp = 2;
    private static final int movementDown= 3;

    private GraphicsContext graphicsContext;
    private List<Point> snakeBody = new ArrayList<>();
    private Point snakehead;
    private Image foodImage;
    private int foodLocationX;
    private int foodLocationY;
    private boolean gameOver;
    private int currentDirection;

    public static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }
    }

    public GameView(){
        initializeStage();
        createKeyListener();
        runGame(graphicsContext);
    }

    private void createKeyListener() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();

                if (code == KeyCode.D || code == KeyCode.RIGHT) {
                    if (currentDirection != movementLeft)
                        currentDirection = movementRight;
                }else if (code == KeyCode.A || code == KeyCode.LEFT) {
                    if (currentDirection != movementRight)
                        currentDirection = movementLeft;
                }
                else if (code == KeyCode.W || code == KeyCode.UP) {
                    if (currentDirection != movementDown)
                        currentDirection = movementUp;
                }else if (code == KeyCode.S || code == KeyCode.DOWN) {
                    if (currentDirection != movementUp)
                        currentDirection = movementDown;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

            }
        });
    }

    private void initializeStage() {
        gameGroup = new Group();
        Canvas canvas = new Canvas(widthGameScene, heightGameScene);
        gameGroup.getChildren().add(canvas);
        gameScene = new Scene(gameGroup);
        gameStage = new Stage();
        gameStage.setTitle("Snakeeeeeee");
        gameStage.setScene(gameScene);

        graphicsContext = canvas.getGraphicsContext2D();

        for (int i =0;i<3;i++){
            snakeBody.add(new Point(5, rows/2));
        }
        snakehead = snakeBody.get(0);
        generateFood();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e->runGame(graphicsContext)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void createNewGame(Stage menuStage){
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameStage.show();
    }

    private void runGame(GraphicsContext graphicsContext){
        drawBackground(graphicsContext);
        drawFood(graphicsContext);
        drawSnake(graphicsContext);

        for (int i = snakeBody.size() - 1;i>= 1;i--){
            snakeBody.get(i).x = snakeBody.get(i-1).x;
            snakeBody.get(i).y = snakeBody.get(i-1).y;
        }

        moveSnake();
    }

    private void generateFood(){
        start:
        while(true){
            foodLocationX = (int)(Math.random() * rows);
            foodLocationY = (int)(Math.random() * colums);

            for (Point p: snakeBody){
                if (p.x == foodLocationX && p.y == foodLocationY){
                    continue start;
                }
            }
            foodImage = new Image(foodsSnake[(int)(Math.random() * foodsSnake.length)]);
            break;
        }
    }

    private void drawFood(GraphicsContext graphicsContext){
        graphicsContext.drawImage(foodImage, foodLocationX * backgroundSquareSize, foodLocationY * backgroundSquareSize, backgroundSquareSize, backgroundSquareSize);
    }

    private void drawSnake(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.web("9ACD32"));
        graphicsContext.fillRoundRect(snakehead.getX()*backgroundSquareSize, snakehead.getY()*backgroundSquareSize,
                backgroundSquareSize -1, backgroundSquareSize- 1, 35, 35);

        for (int i=1;i<snakeBody.size();i++){
            graphicsContext.fillRoundRect(snakeBody.get(i).getX()*backgroundSquareSize, snakeBody.get(i).getY()*backgroundSquareSize,
                    backgroundSquareSize -1, backgroundSquareSize- 1, 20, 20 );
        }
    }

    private void moveSnake(){
        switch (currentDirection){
            case movementRight:
                moveSnakeRight();
                break;
            case movementLeft:
                moveSnakeLeft();
                break;
            case movementUp:
                moveSnakeUp();
                break;
            case movementDown:
                moveSnakeDown();
                break;
        }
    }

    private void moveSnakeRight(){
        snakehead.x++;
    }

    private void moveSnakeLeft(){
        snakehead.x--;
    }

    private void moveSnakeUp(){
        snakehead.y--;
    }

    private void moveSnakeDown(){
        snakehead.y++;
    }

    private void drawBackground(GraphicsContext graphicsContext){
        for (int i=0;i<rows;i++){
            for (int j=0;j<colums;j++){
                if((i+j)%2==0){
                    Button button = new Button("test");
                    graphicsContext.setFill(Color.web("E0FFFF"));
                }else {
                    graphicsContext.setFill(Color.web("AFEEEE"));
                }
                graphicsContext.fillRect(i*backgroundSquareSize, j* backgroundSquareSize, backgroundSquareSize, backgroundSquareSize);
            }
        }
    }
}

/*
 private void generateFood(){
        start: while(true){
            foodLocationX = (int)(Math.random() * rows);
            foodLocationY = (int)(Math.random() * colums);

            for (Point p: snakeBody){
                if (p.x == foodLocationX && p.y == foodLocationY){
                    continue start;
                }
            }
        }

    }

        public void animationSpeed(){
        new AnimationTimer(){
            long lastSpeed;

            @Override
            public void handle(long presentSpeed) {
                if (lastSpeed == 0){
                    lastSpeed = presentSpeed;
                }

                if (presentSpeed - lastSpeed > 1000000000/movementSpeed){
                    lastSpeed = presentSpeed;
                }
            }
        }.start();
    }

     if (code == KeyCode.RIGHT ){
                    if (currentDirection != movementLeft){
                        currentDirection = movementRight;
                    }
                }else if (keyEvent.getCode() == KeyCode.LEFT){

                }
 */