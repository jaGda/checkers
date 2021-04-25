package com.checkers;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.checkers.PawnColor.*;
import static com.checkers.PawnColor.NONE;

public class GameController {
    private final GridPane gridPane;
    private final Board board;
    private final Stage stage;
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

    public void doClick(int col, int row) {

        if (board.getBlackCollected() != 12 && board.getWhiteCollected() != 12) {
            if (colSelected == -1) {
                if (board.getPawn(col, row).getColor() == (board.isBlack() ? BLACK : WHITE)) {
                    colSelected = col;
                    rowSelected = row;
                }
            } else if (board.getPawn(col, row).getColor() == board.getPawn(colSelected, rowSelected).getColor()) {
                colSelected = col;
                rowSelected = row;
            } else if (board.move(colSelected, rowSelected, col, row)) {
                colSelected = -1;
                rowSelected = -1;
                if (checkPossibleToMove(!board.isBlack() ? BLACK : WHITE)) {
                    board.setBlack(!board.isBlack());
                }
                if (board.getBlackCollected() == 12) {
                    gameOver(stage, gridPane, "black");
                } else if (board.getWhiteCollected() == 12) {
                    gameOver(stage, gridPane, "white");
                }
            }
            setBoard();
        }
    }

    public void gameOver(Stage stage, GridPane gridPane, String player) {
        Label label = new Label("GAME OVER \n" + player + " won!!!");
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Cambria", 62));
        label.setWrapText(true);

        Popup popup = new Popup();
        popup.getContent().add(label);
        popup.setOnShown(e -> {
            popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
            popup.setY(stage.getY() + stage.getHeight() / 2 - popup.getHeight() / 2);
        });
        popup.show(stage);
        gridPane.setStyle("-fx-opacity: 0.5;");
    }

    private boolean checkPossibleToMove(PawnColor color) {
        List<Boolean> moves = new ArrayList<>();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (board.getBoard()[i][j].getColor() == color) {
                    moves.add(canIMakeMove(j, i));
                }
            }
        }
        return moves.contains(true);
    }

    private boolean canIMakeMove(int col, int row) {
        PawnColor color = board.getPawn(col, row).getColor();

        if (color == BLACK && isEven(row) && col == 7 && checkBottomLeftField(col, row)) {
            return true;
        } else if (color == BLACK && !isEven(row) && col == 0 && checkBottomRightField(col, row)) {
            return true;
        } else if (col != 7 && col != 0 && color == BLACK && checkBoardFieldsAheadForBlackPawns(col, row)) {
            return true;
        } else if (color == WHITE && isEven(row) && col == 7 && checkUpperLeftField(col, row)) {
            return true;
        } else if (color == WHITE && !isEven(row) && col == 0 && checkUpperRightField(col, row)) {
            return true;
        } else return col != 7 && col != 0 && color == WHITE && checkBoardFieldsAheadForWhitePawns(col, row);
    }

    private boolean checkBoardFieldsAheadForWhitePawns(int col, int row) {
        return checkUpperRightField(col, row) || checkUpperLeftField(col, row);
    }

    private boolean checkUpperRightField(int col, int row) {
        return board.getPawn(col + 1, row - 1).getColor() == NONE;
    }

    private boolean checkUpperLeftField(int col, int row) {
        return board.getPawn(col - 1, row - 1).getColor() == NONE;
    }

    private boolean checkBoardFieldsAheadForBlackPawns(int col, int row) {
        return checkBottomRightField(col, row) || checkBottomLeftField(col, row);
    }

    private boolean checkBottomRightField(int col, int row) {
        return board.getPawn(col + 1, row + 1).getColor() == NONE;
    }

    private boolean checkBottomLeftField(int col, int row) {
        return board.getPawn(col - 1, row + 1).getColor() == NONE;
    }

    private boolean isEven(int i) {
        return i % 2 == 0;
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
