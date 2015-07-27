package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minimax implements Player {

    private static final int MAX_SCORE = 10;

    private final Board.Mark mark;
    private final OutputStream outputStream;
    private final Simulation simulation;

    private int bestMove;

    private int calculateBestScore(
            Board.Mark current, List<Integer> scores, List<Integer> moves) {
        int scoreIndex;
        if (current == mark)
            scoreIndex = scores.indexOf(Collections.max(scores));
        else
            scoreIndex = scores.indexOf(Collections.min(scores));
        bestMove = moves.get(scoreIndex);
        return scores.get(scoreIndex);
    }

    private int determineNextMove(Board board, Board.Mark current, int depth) {
        if (simulation.rules.gameIsOver(board))
            return score(board, depth);

        List<Integer> scores = new ArrayList<>();
        List<Integer> moves = new ArrayList<>();

        for (int position : board.getEmptySpaceIds()) {
            Board copy = board.add(position, current);
            scores.add(determineNextMove(copy, current.other(), depth + 1));
            moves.add(position);
        }

        return calculateBestScore(current, scores, moves);
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
    public Response requestGoFirst() {
        return Response.DEFAULT;
    }

    @Override
    public Response requestPlayAgain() {
        return Response.DEFAULT;
    }

    @Override
    public int determineNextMove(Board board) {
        if (board.isEmpty())
            /* calculations are not worth it */
            bestMove = 0;
        else
            determineNextMove(board, mark, 0);
        printBestMove();
        return bestMove;
    }
}
