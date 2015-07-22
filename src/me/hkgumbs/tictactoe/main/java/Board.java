package me.hkgumbs.tictactoe.main.java;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Board {

    public enum Mark {
        O, X;

        public Mark other() {
            return this == X ? O : X;
        }
    }

    public static final int CAPACITY = 9;

    private static final String PRETTY = "  %s|%s|%s\n" +
            "  -----------  \n" +
            "  %s|%s|%s\n" +
            "  -----------  \n" +
            "  %s|%s|%s\n";

    private final Mark[] state = new Mark[CAPACITY];

    public Set<Integer> get(Mark mark) {
        Set<Integer> result = new HashSet<>();
        for (int position = 0; position < state.length; position++)
            if (state[position] == mark)
                result.add(position);
        return result;
    }

    /* caller is responsible to enforce overwrites and bounds [0, CAPACITY) */
    public Board add(int position, Mark mark) {
        Board result = new Board();
        for (int i = 0; i < CAPACITY; i++)
            result.state[i] = state[i];
        result.state[position] = mark;
        return result;
    }

    public Set<Integer> getEmpty() {
        return get(null);
    }

    public boolean empty() {
        return get(null).size() == CAPACITY;
    }

    public boolean full() {
        return get(null).size() == 0;
    }

    public String format() {
        Object[] marks = new Object[Board.CAPACITY];
        for (int i = 0; i < marks.length; i++)
            if (state[i] == null)
                marks[i] = "(" + i + ")";
            else
                marks[i] = " " + state[i].toString() + " ";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        new PrintStream(stream).format(PRETTY, marks);
        return stream.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Mark mark : state)
            result.append(mark == null ? "-" : mark.toString());
        return result.toString();
    }
}
