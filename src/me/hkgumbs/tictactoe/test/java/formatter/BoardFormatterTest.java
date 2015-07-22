package me.hkgumbs.tictactoe.test.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardFormatterTest {

    BoardFormatter formatter;

    @Before
    public void setup() {
        formatter = new SquareBoardFormatter(3);
    }

    @Test
    public void emptyThreeByThreeBoard() {
        assertEquals("(0)|(1)|(2)\n" +
                        "-----------\n" +
                        "(3)|(4)|(5)\n" +
                        "-----------\n" +
                        "(6)|(7)|(8)",
                formatter.print(new SquareBoard(3)));
    }

    @Test
    public void threeByThreeBoardWithTwoPieces() {
        Board board = new SquareBoard(3)
                .add(0, Board.Mark.X).add(1, Board.Mark.O);
        assertEquals(" X | O |(2)\n" +
                        "-----------\n" +
                        "(3)|(4)|(5)\n" +
                        "-----------\n" +
                        "(6)|(7)|(8)",
                formatter.print(board));
    }

    @Test
    public void threeByThreeWithPadding() {
        formatter.setPadding(2);
        Board board = new SquareBoard(3)
                .add(0, Board.Mark.X).add(1, Board.Mark.O);
        assertEquals("       |       |       \n" +
                        "       |       |       \n" +
                        "   X   |   O   |  (2)  \n" +
                        "       |       |       \n" +
                        "       |       |       \n" +
                        "-----------------------\n" +
                        "       |       |       \n" +
                        "       |       |       \n" +
                        "  (3)  |  (4)  |  (5)  \n" +
                        "       |       |       \n" +
                        "       |       |       \n" +
                        "-----------------------\n" +
                        "       |       |       \n" +
                        "       |       |       \n" +
                        "  (6)  |  (7)  |  (8)  \n" +
                        "       |       |       \n" +
                        "       |       |       ",
                formatter.print(board));
    }
}
