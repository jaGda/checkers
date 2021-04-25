package com.checkers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.checkers.PawnColor.BLACK;
import static com.checkers.PawnColor.WHITE;

public class GameController {
    private GridPane gridPane;
    private Board board;
    private Stage stage;
    private final Image yellowPawn = new Image("file:src/main/resources/yellowPawn.png");
    private final Image blackPawn = new Image("file:src/main/resources/blackPawn.png");
    private final Image nonePawn = new Image("file:src/main/resources/nonePawn.png");
    private int colSelected = -1;
    private int rowSelected = -1;

    public GameController(GridPane gridPane, Board board, Stage stage) {
        this.gridPane = gridPane;
        this.board = board;
        this.stage = stage;
    }

    public void doClick(int x, int y) {

    }

    public void display() {
        setBoard();
    }

    private void setBoard() {
        gridPane.getChildren().clear();
        Pawn[][] pawns = board.getBoard();
        for (int i = 0; i < pawns.length; i++) {
            for (int j = 0; j < pawns[i].length; j++) {
                if (pawns[i][j].getColor() == BLACK) {
                    setImageView(gridPane, j, i, blackPawn);
                } else if (pawns[i][j].getColor() == WHITE) {
                    setImageView(gridPane, j, i, yellowPawn);
                } else {
                    setImageView(gridPane, j, i, nonePawn);
                }
                if (j == colSelected && i == rowSelected) {
                    Rectangle rectangle = new Rectangle(98, 98);
                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setStroke(Color.RED);
                    rectangle.setStrokeWidth(2);
                    gridPane.add(rectangle, j, i);
                }
            }
        }
    }

    private void setImageView(GridPane gridPane, int col, int row, Image image) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setSmooth(true);
        imageView.setImage(image);
        gridPane.add(imageView, col, row);
    }
}
