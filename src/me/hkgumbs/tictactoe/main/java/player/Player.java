package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;

public interface Player {

    int determineNextMove(Board board);

    Board.Mark getMark();

    void onboard();

    boolean requestGoFirst();

    boolean requestPlayAgain();

}
