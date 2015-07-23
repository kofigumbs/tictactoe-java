package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minimax implements Player {

    private Board.Mark mark;
    private int bestMove;

    private static final int MAX_SCORE = 10;

    private int determineNextMove(Board board, Board.Mark current, int depth) {
        if (Rules.gameIsOver(board))
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
    public int determineNextMove(Board board) {
        if (board.isEmpty())
            /* calculations are not worth it */
            return 0;

        determineNextMove(board, mark, 0);
        return bestMove;
    }

    @Override
    public boolean yesOrNo() {
        return false;
    }
}
