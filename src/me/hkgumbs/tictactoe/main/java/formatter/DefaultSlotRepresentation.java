package me.hkgumbs.tictactoe.main.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;

public class DefaultSlotRepresentation implements SlotRepresentation {

    private final static String EMPTY_SYMBOL_LEFT = "(";
    private final static String EMPTY_SYMBOL_RIGHT = ")";
    private int length = 3;

    private String createRepresentation(Board.Mark mark, int position) {
        if (mark != null)
            return mark.toString();
        else
            return EMPTY_SYMBOL_LEFT + position + EMPTY_SYMBOL_RIGHT;
    }

    private String pad(String representation) {
        boolean prepend = true;
        for (int i = length - representation.length(); i > 0; i--) {
            if (prepend)
                representation = " " + representation;
            else
                representation = representation + " ";
            prepend = !prepend;
        }
        return representation;
    }

    @Override
    public String compile(Board.Mark mark, int position) {
        String representation = createRepresentation(mark, position);
        return pad(representation);
    }

    @Override
    public int getEmptySymbolOffset() {
        return (EMPTY_SYMBOL_LEFT + EMPTY_SYMBOL_RIGHT).length();
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }
}
