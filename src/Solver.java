import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {

    private static final int MAX_SCORE = 10;
    private static final Board.Validator validator = new Board.Validator();

    public static Board move(Board board, Board.Mark mark) {
        if (board.isEmpty())
            // if board is unplayed, calculations are not worth it
            // just take first slot
            return new Board().add(0, Board.Mark.X);
        else {
            MoveScore result = minimax(board, mark, 0);
            return board.add(result.move, mark);
        }
    }

    private static MoveScore minimax(Board board, Board.Mark mark, int depth) {
        if (board.isGameOver()) {
            return new MoveScore(-1, score(board, mark, depth));
        } else {
            List<MoveScore> moveScores = new ArrayList<>();
            for (int i = 0; i < Board.CAPACITY; i++) {
                if (board.isFreeAt(i)) {
                    MoveScore result = minimax(board.add(i, mark), mark, depth + 1);
                    moveScores.add(new MoveScore(i, result.score));
                }
            }
            if (depth % 2 == 1)
                return Collections.max(moveScores);
            else
                return Collections.min(moveScores);
        }
    }

    public static int score(Board board, Board.Mark mark, int depth) {
        Board.Mark winner = board.getWinner();
        if (winner == mark)
            return MAX_SCORE - depth;
        else if (winner == null)
            return 0;
        else
            return depth - MAX_SCORE;

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
                    this.score - o.score : this.move - o.move;
        }

    }
}
