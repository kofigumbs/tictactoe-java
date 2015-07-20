package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Board;
import me.hkgumbs.main.java.tictactoe.Game;
import me.hkgumbs.main.java.tictactoe.Mark;
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
        assertFalse(Game.over(board.add(0, Mark.X)));
    }

    @Test
    public void isActuallyOver() {
        board = board
                .add(0, Mark.X).add(3, Mark.O).add(1, Mark.X)
                .add(4, Mark.O).add(2, Mark.X);
        assertTrue(Game.over(board));
    }

    @Test
    public void catsGame() {
        board = board
                .add(0, Mark.X) .add(4, Mark.O) .add(1, Mark.X)
                .add(2, Mark.O) .add(6, Mark.X) .add(3, Mark.O)
                .add(5, Mark.X) .add(8, Mark.O) .add(7, Mark.X);
        assertTrue(Game.over(board));
        assertNull(Game.winner(board));
    }

    @Test
    public void validate() {
        assertTrue(Game.validate(board, 0));
        assertFalse(Game.validate(board, 98));
        assertFalse(Game.validate(board.add(0, Mark.O), 0));
    }
}
