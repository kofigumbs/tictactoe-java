package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;

public class NaiveChoice implements Algorithm{
    @Override
    public int run(Board board) {
        return board.getEmptySpaceIds().get(0);
    }
}
