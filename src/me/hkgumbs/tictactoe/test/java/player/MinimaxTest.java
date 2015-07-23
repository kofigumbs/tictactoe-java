package me.hkgumbs.tictactoe.test.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MinimaxTest {

    Board board;

    @Before
    public void setup() {
        board = new SquareBoard(3);
    }

    @Test
    public void testFirstMove() {
        int result = new Minimax(Board.Mark.X).determineNextMove(board);
        assertEquals(0, result);
    }

    @Test
    public void blockWinningPlay() {
        board = board
                .add(0, Board.Mark.X).add(4, Board.Mark.O).add(6, Board.Mark.X);
        int result = new Minimax(Board.Mark.O).determineNextMove(board);
        assertEquals(3, result);
    }

    @Test
    public void xMakeWinningPlay() {
        board = board
                .add(0, Board.Mark.X).add(1, Board.Mark.O)
                .add(4, Board.Mark.X).add(5, Board.Mark.O);
        int result = new Minimax(Board.Mark.X).determineNextMove(board);
        assertEquals(8, result);
    }

    @Test
    public void oMakeWinningPlay() {
        board = board
                .add(0, Board.Mark.X).add(2, Board.Mark.O).add(1, Board.Mark.X)
                .add(5, Board.Mark.O).add(3, Board.Mark.X);
        int result = new Minimax(Board.Mark.O).determineNextMove(board);
        assertEquals(8, result);
    }

    @Test
    public void twoSolversTie() {
        boolean xTurn = true;
        Minimax x = new Minimax(Board.Mark.X);
        Minimax o = new Minimax(Board.Mark.O);
        while (!Rules.gameIsOver(board)) {
            Minimax current = xTurn ? x : o;
            int move = current.determineNextMove(board);
            board = board.add(move, current.getMark());
            xTurn = !xTurn;
        }
        assertNull(Rules.determineWinner(board));
        assertTrue(board.isFull());
    }
}
