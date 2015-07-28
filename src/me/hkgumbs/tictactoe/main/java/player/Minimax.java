package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.TreeMap;

public class Minimax implements Player {

    private static final int MAX_SCORE = 10;

    private final Board.Mark mark;
    private final OutputStream outputStream;
    private final Simulation simulation;

    private int bestMove;

    private int determineNextMove(Board board, Board.Mark current, int depth,
                                  int alpha, int beta) {
        if (simulation.rules.gameIsOver(board))
            return score(board, depth);

        TreeMap<Integer, Integer> scores = new TreeMap<>();
        int cutoff = mark == current ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int position : board.getEmptySpaceIds()) {
            Board copy = board.add(position, current);
            int score = determineNextMove(
                    copy, current.other(), depth + 1, alpha, beta);

            if (mark == current) {
                cutoff = Integer.max(score, cutoff);
                alpha = Integer.max(cutoff, alpha);
            } else {
                cutoff = Integer.min(score, cutoff);
                beta = Integer.min(cutoff, beta);
            }

            scores.putIfAbsent(score, position);
            if (beta <= alpha)
                break;
        }

        int key = mark == current ? scores.lastKey() : scores.firstKey();
        bestMove = scores.get(key);
        return key;
    }

    private void printBestMove() {
        if (outputStream != null)
            new PrintStream(outputStream).println(mark + " >> " + bestMove);
    }

    private int score(Board board, int depth) {
        Board.Mark winner = simulation.rules.determineWinner(board);
        if (winner == mark)
            return MAX_SCORE - depth;
        else if (winner == mark.other())
            return depth - MAX_SCORE;
        else
            return 0;

    }

    public Minimax(
            Board.Mark mark, OutputStream outputStream, Simulation simulation) {
        this.mark = mark;
        this.outputStream = outputStream;
        this.simulation = simulation;
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
        if (board.isEmpty())
            /* calculations are not worth it */
            bestMove = 0;
        else
            determineNextMove(
                    board, mark, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        printBestMove();
        return bestMove;
    }
}
