package me.hkgumbs.main.java.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minimax extends Player {

    private static class Result {
        int value;
    }

    private static final int MAX_SCORE = 10;

    /* recursively evaluates all possible moves
     * returns score of best outcome
     * stores best move in result */
    private int consider(
            Board board, Board.Mark current, int depth, Result result) {
        if (Game.over(board))
            return score(board, depth);

        List<Integer> scores = new ArrayList<>();
        List<Integer> moves = new ArrayList<>();

        for (int position : board.getEmpty()) {
            Board copy = board.add(position, current);
            scores.add(consider(copy, current.other(), depth + 1, result));
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
        Board.Mark winner = Game.winner(board);
        if (winner == mark)
            return MAX_SCORE - depth;
        else if (winner == mark.other())
            return depth - MAX_SCORE;
        else
            return 0;

    }

    public Minimax(Board.Mark mark) {
        super(mark);
    }

    /* returns the position of best move
     * caller is responsible for ensuring that game is not over */
    @Override
    public int consider(Board board) {
        Result result = new Result();
        if (board.empty())
            // if board is empty, calculations are not worth it
            return 0;

        consider(board, mark, 0, result);
        return result.value;
    }
}
