package me.hkgumbs.tictactoe.main.java;

public abstract class Player {
    public final Board.Mark mark;

    protected Player(Board.Mark mark) {
        this.mark = mark;
    }

    public boolean yesOrNo() {
        return false;
    }

    public abstract int consider(Board board);
}
