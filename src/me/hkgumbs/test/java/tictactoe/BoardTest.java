package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Board;
import me.hkgumbs.main.java.tictactoe.Mark;
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
        board = board.add(0, Mark.X);
        assertFalse(board.empty());
    }

    @Test
    public void emptyToString() {
        assertEquals("---------", board.toString());
    }

    @Test
    public void oneMoveToString() {
        board = board.add(0, Mark.X);
        assertEquals("X--------", board.toString());
    }

    @Test
    public void twoMoveToString() {
        board = board.add(0, Mark.X).add(1, Mark.O);
        assertEquals("XO-------", board.toString());
    }

    @Test
    public void immutableBoard() {
        board = board.add(0, Mark.X);
        assertFalse(board.empty());
    }

    @Test
    public void getTwoMovesByX() {
        board = board.add(0, Mark.X).add(1, Mark.X);
        Set<Integer> movesByX = board.get(Mark.X);
        assertEquals(new HashSet<>(Arrays.asList(0, 1)), movesByX);
    }

    @Test
    public void isNotFull() {
        assertFalse(board.full());
    }

    @Test
    public void isFull() {
        board = board
                .add(0, Mark.X).add(1, Mark.X).add(2, Mark.O)
                .add(3, Mark.O).add(4, Mark.O).add(5, Mark.X)
                .add(6, Mark.X).add(7, Mark.O).add(8, Mark.X);
        assertTrue(board.full());
    }

    @Test
    public void availabilities() {
        assertEquals(9, board.getEmpty().size());
        assertEquals(8, board.add(1, Mark.O).getEmpty().size());
    }

    @Test
    public void prettPrint() {
        assertEquals("   - | - | - \n" +
                        "  -----------  \n" +
                        "   - | - | - \n" +
                        "  -----------  \n" +
                        "   - | - | - \n",
                board.format());
        assertEquals("   X | O | - \n" +
                        "  -----------  \n" +
                        "   - | - | - \n" +
                        "  -----------  \n" +
                        "   - | - | - \n",
                board.add(0, Mark.X).add(1, Mark.O).format());
    }

}