package me.hkgumbs.tictactoe.test.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatterImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardFormatterTest {

    BoardFormatter formatter;

    @Before
    public void setup() {
        formatter = new BoardFormatterImpl();
    }

    @Test
    public void emptyThreeByThreeBoard() {
        assertEquals("  (0)|(1)|(2)\n" +
                        "  -----------  \n" +
                        "  (3)|(4)|(5)\n" +
                        "  -----------  \n" +
                        "  (6)|(7)|(8)\n",
                formatter.format(new SquareBoard(3)));
    }

    @Test
    public void threeByThreeBoardWithTwoPieces() {
        Board board = new SquareBoard(3).add(0, Board.Mark.X).add(1, Board.Mark.O);
        assertEquals("   X | O |(2)\n" +
                        "  -----------  \n" +
                        "  (3)|(4)|(5)\n" +
                        "  -----------  \n" +
                        "  (6)|(7)|(8)\n",
                formatter.format(board));
    }
}
