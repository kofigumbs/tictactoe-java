package me.hkgumbs.tictactoe.main.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class SquareBoardFormatter implements BoardFormatter {

    private static final String HORIZONTAL_DIVIDER_UNIT = "-";
    private static final String VERTICAL_DIVIDER_UNIT = "|";

    private String format;
    private int dimension;
    private int padding;
    private SlotRepresentation slot;

    private void fillHorizontalPadding(String[] rows, String center) {
        for (int i = 0; i < rows.length; i++) {
            String[] slots = new String[dimension];
            fillSlotPadding(slots, center);
            rows[i] = String.join(VERTICAL_DIVIDER_UNIT, slots);
        }
    }

    private void fillSlotPadding(String[] slots, String center) {
        Arrays.fill(slots, getPad() + center + getPad());
    }

    private void fillVerticalPadding(String[] rows) {
        for (int i = 0; i < dimension; i++) {
            String[] rowWithPadding = new String[padding * 2 + 1];
            String center = getSpaces(slot.getLength());
            fillHorizontalPadding(rowWithPadding, center);
            rowWithPadding[padding] = rows[i];
            rows[i] = String.join("\n", rowWithPadding);
        }
    }

    private void generateFormat() {
        generateSlotLength();
        String[] rows = new String[dimension];
        String divider = getHorizontalDivider();
        fillHorizontalPadding(rows, "%s");
        fillVerticalPadding(rows);
        format = String.join(divider, rows);
    }

    private void generateSlotLength() {
        int area = dimension * dimension;
        int digits = Integer.toString(area).length();
        int length = digits + slot.getEmptySymbolOffset();
        if (length > slot.getLength())
            slot.setLength(length);
    }

    private int getDividerLength() {
        return (padding * 2 + slot.getLength()) * dimension + dimension - 1;
    }

    private String getSpaces(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++)
            builder.append(" ");
        return builder.toString();
    }

    private String getHorizontalDivider() {
        int length = getDividerLength();
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = 0; i < length; i++)
            builder.append(HORIZONTAL_DIVIDER_UNIT);
        builder.append("\n");
        return builder.toString();
    }

    private String getPad() {
        return getSpaces(padding);
    }

    public SquareBoardFormatter(int dimension, SlotRepresentation slot) {
        this.dimension = dimension;
        this.slot = slot;
        padding = 0;
        generateFormat();
    }

    @Override
    public String print(Board board) {
        Object[] marks = new Object[board.getCapacity()];
        int i = 0;
        for (Board.Mark mark : board) {
            marks[i] = slot.compile(mark, i);
            i++;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        new PrintStream(stream).format(format, marks);
        return stream.toString();
    }

    @Override
    public void setPadding(int padding) {
        this.padding = padding;
        generateFormat();
    }
}
