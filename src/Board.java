import java.util.*;

public class Board {

    public enum Mark {
        X, O
    }

    public static final int CAPACITY = 9;
    private static final List[] WINNING_COMBINGATIONS = new List[]{
            Arrays.asList(0, 1, 2), Arrays.asList(3, 4, 5),
            Arrays.asList(6, 7, 8), Arrays.asList(0, 4, 8),
            Arrays.asList(0, 3, 6), Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8), Arrays.asList(2, 4, 6)
    };

    private final int size;
    private final Mark[] state = new Mark[CAPACITY];
    private final Map<Mark, Set<Integer>> moves = new HashMap<>();

    Board() {
        size = 0;
        for (Mark mark : Mark.values())
            moves.put(mark, new TreeSet<>());
    }

    private Board(Board old, int position, Mark mark) {
        for (int i = 0; i < CAPACITY; i++)
            state[i] = old.state[i];
        state[position] = mark;
        size = old.size + 1;

        for (Mark oldMark : old.moves.keySet()) {
            moves.put(oldMark, new TreeSet<>());
            for (Integer oldPosition : old.moves.get(oldMark))
                moves.get(oldMark).add(oldPosition);
        }
    }

    public Board add(int position, Mark mark) {
        moves.get(mark).add(position);
        return new Board(this, position, mark);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == CAPACITY;
    }

    public boolean isFreeAt(int position) {
        return state[position] == null;
    }

    public boolean isGameOver()  {
        return isFull() || getWinner() != null;
    }

    public Set<Integer> getMovesBy(Mark mark) {
        return moves.get(mark);
    }

    public Board.Mark getWinner() {
        for (Board.Mark mark : Board.Mark.values())
            for (List winningCombination : WINNING_COMBINGATIONS)
                if (getMovesBy(mark).containsAll(winningCombination))
                    return mark;
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int dimension = (int) Math.sqrt(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            if (state[i] == null)
                result.append("-");
            else
                result.append(state[i]);

            if (i % dimension == dimension - 1)
                result.append("\n");
        }
        result.setLength(CAPACITY + dimension - 1);
        result.trimToSize();
        return result.toString();
    }
}
