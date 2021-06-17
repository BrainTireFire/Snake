package sample.model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class SnakeButton extends Button {

    private final String buttonPressedStyle = "-fx-background-color: #32CD32;";
    private final String buttonFreeStyle = "-fx-background-color: #98FB98;";

    public SnakeButton(String text){
        setText(text);
        setPrefWidth(200);
        setPrefHeight(50);
        setStyle(buttonFreeStyle);
        initializeButtonListeners();
    }

    private void setButtonPressedStyle(){
        setStyle(buttonPressedStyle);
        setPrefHeight(50);
        setLayoutY(getLayoutY());
    }

    private void setButtonReleasedStyle(){
        setStyle(buttonFreeStyle);
        setPrefHeight(50);
        setLayoutY(getLayoutY());
    }

    private void initializeButtonListeners(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }


}
