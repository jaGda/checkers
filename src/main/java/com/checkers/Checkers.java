package com.checkers;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Checkers extends Application {
    private final Image imageBack = new Image("file:src/main/resources/board.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        Board board = new Board();

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 800, 800, Color.WHITE);

        GameController gameController = new GameController(grid, board, primaryStage);

        gameController.display();

        grid.setOnMouseClicked(event -> {
            int x = (int) event.getX() / 100;
            int y = (int) event.getY() / 100;
            if (x >= 0 && y >= 0)
                gameController.doClick(x, y);
        });

        primaryStage.setTitle("checkers");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
