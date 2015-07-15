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
    private final Map<Mark, Set<Integer>> moves = new HashMap<>();

    Board() {
        for (Mark mark : Mark.values())
            moves.put(mark, new TreeSet<>());
    }

    private Board(Board old, int position, Mark mark) {
        for (int i = 0; i < CAPACITY; i++)
            state[i] = old.state[i];
        state[position] = mark;

        for (Mark oldMark : old.moves.keySet()) {
            moves.put(oldMark, new TreeSet<>());
            for (Integer oldPosition : old.moves.get(oldMark))
                moves.get(oldMark).add(oldPosition);
        }
    }

    public int numberOfAvailabilities() {
        int spaces = 0;
        for (int ignored : availabilities())
            spaces++;
        return spaces;
    }

    public Board add(int position, Mark mark) {
        moves.get(mark).add(position);
        return new Board(this, position, mark);
    }

    public boolean empty() {
        return numberOfAvailabilities() == CAPACITY;
    }

    public boolean full() {
        return numberOfAvailabilities() == 0;
    }

    public Iterable<Integer> availabilities() {
        return () -> new Iterator<Integer>() {
            int position = 0;

            @Override
            public boolean hasNext() {
                while (position < CAPACITY && state[position] != null)
                    position++;
                return position < CAPACITY;
            }

            @Override
            public Integer next() {
                return position++;
            }
        };
    }

    public boolean isGameOver()  {
        return full() || getWinner() != null;
    }

    public Set<Integer> get(Mark mark) {
        return moves.get(mark);
    }

    public Board.Mark getWinner() {
        for (Board.Mark mark : Board.Mark.values())
            for (List winningCombination : WINNING_COMBINGATIONS)
                if (get(mark).containsAll(winningCombination))
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
