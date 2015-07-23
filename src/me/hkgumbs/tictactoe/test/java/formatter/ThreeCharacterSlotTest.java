package me.hkgumbs.tictactoe.test.java.formatter;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.formatter.SlotRepresentation;
import me.hkgumbs.tictactoe.main.java.formatter.ThreeCharacterSlot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThreeCharacterSlotTest {

    SlotRepresentation slot;
    
    @Before
    public void setup() {
        slot = new ThreeCharacterSlot();
    }
    
    @Test
    public void compileSlotAtZero() {
        assertEquals(" X ", slot.compile(Board.Mark.X, 0));
    }

    @Test
    public void compileDoubleDigitO() {
        assertEquals(" O ", slot.compile(Board.Mark.O, 10));
    }

    @Test
    public void compileSingleDigitNull() {
        assertEquals("(0)", slot.compile(null, 0));
    }

    @Test
    public void compileDoubleDigitNull() {
        assertEquals("...", slot.compile(null, 10));
    }

    @Test
    public void length() {
        assertEquals(3, slot.length());
    }
}
