package me.hkgumbs.tictactoe.main.java.board;

import java.util.*;

public class SquareBoard implements Board {

    protected final Mark[] state;
    private final int capacity;
    private final int dimension;

    public SquareBoard(int dimension) {
        this.dimension = dimension;
        capacity = dimension * dimension;
        state = new Mark[capacity];
    }

    /* caller is responsible to enforce overwrites and bounds
     * throws IndexOutOfBoundsException if position out of bounds */
    public Board add(int position, Mark mark) {

        SquareBoard result = new SquareBoard(dimension);
        for (int i = 0; i < getCapacity(); i++)
            result.state[i] = state[i];
        result.state[position] = mark;
        return result;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public List<Integer> getEmptySpaceIds() {
        return getSpaceIds(null);
    }

    @Override
    public List<Integer> getSpaceIds(Mark mark) {
        List<Integer> result = new LinkedList<>();
        for (int position = 0; position < state.length; position++)
            if (state[position] == mark)
                result.add(position);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Mark mark : state)
            result.append(mark == null ? "-" : mark.toString());
        return result.toString();
    }

    @Override
    public Iterator<Mark> iterator() {
        return new Iterator<Mark>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return current < state.length;
            }

            @Override
            public Mark next() {
                return state[current++];
            }
        };
    }
}
