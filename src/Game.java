import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Game {

    private static final List[] WINNING_COMBINATIONS = new List[]{
            Arrays.asList(0, 1, 2), Arrays.asList(3, 4, 5),
            Arrays.asList(6, 7, 8), Arrays.asList(0, 4, 8),
            Arrays.asList(0, 3, 6), Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8), Arrays.asList(2, 4, 6)
    };

    private Board.Mark next = Board.Mark.X;
    private Stack<Integer> history = new Stack<>();
    private Board board = new Board();

    public boolean isOver() {
        return board.full() || board.getWinner() != null;
    }

    public void play(int position) {
        history.push(position);
        board = board.add(position, next);
        next = next.other();
    }

    public String lastMove() {
        if (history.isEmpty())
            return null;
        return next.other() + " >> " + history.peek();
    }

    public Board getBoard() {
        return board;
    }

    public Board.Mark getWinner() {
        for (Board.Mark mark : Board.Mark.values())
            for (List winningCombination : WINNING_COMBINATIONS)
                if (board.get(mark).containsAll(winningCombination))
                    return mark;
        return null;
    }


    public Board.Mark whoseTurn() {
        return next;
    }
}
