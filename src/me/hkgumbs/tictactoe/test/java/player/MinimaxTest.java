package me.hkgumbs.tictactoe.test.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.player.Algorithm;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinimaxTest {

    Rules defaultRules = new DefaultRules(3);
    Board squareBoard;

    @Before
    public void setup() {
        squareBoard = new SquareBoard(3);
    }

    @Test
    public void emptyBoard() {
        Algorithm minimax = new Minimax(Board.Mark.X, defaultRules);
        assertEquals(0, minimax.run(squareBoard));
    }

    @Test
    public void blockWinningPlay() {
        squareBoard = squareBoard
                .add(0, Board.Mark.X).add(4, Board.Mark.O).add(6, Board.Mark.X);
        Algorithm minimax = new Minimax(Board.Mark.O, defaultRules);
        assertEquals(3, minimax.run(squareBoard));
    }

    @Test
    public void xMakeWinningPlay() {
        squareBoard = squareBoard
                .add(0, Board.Mark.X).add(1, Board.Mark.O)
                .add(4, Board.Mark.X).add(5, Board.Mark.O);
        Algorithm minimax = new Minimax(Board.Mark.X, defaultRules);
        assertEquals(8, minimax.run(squareBoard));
    }

    @Test
    public void oMakeWinningPlay() {
        squareBoard = squareBoard
                .add(0, Board.Mark.X).add(2, Board.Mark.O).add(1, Board.Mark.X)
                .add(5, Board.Mark.O).add(3, Board.Mark.X);
        Algorithm minimax = new Minimax(Board.Mark.O, defaultRules);
        assertEquals(8, minimax.run(squareBoard));
    }

    @Test
    public void twoMinimaxTie() {
        boolean xTurn = true;
        Algorithm x = new Minimax(Board.Mark.X, defaultRules);
        Algorithm o = new Minimax(Board.Mark.O, defaultRules);
        while (!defaultRules.gameIsOver(squareBoard)) {
            Algorithm current = xTurn ? x : o;
            Board.Mark mark = xTurn ? Board.Mark.X : Board.Mark.O;
            int move = current.run(squareBoard);
            squareBoard = squareBoard.add(move, mark);
            xTurn = !xTurn;
        }
        assertNull(defaultRules.determineWinner(squareBoard));
        assertTrue(squareBoard.isFull());
    }

    @Test
    public void fourByFourVeryFast() {
        defaultRules = new DefaultRules(4);
        squareBoard = new SquareBoard(4);
        squareBoard = squareBoard.add(0, Board.Mark.O);
        Algorithm minimax = new Minimax(Board.Mark.X, defaultRules);
        long start = System.currentTimeMillis();
        minimax.run(squareBoard);
        long end = System.currentTimeMillis();
        assertTrue(end - start < 1500l);
    }
}
