package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;

public interface Player {

    enum Response {
        YES, NO, DEFAULT
    }

    int determineNextMove(Board board);

    Board.Mark getMark();

    void onboard();

    Response requestGoFirst();

    Response requestPlayAgain();

}
