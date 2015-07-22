package me.hkgumbs.tictactoe.test.java;

import me.hkgumbs.tictactoe.main.java.Board;
import me.hkgumbs.tictactoe.main.java.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GameTest {

    Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void newGameIsNotOver() {
        assertFalse(Game.over(board));
    }

    @Test
    public void playOnceNotOver() {
        assertFalse(Game.over(board.add(0, Board.Mark.X)));
    }

    @Test
    public void isActuallyOver() {
        board = board
                .add(0, Board.Mark.X).add(3, Board.Mark.O).add(1, Board.Mark.X)
                .add(4, Board.Mark.O).add(2, Board.Mark.X);
        assertTrue(Game.over(board));
    }

    @Test
    public void catsGame() {
        board = board
                .add(0, Board.Mark.X).add(4, Board.Mark.O).add(1, Board.Mark.X)
                .add(2, Board.Mark.O).add(6, Board.Mark.X).add(3, Board.Mark.O)
                .add(5, Board.Mark.X).add(8, Board.Mark.O).add(7, Board.Mark.X);
        assertTrue(Game.over(board));
        assertNull(Game.winner(board));
    }

    @Test
    public void validate() {
        assertTrue(Game.validate(board, 0));
        assertFalse(Game.validate(board, 98));
        assertFalse(Game.validate(board.add(0, Board.Mark.O), 0));
    }
}
