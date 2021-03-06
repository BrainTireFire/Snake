package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.model.SnakeButton;
import sample.model.SnakeSubScene;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private  static final int HEIGHT = 600;
    private  static final int WIDTH = 600;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int menuButtonStartLocationX = 200;
    private final static int menuButtonStartLocationY = 100;
    List<SnakeButton> menuButtons;

    private SnakeSubScene optionsSubScene;
    private SnakeSubScene scoreSubScene;

    public ViewManager(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createLogo();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(SnakeButton button){
        button.setLayoutX(menuButtonStartLocationX);
        button.setLayoutY(menuButtonStartLocationY + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createScoreSubScene(){
        scoreSubScene = new SnakeSubScene();
        mainPane.getChildren().add(scoreSubScene);
        scoreSubScene.moveSubScene();
        scoreSubScene.getPane().getChildren().add(createBackButton(scoreSubScene));
    }

    private void createOptionSubScene(){
        optionsSubScene = new SnakeSubScene();
        mainPane.getChildren().add(optionsSubScene);
        optionsSubScene.moveSubScene();
        optionsSubScene.getPane().getChildren().add(createBackButton(optionsSubScene));
    }

    private void createButtons() {
        createStartButton();
        createScoresButton();
        createOptionsButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton(){
        SnakeButton startButton = new SnakeButton("New Game");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameView gameView = new GameView();
                gameView.createNewGame(mainStage);
            }
        });
    }

    private void createScoresButton(){
        SnakeButton scoresButton = new SnakeButton("Scores");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createScoreSubScene();
            }
        });
    }

    private void createOptionsButton(){
        SnakeButton optionsButton = new SnakeButton("Options");
        addMenuButton(optionsButton);

        optionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createOptionSubScene();
            }
        });
    }

    private SnakeButton createBackButton(SnakeSubScene snakeSubScene){
        SnakeButton backButton = new SnakeButton("Back");
        backButton.setLayoutX(400);
        backButton.setLayoutY(550);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                snakeSubScene.moveSubScene();
            }
        });

        return backButton;
    }

    private void createCreditsButton(){
        SnakeButton creditsButton = new SnakeButton("Credits");
        addMenuButton(creditsButton);
    }

    private void createExitButton(){
        SnakeButton exitButton = new SnakeButton("Exit");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void createBackground(){
        Image backgroundImage = new Image("sample/view/resources/menu_background.png", 600, 600, false, true );
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo(){
        ImageView logo = new ImageView("sample/view/resources/Logo1.png");
        logo.setLayoutX(200);
        logo.setLayoutY(-50);

        logo.setOnMouseEntered(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);
    }

}
