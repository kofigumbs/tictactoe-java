package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

import java.util.HashMap;
import java.util.TreeMap;

public class Minimax implements Algorithm {

    private class MoveScore {
        final int move;
        final int score;

        MoveScore(int move, int score) {
            this.move = move;
            this.score = score;
        }
    }

    private static final int MAX_SCORE = 10;

    private final HashMap<String, MoveScore> cache;
    private Board.Mark mark;
    private final Rules rules;
    private int bestMove;

    private int determineBestMove(Board board, Board.Mark current, int depth,
                                  int alpha, int beta) {
        if (cache.containsKey(board.toString())) {
            MoveScore moveScore = cache.get(board.toString());
            bestMove = moveScore.move;
            return moveScore.score;
        }

        if (rules.gameIsOver(board))
            return score(board, depth);

        TreeMap<Integer, Integer> scores = new TreeMap<>();
        int cutoff = mark == current ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int position : board.getEmptySpaceIds()) {
            Board copy = board.add(position, current);
            int score = determineBestMove(
                    copy, current.other(), depth + 1, alpha, beta);

            if (mark == current) {
                cutoff = Integer.max(score, cutoff);
                alpha = Integer.max(cutoff, alpha);
            } else {
                cutoff = Integer.min(score, cutoff);
                beta = Integer.min(cutoff, beta);
            }

            cache.put(copy.toString(), new MoveScore(position, score));
            scores.putIfAbsent(score, position);
            if (beta <= alpha)
                break;
        }

        int key = mark == current ? scores.lastKey() : scores.firstKey();
        bestMove = scores.get(key);
        return key;
    }

    private int score(Board board, int depth) {
        Board.Mark winner = rules.determineWinner(board);
        if (winner == mark)
            return MAX_SCORE - depth;
        else if (winner == mark.other())
            return depth - MAX_SCORE;
        else
            return 0;
    }

    public Minimax(Board.Mark mark, Rules rules) {
        this.mark = mark;
        this.rules = rules;
        cache = new HashMap<>();
    }

    @Override
    public int run(Board board) {
        if (board.isEmpty())
            /* calculations are not worth it */
            bestMove = 0;
        else {
            cache.clear();
            determineBestMove(
                    board, mark, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return bestMove;
    }
}
