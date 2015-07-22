package me.hkgumbs.tictactoe.test.java.board;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
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
        board = new SquareBoard(3);
    }

    @Test
    public void emptyWhenCreated() {
        assertTrue(board.isEmpty());
    }

    @Test
    public void oneMoveNotEmpty() {
        board = board.add(0, Board.Mark.X);
        assertFalse(board.isEmpty());
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
        assertFalse(board.isEmpty());
    }

    @Test
    public void getTwoMovesByX() {
        board = board.add(0, Board.Mark.X).add(1, Board.Mark.X);
        Set<Integer> movesByX = board.getSpaceIds(Board.Mark.X);
        assertEquals(new HashSet<>(Arrays.asList(0, 1)), movesByX);
    }

    @Test
    public void isNotFull() {
        assertFalse(board.isFull());
    }

    @Test
    public void isFull() {
        board = board
                .add(0, Board.Mark.X).add(1, Board.Mark.X).add(2, Board.Mark.O)
                .add(3, Board.Mark.O).add(4, Board.Mark.O).add(5, Board.Mark.X)
                .add(6, Board.Mark.X).add(7, Board.Mark.O).add(8, Board.Mark.X);
        assertTrue(board.isFull());
    }

    @Test
    public void availabilities() {
        assertEquals(9, board.getEmptySpaceIds().size());
        assertEquals(8, board.add(1, Board.Mark.O).getEmptySpaceIds().size());
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

    @Test
    public void emptyBoardIterable() {
        int count = 0;
        Board board = new SquareBoard(3);
        for (Board.Mark ignored : board)
            count++;
        assertEquals(board.getCapacity(), count);
    }

    @Test
    public void nonEmptyBoardIterable() {
        int xCount = 0;
        int yCount = 0;
        Board board = new SquareBoard(3)
                .add(0, Board.Mark.X).add(1, Board.Mark.O).add(2, Board.Mark.X);
        for (Board.Mark mark : board)
            if (mark == Board.Mark.X)
                xCount++;
            else if (mark == Board.Mark.O)
                yCount++;
        assertEquals(2, xCount);
        assertEquals(1, yCount);
    }

}