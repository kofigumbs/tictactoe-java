package me.hkgumbs.tictactoe.test.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardFormatterTest {

    @Test
    public void emptyThreeByThreeBoard() {
        SquareBoardFormatter formatter = new SquareBoardFormatter(3);
        assertEquals("(0)|(1)|(2)\n" +
                        "-----------\n" +
                        "(3)|(4)|(5)\n" +
                        "-----------\n" +
                        "(6)|(7)|(8)",
                formatter.print(new SquareBoard(3)));
    }

    @Test
    public void threeByThreeBoardWithTwoPieces() {
        SquareBoardFormatter formatter = new SquareBoardFormatter(3);
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
    public void threeByThreeWithTwoPadding() {
        SquareBoardFormatter formatter = new SquareBoardFormatter(3);
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

    @Test
    public void fourByFourWithOnePadding() {
        SquareBoardFormatter formatter = new SquareBoardFormatter(4);
        formatter.setPadding(1);
        Board board = new SquareBoard(4)
                .add(0, Board.Mark.X).add(1, Board.Mark.O);
        assertEquals("     |     |     |     \n" +
                        "  X  |  O  | (2) | (3) \n" +
                        "     |     |     |     \n" +
                        "-----------------------\n" +
                        "     |     |     |     \n" +
                        " (4) | (5) | (6) | (7) \n" +
                        "     |     |     |     \n" +
                        "-----------------------\n" +
                        "     |     |     |     \n" +
                        " (8) | (9) | (a) | (b) \n" +
                        "     |     |     |     \n" +
                        "-----------------------\n" +
                        "     |     |     |     \n" +
                        " (c) | (d) | (e) | (f) \n" +
                        "     |     |     |     ",
                formatter.print(board));

    }
}
