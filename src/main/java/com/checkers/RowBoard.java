package com.checkers;

public class RowBoard {

    private final Pawn[] row = new Pawn[8];

    public RowBoard() {
        for (int i = 0; i < row.length; i++) {
            row[i] = new Pawn();
        }
    }

    public Pawn[] getRow() {
        return row;
    }
}
