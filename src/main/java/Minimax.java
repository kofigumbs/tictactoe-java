package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minimax {

    private static final int MAX_SCORE = 10;

    /* makes move and returns the position it took
     * caller is responsible for ensuring that game is not over */
    public static int run(Game game) {
        Result result = new Result();
        if (!game.getBoard().empty())
            // if board is empty, calculations are not worth it
            run(game, game.whoseTurn(), 0, result);
        game.play(result.value);
        return result.value;
    }

    /* recursively evaluates all possible moves
     * returns score of best outcome
     * stores best move in result */
    private static int run(Game game, Board.Mark self, int depth, Result result) {
        if (game.isOver())
            return score(game, self, depth);

        List<Integer> scores = new ArrayList<>();
        List<Integer> moves = new ArrayList<>();

        for (int position : game.getBoard().getEmpty()) {
            Game copy = new Game(game);
            copy.play(position);
            scores.add(run(copy, self, depth + 1, result));
            moves.add(position);
        }

        int scoreIndex;
        if (game.whoseTurn() == self)
            scoreIndex = scores.indexOf(Collections.max(scores));
        else
            scoreIndex = scores.indexOf(Collections.min(scores));
        result.value = moves.get(scoreIndex);
        return scores.get(scoreIndex);
    }

    private static int score(Game game, Board.Mark mark, int depth) {
        Board.Mark winner = game.getWinner();
        if (mark == winner)
            return MAX_SCORE - depth;
        else if (winner == null)
            return 0;
        else
            return depth - MAX_SCORE;
    }

    private static class Result {
        int value;
    }
}
