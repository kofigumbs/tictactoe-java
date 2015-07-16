import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {

    private static final int MAX_SCORE = 10;

    public static void move(Game game) {
        if (game.lastMove() == null)
            // if board is unplayed, calculations are not worth it
            // just take first slot
            game.play(0);
        else {
            int move = new Minimax(game).getMove();
            game.play(move);
        }
    }

    private static class Minimax {

        Integer result;
        Game game;

        Minimax(Game game) {
            this.game = game;
        }

        public int getMove() {
            run(game.getBoard(), game.whoseTurn(), game.whoseTurn(), 0);
            int result = this.result;
            this.result = null;  // clear old result for safety
            return result;
        }

        private int run(Board board, Board.Mark current, Board.Mark cpu, int depth) {
            if (board.isGameOver()) return score(board, cpu, depth);
            List<Integer> scores = new ArrayList<>();
            List<Integer> moves = new ArrayList<>();

            for (int position : board.getEmpty()) {
                Board possibility = board.add(position, current);
                scores.add(run(possibility, current.other(), cpu, depth + 1));
                moves.add(position);
            }

            if (current.equals(cpu)) {
                int maxScoreIndex = scores.indexOf(Collections.max(scores));
                result = moves.get(maxScoreIndex);
                return scores.get(maxScoreIndex);
            } else {
                int minScoreIndex = scores.indexOf(Collections.min(scores));
                result = moves.get(minScoreIndex);
                return scores.get(minScoreIndex);
            }
        }

        private int score(Board board, Board.Mark mark, int depth) {
            Board.Mark winner = board.getWinner();
            if (mark == winner)
                return MAX_SCORE - depth;
            else if (winner == null)
                return 0;
            else
                return depth - MAX_SCORE;
        }
    }

}
