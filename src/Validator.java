import java.util.Arrays;
import java.util.List;

public class Validator {

    private static final List[] WINNING_COMBINGATIONS = new List[]{
            Arrays.asList(0, 1, 2), Arrays.asList(3, 4, 5),
            Arrays.asList(6, 7, 8), Arrays.asList(0, 4, 8),
            Arrays.asList(0, 3, 6), Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8), Arrays.asList(2, 4, 6)
    };

    public boolean checkMove(Board board, int position, Board.Mark mark) {
        return board.isFreeAt(position);
    }

    public boolean gameOver(Board board) {
        return board.isFull() || winner(board) != null;
    }

    public Board.Mark winner(Board board) {
        for (Board.Mark mark : Board.Mark.values())
            for (List winningCombination : WINNING_COMBINGATIONS)
                if (board.getMovesBy(mark).containsAll(winningCombination))
                    return mark;
        return null;
    }
}
