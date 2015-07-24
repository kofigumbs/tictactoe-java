package me.hkgumbs.tictactoe.test.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.player.Human;
import me.hkgumbs.tictactoe.main.java.player.Player;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HumanTest {

    private Board board = new SquareBoard(3);

    private Human birth(String contents) {
        return new Human(Board.Mark.X,
                new ByteArrayInputStream(contents.getBytes()),
                new ByteArrayOutputStream());
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
        Human human = birth("foo bar y\nn\nasdf asdf\n0\n");
        assertFalse(human.respondYesOrNo(""));
        assertEquals(0, human.determineNextMove(board));
    }

    @Test
    public void parseYesNoUppercase() {
        Human human = birth("asdf\nNO\nYES");
        assertFalse(human.respondYesOrNo(""));
        assertTrue(human.respondYesOrNo(""));
    }

    @Test
    public void parseYNLowercaseWithInvalid() {
        Human human = birth("asdf\nNOPE\ny\nn");
        assertTrue(human.respondYesOrNo(""));
        assertFalse(human.respondYesOrNo(""));
    }

}
