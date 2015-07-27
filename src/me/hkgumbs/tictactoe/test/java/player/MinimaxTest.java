package me.hkgumbs.tictactoe.test.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinimaxTest {

    Board board;

    private Minimax generate(Board.Mark mark) {
        Minimax minimax = new Minimax(mark, null);
        minimax.setRules(new DefaultRules(3));
        return minimax;
    }

    @Before
    public void setup() {
        board = new SquareBoard(3);
    }

    @Test
    public void testFirstMove() {
        Minimax minimax = generate(Board.Mark.X);
        int result = minimax.determineNextMove(board);
        assertEquals(0, result);
    }

    @Test
    public void blockWinningPlay() {
        board = board
                .add(0, Board.Mark.X).add(4, Board.Mark.O).add(6, Board.Mark.X);
        int result = generate(Board.Mark.O).determineNextMove(board);
        assertEquals(3, result);
    }

    @Test
    public void xMakeWinningPlay() {
        board = board
                .add(0, Board.Mark.X).add(1, Board.Mark.O)
                .add(4, Board.Mark.X).add(5, Board.Mark.O);
        int result = generate(Board.Mark.X).determineNextMove(board);
        assertEquals(8, result);
    }

    @Test
    public void oMakeWinningPlay() {
        board = board
                .add(0, Board.Mark.X).add(2, Board.Mark.O).add(1, Board.Mark.X)
                .add(5, Board.Mark.O).add(3, Board.Mark.X);
        int result = generate(Board.Mark.O).determineNextMove(board);
        assertEquals(8, result);
    }

    @Test
    public void twoSolversTie() {
        boolean xTurn = true;
        Minimax x = generate(Board.Mark.X);
        Minimax o = generate(Board.Mark.O);
        Rules rules = new DefaultRules(3);
        x.setRules(rules);
        o.setRules(rules);
        while (!rules.gameIsOver(board)) {
            Minimax current = xTurn ? x : o;
            int move = current.determineNextMove(board);
            board = board.add(move, current.getMark());
            xTurn = !xTurn;
        }
        assertNull(rules.determineWinner(board));
        assertTrue(board.isFull());
    }
}