package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Board;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void emptyWhenCreated() {
        assertTrue(board.empty());
    }

    @Test
    public void oneMoveNotEmpty() {
        board = board.add(0, Board.Mark.X);
        assertFalse(board.empty());
    }

    @Test
    public void emptyToString() {
        assertEquals("---------", board.toString());
    }

    @Test
    public void oneMoveToString() {
        board = board.add(0, Board.Mark.X);
        assertEquals("X--------", board.toString());
    }

    @Test
    public void twoMoveToString() {
        board = board.add(0, Board.Mark.X).add(1, Board.Mark.O);
        assertEquals("XO-------", board.toString());
    }

    @Test
    public void immutableBoard() {
        board = board.add(0, Board.Mark.X);
        assertFalse(board.empty());
    }

    @Test
    public void getTwoMovesByX() {
        board = board.add(0, Board.Mark.X).add(1, Board.Mark.X);
        Set<Integer> movesByX = board.get(Board.Mark.X);
        assertEquals(new HashSet<>(Arrays.asList(0, 1)), movesByX);
    }

    @Test
    public void isNotFull() {
        assertFalse(board.full());
    }

    @Test
    public void isFull() {
        board = board
                .add(0, Board.Mark.X).add(1, Board.Mark.X).add(2, Board.Mark.O)
                .add(3, Board.Mark.O).add(4, Board.Mark.O).add(5, Board.Mark.X)
                .add(6, Board.Mark.X).add(7, Board.Mark.O).add(8, Board.Mark.X);
        assertTrue(board.full());
    }

    @Test
    public void availabilities() {
        assertEquals(9, board.getEmpty().size());
        assertEquals(8, board.add(1, Board.Mark.O).getEmpty().size());
    }

    @Test
    public void prettyPrint() {
        assertEquals("  (0)|(1)|(2)\n" +
                        "  -----------  \n" +
                        "  (3)|(4)|(5)\n" +
                        "  -----------  \n" +
                        "  (6)|(7)|(8)\n",
                board.format());
        assertEquals("   X | O |(2)\n" +
                        "  -----------  \n" +
                        "  (3)|(4)|(5)\n" +
                        "  -----------  \n" +
                        "  (6)|(7)|(8)\n",
                board.add(0, Board.Mark.X).add(1, Board.Mark.O).format());
    }

    @Test
    public void markOther() {
        assertEquals(Board.Mark.X, Board.Mark.O.other());
        assertEquals(Board.Mark.O, Board.Mark.X.other());
    }

    @Test
    public void markString() {
        assertEquals(Board.Mark.O.toString(), "O");
        assertEquals(Board.Mark.X.toString(), "X");
    }


}