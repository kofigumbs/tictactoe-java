package me.hkgumbs.tictactoe.main.java.board;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SquareBoard extends Board {

    protected final Mark[] state;
    private final int size;

    public SquareBoard(int size) {
        this.size = size;
        state = new Mark[size * size];
    }

    /* caller is responsible to enforce overwrites and bounds [0, CAPACITY) */
    public Board add(int position, Mark mark) {
        SquareBoard result = new SquareBoard(3);
        for (int i = 0; i < getCapacity(); i++)
            result.state[i] = state[i];
        result.state[position] = mark;
        return result;
    }

    @Override
    public int getCapacity() {
        return size * size;
    }

    @Override
    public Set<Integer> getEmptySpaceIds() {
        return getSpaceIds(null);
    }

    @Override
    public Set<Integer> getSpaceIds(Mark mark) {
        Set<Integer> result = new HashSet<>();
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
