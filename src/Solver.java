import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {

    private static final int MAX_SCORE = 10;
    private static int result;

    public static void move(Game game) {
        if (game.lastMove() == null)
            // if board is unplayed, calculations are not worth it
            // just take first slot
            game.play(0);
        else {
            minimax(game.getBoard(), game.whoseTurn(), game.whoseTurn());
            game.play(result);
        }
    }

    private static int score(Board board, Board.Mark mark) {
        Board.Mark winner = board.getWinner();
        if (winner == mark)
            return 10;
        else if (winner == null)
            return 0;
        else
            return -10;
    }

    private static int minimax(Board board, Board.Mark current, Board.Mark cpu) {
        if (board.isGameOver()) return score(board, cpu);
        List<Integer> scores = new ArrayList<>();
        List<Integer> moves = new ArrayList<>();

        for (int i : board.availabilities()) {
            Board possibility = board.add(i, current);
            scores.add(minimax(possibility, current.other(), cpu));
            moves.add(i);
        }

        if (current != cpu) {
            int maxScoreIndex = scores.indexOf(Collections.max(scores));
            result = moves.get(maxScoreIndex);
            return scores.get(maxScoreIndex);
        } else {
            int minScoreIndex = scores.indexOf(Collections.min(scores));
            result = moves.get(minScoreIndex);
            return scores.get(minScoreIndex);
        }
    }

    public static class MoveScore implements Comparable<MoveScore> {
        final int move;
        final int score;

        MoveScore(int move, int score) {
            this.move = move;
            this.score = score;
        }

        @Override
        public int compareTo(MoveScore o) {
            return this.score != o.score ?
                    this.score - o.score : o.move - this.move;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof MoveScore
                    && move == ((MoveScore)obj).move
                    && score == ((MoveScore)obj).score;
        }
    }
}
