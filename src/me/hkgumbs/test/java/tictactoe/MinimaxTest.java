package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Board;
import me.hkgumbs.main.java.tictactoe.Game;
import me.hkgumbs.main.java.tictactoe.Mark;
import me.hkgumbs.main.java.tictactoe.Minimax;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


//
//import me.hkgumbs.main.java.tictactoe.Game;
//import me.hkgumbs.main.java.tictactoe.Minimax;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
public class MinimaxTest {

    Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void testFirstMove() {
        int result = new Minimax(Mark.X).run(board);
        assertEquals(0, result);
    }

    @Test
    public void blockWinningPlay() {
        board = board
                .add(0, Mark.X).add(4, Mark.O).add(6, Mark.X);
        int result = new Minimax(Mark.O).run(board);
        assertEquals(3, result);
    }

    @Test
    public void xMakeWinningPlay() {
        board = board
                .add(0, Mark.X).add(1, Mark.O).add(4, Mark.X).add(5, Mark.O);
        int result = new Minimax(Mark.X).run(board);
        assertEquals(8, result);
    }

    @Test
    public void oMakeWinningPlay() {
        board = board
                .add(0, Mark.X).add(2, Mark.O).add(1, Mark.X)
                .add(5, Mark.O).add(3, Mark.X);
        int result = new Minimax(Mark.O).run(board);
        assertEquals(8, result);
    }

    @Test
    public void twoSolversTie() {
        boolean xTurn = true;
        Minimax x = new Minimax(Mark.X);
        Minimax o = new Minimax(Mark.O);
        while (!Game.over(board)) {
            Minimax current = xTurn ? x : o;
            int move = current.run(board);
            board = board.add(move, current.mark);
            xTurn = !xTurn;
        }
        assertNull(Game.winner(board));
        assertTrue(board.full());
    }
}
