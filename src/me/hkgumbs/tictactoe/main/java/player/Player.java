package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

public interface Player {
    int determineNextMove(Board board);

    Board.Mark getMark();

    void onboard();

    boolean playAgain();

    void setRules(Rules rules);

}
