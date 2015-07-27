package me.hkgumbs.tictactoe.test.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.player.Human;
import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.test.java.simulation.StubSimulation;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class HumanTest {

    private Board board = new SquareBoard(3);

    private Human birth(String contents) {
        Simulation simulation = new StubSimulation();
        simulation.rules = new DefaultRules(3, null, simulation);
        simulation.formatter = new BoardFormatter() {
            @Override
            public String format(Board board) {
                return null;
            }

            @Override
            public void setPadding(int padding) {
            }

            @Override
            public int getPadding() {
                return 0;
            }
        };

        return new Human(Board.Mark.X,
                new ByteArrayInputStream(contents.getBytes()),
                new ByteArrayOutputStream(), simulation);
    }

    @Test
    public void humanPlayerMoves() {
        Player human = birth("0\n1\n");
        assertEquals(0, human.determineNextMove(board));
        assertEquals(1, human.determineNextMove(board));
    }

    @Test
    public void moveOutOfRange() {
        Player human = birth("-1\n99\n7");
        assertEquals(7, human.determineNextMove(board));
    }

    @Test
    public void parseInvalidMove() {
        Player human = birth("asdf\n7");
        assertEquals(7, human.determineNextMove(board));
    }

    @Test
    public void multiWordResponse() {
        String input = "foo bar y\nn\nasdf asdf\n0\n";
        Human human = birth(input);
        assertEquals(Player.Response.NO, human.requestGoFirst());
        assertEquals(0, human.determineNextMove(board));

        human = birth(input);
        assertEquals(Player.Response.NO, human.requestPlayAgain());
        assertEquals(0, human.determineNextMove(board));
    }

    @Test
    public void parseYesNoUppercase() {
        String input = "asdf\nNO\nYES\n";
        Human human = birth(input);
        assertEquals(Player.Response.NO, human.requestGoFirst());
        assertEquals(Player.Response.YES, human.requestGoFirst());

        human = birth(input);
        assertEquals(Player.Response.NO, human.requestPlayAgain());
        assertEquals(Player.Response.YES, human.requestPlayAgain());
    }

    @Test
    public void parseYNLowercaseWithInvalid() {
        String input = "asdf\nNOPE\ny\nn";
        Human human = birth(input);
        assertEquals(Player.Response.YES, human.requestGoFirst());
        assertEquals(Player.Response.NO, human.requestGoFirst());

        human = birth(input);
        assertEquals(Player.Response.YES, human.requestPlayAgain());
        assertEquals(Player.Response.NO, human.requestPlayAgain());
    }

}
