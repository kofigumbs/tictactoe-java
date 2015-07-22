package me.hkgumbs.tictactoe.test.java;

import me.hkgumbs.tictactoe.main.java.Board;
import me.hkgumbs.tictactoe.main.java.Human;
import me.hkgumbs.tictactoe.main.java.Player;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HumanTest {

    private Board board = new Board();

    private Human birth(String contents) {
        return new Human(Board.Mark.X,
                new ByteArrayInputStream(contents.getBytes()),
                new ByteArrayOutputStream());
    }

    @Test
    public void humanPlayerMoves() {
        Player human = birth("0\n1\n");
        assertEquals(0, human.consider(board));
        assertEquals(1, human.consider(board));
    }

    @Test
    public void moveOutOfRange() {
        Player human = birth("-1\n99\n7");
        assertEquals(7, human.consider(board));
    }

    @Test
    public void parseInvalidMove() {
        Player human = birth("asdf\n7");
        assertEquals(7, human.consider(board));
    }

    @Test
    public void multiWordResponse() {
        Player human = birth("foo bar y\nn\nasdf asdf\n0\n");
        assertFalse(human.yesOrNo());
        assertEquals(0, human.consider(board));
    }

    @Test
    public void parseYesNoUppercase() {
        Player human = birth("asdf\nNO\nYES");
        assertFalse(human.yesOrNo());
        assertTrue(human.yesOrNo());
    }

    @Test
    public void parseYNLowercaseWithInvalid() {
        Player human = birth("asdf\nNOPE\ny\nn");
        assertTrue(human.yesOrNo());
        assertFalse(human.yesOrNo());
    }

}
