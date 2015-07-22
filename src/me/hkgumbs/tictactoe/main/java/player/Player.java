package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;

public abstract class Player {
    public abstract Board.Mark getMark();

    public boolean yesOrNo() {
        return false;
    }

    public abstract int consider(Board board);
}
