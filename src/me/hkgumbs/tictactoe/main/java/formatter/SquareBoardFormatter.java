package me.hkgumbs.tictactoe.main.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class SquareBoardFormatter implements BoardFormatter {

    private String format;
    private int dimension;
    private int padding;

    private String createFormat() {
        String[] rows = new String[dimension];
        String divider = getHorizontalDivider();
        fillHorizontalPadding(rows, "%s");
        fillVerticalPadding(rows);
        return String.join(divider, rows);
    }

    private void fillHorizontalPadding(String[] rows, String center) {
        for (int i = 0; i < rows.length; i++) {
            String[] slots = new String[dimension];
            fillSlotPadding(slots, center);
            rows[i] = String.join("|", slots);
        }
    }

    private void fillSlotPadding(String[] slots, String center) {
        Arrays.fill(slots, getPad() + center + getPad());
    }

    private void fillVerticalPadding(String[] rows) {
        for (int i = 0; i < dimension; i++) {
            String[] rowWithPadding = new String[padding * 2 + 1];
            fillHorizontalPadding(rowWithPadding, "   ");
            rowWithPadding[padding] = rows[i];
            rows[i] = String.join("\n", rowWithPadding);
        }
    }

    private int getDividerLength() {
        return (dimension + padding * 2) * dimension + dimension - 1;
    }

    private String getHorizontalDivider() {
        int length = getDividerLength();
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = 0; i < length; i++)
            builder.append("-");
        builder.append("\n");
        return builder.toString();
    }

    private String getPad() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < padding; i++)
            builder.append(" ");
        return builder.toString();
    }

    private static String getSlotRepresentation(Board.Mark mark, int position) {
        if (mark == null)
            return "(" + position + ")";
        else
            return " " + mark.toString() + " ";
    }

    public SquareBoardFormatter(int dimension) {
        this.dimension = dimension;
        padding = 0;
        format = createFormat();
    }

    @Override
    public String print(Board board) {
        Object[] marks = new Object[board.getCapacity()];
        int i = 0;
        for (Board.Mark mark : board) {
            marks[i] = getSlotRepresentation(mark, i);
            i++;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        new PrintStream(stream).format(format, marks);
        return stream.toString();
    }

    @Override
    public void setPadding(int padding) {
        this.padding = padding;
        format = createFormat();
    }
}
