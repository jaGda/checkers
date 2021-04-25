package com.checkers;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameController {

    private GridPane gridPane;
    private Board board;
    private Stage stage;

    public GameController(GridPane gridPane, Board board, Stage stage) {
        this.gridPane = gridPane;
        this.board = board;
        this.stage = stage;
    }

    public void display() {
    }

    public void doClick(int x, int y) {

    }
}
