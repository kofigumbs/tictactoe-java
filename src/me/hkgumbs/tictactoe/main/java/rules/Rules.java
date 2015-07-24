package me.hkgumbs.tictactoe.main.java.rules;

import me.hkgumbs.tictactoe.main.java.board.Board;

import java.io.OutputStream;

public interface Rules {
    Board.Mark determineWinner(Board board);

    void printWinnerMessage(Board board);

    void setMessageListener(OutputStream outputStream);

    boolean gameIsOver(Board board);

    boolean validateMove(Board board, int position);
}
