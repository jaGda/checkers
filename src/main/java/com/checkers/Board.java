package com.checkers;

import static com.checkers.PawnColor.*;
import static com.checkers.PawnColor.NONE;

public class Board {
    private final Pawn[][] board = new Pawn[8][];
    private int whiteCollected = 0;
    private int blackCollected = 0;
    private boolean black = true;

    public Board() {
        initialPawnsArrangement();
    }

    public boolean move(int colSelected, int rowSelected, int colTarget, int rowTarget) {
        Pawn selected = getPawn(colSelected, rowSelected);
        Pawn target = getPawn(colTarget, rowTarget);

        if (black && Math.abs(colTarget - colSelected) == 1 && rowTarget - rowSelected == 1 && target.getColor() == NONE) {
            target.setColor(selected.getColor());
            selected.setColor(NONE);
            return true;
        } else if (!black && Math.abs(colTarget - colSelected) == 1 && rowSelected - rowTarget == 1 && target.getColor() == NONE) {
            target.setColor(selected.getColor());
            selected.setColor(NONE);
            return true;
        } else if (Math.abs(colTarget - colSelected) == 2 && Math.abs(rowTarget - rowSelected) == 2
                && getPawn((colSelected + colTarget) / 2, (rowSelected + rowTarget) / 2).getColor() == (isBlack() ? WHITE : BLACK)
                && target.getColor() == NONE) {
            target.setColor(selected.getColor());
            selected.setColor(NONE);
            getPawn((colSelected + colTarget) / 2, (rowSelected + rowTarget) / 2).setColor(NONE);
            if (isBlack()) {
                blackCollected++;
            } else {
                whiteCollected++;
            }
            return true;
        }
        return false;
    }

    private void initialPawnsArrangement() {
        for (int i = 0; i < board.length; i++) {
            board[i] = new RowBoard().getRow();
            if (i < 3 && isEven(i)) {
                setOddRow(board[i], BLACK);
            } else if (i < 3) {
                setEvenRow(board[i], BLACK);
            } else if (i > 4 && isEven(i)) {
                setOddRow(board[i], WHITE);
            } else if (i > 4) {
                setEvenRow(board[i], WHITE);
            }
        }
    }

    private void setOddRow(Pawn[] pawns, PawnColor pawnColor) {
        for (int j = 0; j < pawns.length; j++) {
            if (!isEven(j)) {
                pawns[j].setColor(pawnColor);
            }
        }
    }

    private void setEvenRow(Pawn[] pawns, PawnColor pawnColor) {
        for (int j = 0; j < pawns.length; j++) {
            if (isEven(j)) {
                pawns[j].setColor(pawnColor);
            }
        }
    }

    private boolean isEven(int i) {
        return i % 2 == 0;
    }

    public Pawn getPawn(int col, int row) {
        return board[row][col];
    }

    public Pawn[][] getBoard() {
        return board;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public int getWhiteCollected() {
        return whiteCollected;
    }

    public int getBlackCollected() {
        return blackCollected;
    }
}
