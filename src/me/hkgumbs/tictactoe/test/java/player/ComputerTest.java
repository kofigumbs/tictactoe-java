package me.hkgumbs.tictactoe.test.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.player.Algorithm;
import me.hkgumbs.tictactoe.main.java.player.Computer;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ComputerTest {

    Board board;
    OutputStream outputStream;

    private class MockAlgorithm implements Algorithm {
        @Override
        public int run(Board board) {
            return board.isEmpty() ? 0 : 5;
        }
    }

    private Computer generate(Board.Mark mark) {
        board = new SquareBoard(3);
        outputStream = new ByteArrayOutputStream();
        Computer computer = new Computer(
                mark, outputStream, new MockAlgorithm());
        return computer;
    }

    @Test
    public void firstMove() {
        Computer computer = generate(Board.Mark.X);
        int result = computer.determineNextMove(board);
        assertEquals(0, result);
        assertEquals(outputStream.toString(), "X >> 0\n");
    }

    @Test
    public void secondMove() {
        Computer computer = generate(Board.Mark.O);
        int result = computer.determineNextMove(board.add(0, Board.Mark.X));
        assertEquals(5, result);
        assertEquals(outputStream.toString(), "O >> 5\n");
    }

    @Test
    public void allRequestsFalse() {
        Computer x = generate(Board.Mark.X);
        assertFalse(x.requestGoFirst());
        assertFalse(x.requestPlayAgain());
    }

}
