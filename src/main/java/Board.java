package main.java;

import java.util.*;

public class Board {

    public enum Mark {
        X, O;

        Mark other() {
            return this == X ? O : X;
        }
    }

    public static final int CAPACITY = 9;

    private final Mark[] state = new Mark[CAPACITY];

    /* creates deep copy of original board with mark added at position */
    private Board(Board original, int position, Mark mark) {
        for (int i = 0; i < CAPACITY; i++)
            state[i] = original.state[i];
        state[position] = mark;
    }

    public Board() {
    }

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

    /* Caller is responsible to validate(position) beforehand */
    public Board add(int position, Mark mark) {
        return new Board(this, position, mark);
    }

    public boolean empty() {
        return get(null).size() == CAPACITY;
    }

    public boolean full() {
        return get(null).size() == 0;
    }

    public boolean validate(int position) {
        try {
            return state[position] == null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < CAPACITY; i++) {
            if (state[i] == null)
                result.append("-");
            else
                result.append(state[i]);
        }
        return result.toString();
    }
}
