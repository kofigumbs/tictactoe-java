package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Mark;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkTest {
    @Test
    public void other() {
        assertEquals(Mark.X, Mark.O.other());
        assertEquals(Mark.O, Mark.X.other());
    }
    @Test
    public void valueString() {
        assertEquals(Mark.toString(Mark.O), "O");
        assertEquals(Mark.toString(Mark.X), "X");
    }
    @Test
    public void valuesIteration() {
        int count = 0;
        for (Mark mark : Mark.values())
            if (mark.equals(Mark.X))
                count += 1;
            else if (mark.equals(Mark.O))
                count += 10;
        assertEquals(11, count);
    }
}
