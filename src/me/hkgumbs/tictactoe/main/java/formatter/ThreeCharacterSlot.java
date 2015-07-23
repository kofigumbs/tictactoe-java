package me.hkgumbs.tictactoe.main.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;

public class ThreeCharacterSlot implements SlotRepresentation {

    @Override
    public String compile(Board.Mark mark, int position) {
        if (mark != null)
            return " " + mark.toString() + " ";
        else if (position < 10)
            return "(" + Integer.toHexString(position) + ")";
        else
            return "...";
    }

    @Override
    public int length() {
        return 3;
    }
}
