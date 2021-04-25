package com.checkers;

public class Pawn {

    private PawnColor color = PawnColor.NONE;
    private boolean isQueen = false;

    public PawnColor getColor() {
        return color;
    }

    public void setColor(PawnColor color) {
        this.color = color;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
    }

    @Override
    public String toString() {
        return "" + color;
    }
}
