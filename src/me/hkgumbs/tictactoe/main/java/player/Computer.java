package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;

import java.io.OutputStream;
import java.io.PrintStream;

public class Computer implements Player {

    private final Algorithm algorithm;
    private final Board.Mark mark;
    private final OutputStream outputStream;

    public Computer(
            Board.Mark mark, OutputStream outputStream, Algorithm algorithm) {
        this.mark = mark;
        this.outputStream = outputStream;
        this.algorithm = algorithm;
    }

    public Class<? extends Algorithm> getAlgorithm() {
        return algorithm.getClass();
    }

    @Override
    public Board.Mark getMark() {
        return mark;
    }


    @Override
    public void onboard() {
    }

    @Override
    public boolean requestGoFirst() {
        return false;
    }

    @Override
    public boolean requestPlayAgain() {
        return false;
    }

    @Override
    public int determineNextMove(Board board) {
        int move = algorithm.run(board);
        new PrintStream(outputStream).println(mark + " >> " + move);
        return move;
    }
}
