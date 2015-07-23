package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;

public interface Player {
    Board.Mark getMark();

    int determineNextMove(Board board);

    boolean yesOrNo();
}
