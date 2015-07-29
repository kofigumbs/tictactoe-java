package me.hkgumbs.tictactoe.main.java.rules;

import me.hkgumbs.tictactoe.main.java.board.Board;

public interface Rules {
    Board.Mark determineWinner(Board board);

    boolean gameIsOver(Board board);

    boolean validateMove(Board board, int position);
}
