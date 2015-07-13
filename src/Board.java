public class Board {

    private final int SIZE = 3;
    private final String EMPTY_MARK = "-";

    private boolean empty = true;
    private String[][] state = new String[SIZE][SIZE];

    Board() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                state[i][j] = EMPTY_MARK;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Board add(int row, int column, String mark) {
        Board copy = new Board();
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                copy.state[i][j] = state[i][j];
        copy.state[row][column] = mark;
        copy.empty = false;
        return copy;
    }

    public boolean isFreeAt(int row, int column) {
        return state[row][column].equals(EMPTY_MARK);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                result.append(state[i][j]);
            result.append("\n");
        }
        result.setLength(11);
        result.trimToSize();
        return result.toString();
    }

}
