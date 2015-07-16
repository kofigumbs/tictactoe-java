import java.util.*;

public class Board {

    public enum Mark {
        X, O;
        Mark other() {
            return this == X ? O : X;
        }
    }

    public static final int CAPACITY = 9;
    private static final List[] WINNING_COMBINGATIONS = new List[]{
            Arrays.asList(0, 1, 2), Arrays.asList(3, 4, 5),
            Arrays.asList(6, 7, 8), Arrays.asList(0, 4, 8),
            Arrays.asList(0, 3, 6), Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8), Arrays.asList(2, 4, 6)
    };

    private final Mark[] state = new Mark[CAPACITY];

    private Board(Board old, int position, Mark mark) {
        for (int i = 0; i < CAPACITY; i++)
            state[i] = old.state[i];
        state[position] = mark;
    }

    public Board(){}

    public Set<Integer> getEmpty() {
        return get(null);
    }

    public Set<Integer> get(Board.Mark mark) {
        Set<Integer> result = new HashSet<>();
        for (int position = 0; position < state.length; position++)
            if (state[position] == mark)
                result.add(position);
        return result;
    }

    public Board add(int position, Mark mark) {
        return new Board(this, position, mark);
    }

    public boolean empty() {
        return get(null).size() == CAPACITY;
    }

    public boolean full() {
        return get(null).size() == 0;
    }

    public boolean isGameOver()  {
        return full() || getWinner() != null;
    }

    public Board.Mark getWinner() {
        for (Board.Mark mark : Board.Mark.values())
            for (List winningCombination : WINNING_COMBINGATIONS) {
                Set<Integer> combo = get(mark);
                if (combo.containsAll(winningCombination))
                    return mark;
            }
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
