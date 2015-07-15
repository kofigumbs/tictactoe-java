import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solver {

    private static final int MAX_SCORE = 10;
    private static final Validator validator = new Validator();

    public static Board move(Board board, Board.Mark mark) {
        if (board.isEmpty())
            // if board is unplayed, calculations are not worth it
            // just take first slot
            return new Board().add(0, Board.Mark.X);
        else
            return board.add(minimax(board, Board.Mark.O, 0).move, Board.Mark.O);
    }

    private static MoveScore minimax(Board board, Board.Mark mark, int depth) {
        if (validator.gameOver(board)) {
            return new MoveScore(-1, score(board, mark, depth));
        } else {
            List<MoveScore> moveScores = Arrays.asList();
            for (int i = 0; i < Board.CAPACITY; i++) {
                if (board.isFreeAt(i))
                    moveScores.add(minimax(board.add(i, mark), mark, depth + 1));
            }
            return Collections.max(moveScores);
        }
    }

    public static int score(Board board, Board.Mark mark, int depth) {
        Board.Mark winner = validator.winner(board);
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
