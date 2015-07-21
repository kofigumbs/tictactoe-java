package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Mark;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MarkTest {
    @Test
    public void other() {
        assertEquals(Mark.X, Mark.O.other());
        assertEquals(Mark.O, Mark.X.other());
    }

    @Test
    public void valueString() {
        assertEquals(Mark.O.toString(), "O");
        assertEquals(Mark.X.toString(), "X");
    }

    @Test
    public void valuesIteration() {
        int count = 0;
        boolean hasX = false;
        boolean hasO = false;
        for (Mark mark : Mark.values()) {
            count++;
            if (mark == Mark.X)
                hasX = true;
            if (mark == Mark.O)
                hasO = true;
        }
        assertEquals(2, count);
        assertTrue(hasX);
        assertTrue(hasO);
    }
}
