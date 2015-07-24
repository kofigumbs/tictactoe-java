package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minimax implements Player {

    private Board.Mark mark;

    private static class Result {
        int value;
    }

    private static final int MAX_SCORE = 10;

    /* recursively evaluates all possible moves
     * returns score of best outcome
     * stores best move in result */
    private int consider(
            Board board, Board.Mark current, int depth, Result result) {
        if (Rules.gameIsOver(board))
            return score(board, depth);

        List<Integer> scores = new ArrayList<>();
        List<Integer> moves = new ArrayList<>();

        for (int position : board.getEmptySpaceIds()) {
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
        Board.Mark winner = Rules.determineWinner(board);
        if (winner == mark)
            return MAX_SCORE - depth;
        else if (winner == mark.other())
            return depth - MAX_SCORE;
        else
            return 0;

    }

    public Minimax(Board.Mark mark) {
        this.mark = mark;
    }

    @Override
    public Board.Mark getMark() {
        return mark;
    }

    /* caller is responsible for ensuring that game is not over */
    @Override
    public int evaluate(Board board) {
        Result result = new Result();
        if (board.isEmpty())
            /* if board is isEmpty, calculations are not worth it */
            return 0;

        consider(board, mark, 0, result);
        return result.value;
    }

    @Override
    public boolean yesOrNo() {
        return false;
    }
}
