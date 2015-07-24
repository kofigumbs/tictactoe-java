package me.hkgumbs.tictactoe.main.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;

public interface SlotRepresentation {
    String compile(Board.Mark mark, int position);

    int getEmptySymbolOffset();

    int getLength();

    void setLength(int length);
}
