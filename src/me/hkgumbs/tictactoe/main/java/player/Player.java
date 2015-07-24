package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

public interface Player {
    int determineNextMove(Board board);

    Board.Mark getMark();

    BoardFormatter getFormatter();

    void onboard();

    boolean playAgain();

    void setFormatter(BoardFormatter formatter);

    void setRules(Rules rules);
}
