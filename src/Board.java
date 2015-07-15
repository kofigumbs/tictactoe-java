import java.util.*;

public class Board {

    public enum Mark {
        X, O
    }
    public static final int CAPACITY = 9;

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

    public Set<Integer> getMovesBy(Mark mark) {
        return moves.get(mark);
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
