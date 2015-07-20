package me.hkgumbs.main.java.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minimax {

    private static class Result {
        int value;
    }

    private static final int MAX_SCORE = 10;

    public final Mark mark;

    public Minimax(Mark mark) {
        this.mark = mark;
    }

    /* makes move and returns the position it took
     * caller is responsible for ensuring that game is not over */
    public int run(Board board) {
        Result result = new Result();
        if (board.empty())
            // if board is empty, calculations are not worth it
            return 0;

        run(board, mark, 0, result);
        return result.value;
    }

    /* recursively evaluates all possible moves
     * returns score of best outcome
     * stores best move in result */
    private int run(
            Board board, Mark current, int depth, Result result) {
        if (Game.over(board))
            return score(board, depth);

        List<Integer> scores = new ArrayList<>();
        List<Integer> moves = new ArrayList<>();

        for (int position : board.getEmpty()) {
            Board copy = board.add(position, current);
            scores.add(run(copy, current.other(), depth + 1, result));
            moves.add(position);
        }

        int scoreIndex;
        if (current == mark)
            scoreIndex = scores.indexOf(Collections.max(scores));
        else
            scoreIndex = scores.indexOf(Collections.min(scores));
        result.value = moves.get(scoreIndex);
        return scores.get(scoreIndex);
    }

    private int score(Board board, int depth) {
        Mark winner = Game.winner(board);
        if (mark == winner)
            return MAX_SCORE - depth;
        else if (winner == null)
            return 0;
        else
            return depth - MAX_SCORE;
    }
}
