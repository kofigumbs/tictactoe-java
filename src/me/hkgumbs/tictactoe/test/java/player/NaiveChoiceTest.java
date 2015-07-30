package me.hkgumbs.tictactoe.test.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.player.Algorithm;
import me.hkgumbs.tictactoe.main.java.player.NaiveChoice;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NaiveChoiceTest {
    @Test
    public void firstMove() {
        assertEquals(0, new NaiveChoice().run(new SquareBoard(3)));
    }

    @Test
    public void secondMove() {
        Board board = new SquareBoard(3);
        Algorithm naive = new NaiveChoice();
        assertEquals(0, naive.run(board.add(1, Board.Mark.X)));
        assertEquals(0, naive.run(board.add(1, Board.Mark.O)));
        assertEquals(1, naive.run(board.add(0, Board.Mark.X)));
    }
}
