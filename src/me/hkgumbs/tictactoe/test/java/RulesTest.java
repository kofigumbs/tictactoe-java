package me.hkgumbs.tictactoe.test.java;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RulesTest {

    Board board;

    @Before
    public void setup() {
        board = new SquareBoard(3);
    }

    @Test
    public void newGameIsNotOver() {
        assertFalse(Rules.gameIsOver(board));
    }

    @Test
    public void playOnceNotOver() {
        assertFalse(Rules.gameIsOver(board.add(0, Board.Mark.X)));
    }

    @Test
    public void isActuallyOver() {
        board = board
                .add(0, Board.Mark.X).add(3, Board.Mark.O).add(1, Board.Mark.X)
                .add(4, Board.Mark.O).add(2, Board.Mark.X);
        assertTrue(Rules.gameIsOver(board));
    }

    @Test
    public void catsGame() {
        board = board
                .add(0, Board.Mark.X).add(4, Board.Mark.O).add(1, Board.Mark.X)
                .add(2, Board.Mark.O).add(6, Board.Mark.X).add(3, Board.Mark.O)
                .add(5, Board.Mark.X).add(8, Board.Mark.O).add(7, Board.Mark.X);
        assertTrue(Rules.gameIsOver(board));
        assertNull(Rules.determineWinner(board));
    }

    @Test
    public void validate() {
        assertTrue(Rules.validate(board, 0));
        assertFalse(Rules.validate(board, 98));
        assertFalse(Rules.validate(board.add(0, Board.Mark.O), 0));
    }
}
