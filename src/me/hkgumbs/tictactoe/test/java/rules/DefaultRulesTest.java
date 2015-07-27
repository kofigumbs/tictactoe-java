package me.hkgumbs.tictactoe.test.java.rules;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.formatter.DefaultSlotRepresentation;
import me.hkgumbs.tictactoe.main.java.formatter.SlotRepresentation;
import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.test.java.simulation.StubSimulation;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class DefaultRulesTest {

    Board board;
    Rules rules;
    ByteArrayOutputStream outputStream;
    Simulation simulation;

    @Before
    public void setup() {
        outputStream = new ByteArrayOutputStream();
        board = new SquareBoard(3);
        simulation = new StubSimulation();
        SlotRepresentation slot = new DefaultSlotRepresentation();
        simulation.formatter = new SquareBoardFormatter(3, slot);
        rules = new DefaultRules(3, outputStream, simulation);
    }

    @Test
    public void newGameIsNotOver() {
        assertFalse(rules.gameIsOver(board));
    }


    @Test
    public void determineWinner() {
        board = board
                .add(3, Board.Mark.X).add(4, Board.Mark.X)
                .add(0, Board.Mark.O).add(1, Board.Mark.O).add(2, Board.Mark.O);
        assertEquals(Board.Mark.O, rules.determineWinner(board));
    }

    @Test
    public void playOnceNotOver() {
        assertFalse(rules.gameIsOver(board.add(0, Board.Mark.X)));
    }

    @Test
    public void isActuallyOver() {
        board = board
                .add(0, Board.Mark.X).add(3, Board.Mark.O).add(1, Board.Mark.X)
                .add(4, Board.Mark.O).add(2, Board.Mark.X);
        assertTrue(rules.gameIsOver(board));
    }

    @Test
    public void catsGame() {
        board = board
                .add(0, Board.Mark.X).add(4, Board.Mark.O).add(1, Board.Mark.X)
                .add(2, Board.Mark.O).add(6, Board.Mark.X).add(3, Board.Mark.O)
                .add(5, Board.Mark.X).add(8, Board.Mark.O).add(7, Board.Mark.X);
        assertTrue(rules.gameIsOver(board));
        assertNull(rules.determineWinner(board));
    }

    @Test
    public void validate() {
        assertTrue(rules.validateMove(board, 0));
        assertFalse(rules.validateMove(board, 98));
        assertFalse(rules.validateMove(board.add(0, Board.Mark.O), 0));
    }

    @Test
    public void fourByFourGameOver() {
        board = new SquareBoard(4)
                .add(0, Board.Mark.X).add(1, Board.Mark.X)
                .add(2, Board.Mark.X).add(3, Board.Mark.X);
        rules = new DefaultRules(4, outputStream, simulation);
        assertEquals(Board.Mark.X, rules.determineWinner(board));
    }

    @Test
    public void printInProgressWinnerMessage() {
        rules.printWinnerMessage(board);
        assertTrue(outputStream.toString().endsWith("Game is in progress.\n"));
    }

    @Test
    public void printCompletedWinnerMessage() {
        board = board
                .add(3, Board.Mark.X).add(4, Board.Mark.X)
                .add(0, Board.Mark.O).add(1, Board.Mark.O).add(2, Board.Mark.O);
        rules.printWinnerMessage(board);
        assertTrue(outputStream.toString().endsWith("O wins!\n"));
    }

    @Test
    public void printCatsGameWinnerMessage() {
        board = board
                .add(0, Board.Mark.X).add(4, Board.Mark.O).add(1, Board.Mark.X)
                .add(2, Board.Mark.O).add(6, Board.Mark.X).add(3, Board.Mark.O)
                .add(5, Board.Mark.X).add(8, Board.Mark.O).add(7, Board.Mark.X);

        rules.printWinnerMessage(board);
        assertTrue(outputStream.toString().endsWith("Nobody wins!\n"));
    }
    @Test
    public void allSpacesFilledGameIsWon() {
        board = board
                .add(0, Board.Mark.X).add(1, Board.Mark.X).add(2, Board.Mark.X)
                .add(3, Board.Mark.X).add(4, Board.Mark.X).add(5, Board.Mark.X)
                .add(6, Board.Mark.X).add(7, Board.Mark.X).add(8, Board.Mark.X);
        assertEquals(Board.Mark.X, rules.determineWinner(board));
        assertTrue(rules.gameIsOver(board));
    }
}
