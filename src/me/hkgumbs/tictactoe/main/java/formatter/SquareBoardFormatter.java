package me.hkgumbs.tictactoe.main.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SquareBoardFormatter implements BoardFormatter {

    private static final String PRETTY = "  %s|%s|%s\n" +
            "  -----------  \n" +
            "  %s|%s|%s\n" +
            "  -----------  \n" +
            "  %s|%s|%s\n";

    public String format(Board board) {
        Object[] marks = new Object[board.getCapacity()];
        int i = 0;
        for (Board.Mark mark : board) {
            if (mark == null)
                marks[i] = "(" + i + ")";
            else
                marks[i] = " " + mark.toString() + " ";
            i++;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        new PrintStream(stream).format(PRETTY, marks);
        return stream.toString();
    }

}
