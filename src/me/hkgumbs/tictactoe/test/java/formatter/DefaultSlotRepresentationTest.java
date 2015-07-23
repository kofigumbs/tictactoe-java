package me.hkgumbs.tictactoe.test.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.formatter.SlotRepresentation;
import me.hkgumbs.tictactoe.main.java.formatter.DefaultSlotRepresentation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultSlotRepresentationTest {

    SlotRepresentation slot;
    
    @Before
    public void setup() {
        slot = new DefaultSlotRepresentation();
    }
    
    @Test
    public void compileSlotAtZero() {
        slot.setLength(1);
        assertEquals("X", slot.compile(Board.Mark.X, 0));
    }

    @Test
    public void compileWithLengthTwo() {
        slot.setLength(2);
        assertEquals(" O", slot.compile(Board.Mark.O, 10));
    }

    @Test
    public void compileWithLengthThree() {
        slot.setLength(3);
        assertEquals(" O ", slot.compile(Board.Mark.O, 10));
    }

    @Test
    public void compileSingleDigitNull() {
        slot.setLength(3);
        assertEquals("(0)", slot.compile(null, 0));
    }

    @Test
    public void compileDoubleDigitNull() {
        slot.setLength(4);
        assertEquals("(10)", slot.compile(null, 10));
    }

    @Test
    public void setLength() {
        slot.setLength(4);
        assertEquals(" (0)", slot.compile(null, 0));
    }
}
