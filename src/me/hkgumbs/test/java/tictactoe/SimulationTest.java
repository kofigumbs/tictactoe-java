package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Simulation;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class SimulationTest {

    String prompt = "";

    private Simulation simlulate(String contents) {
        return new Simulation(
                new ByteArrayInputStream(contents.getBytes()),
                new ByteArrayOutputStream());
    }

    @Test
    public void userMoves() {
        Simulation simulation = simlulate("0\n1\n");
        assertEquals(0, simulation.parseValidMove(prompt));
        assertEquals(1, simulation.parseValidMove(prompt));
    }

    @Test
    public void moveOutOfRange() {
        Simulation simulation = simlulate("-1\n99\n0");
        assertEquals(0, simulation.parseValidMove(prompt));;
    }

    @Test
    public void parseInvalidMove() {
        Simulation simulation = simlulate("asdf\n0\n");
        assertEquals(0, simulation.parseValidMove(prompt));
    }

    @Test
    public void parseYesNoUppercase() {
        Simulation simulation = simlulate("asdf\nNO\nYES");
        assertFalse(simulation.parseYesOrNo(prompt));
        assertTrue(simulation.parseYesOrNo(prompt));
    }

    @Test
    public void parseYNLowercaseWithInvalid() {
        Simulation simulation = simlulate("asdf\nNOPE\ny\nn");
        assertTrue(simulation.parseYesOrNo(prompt));
        assertFalse(simulation.parseYesOrNo(prompt));
    }
    @Test
    public void multiWordResponse() {
        Simulation simulation = simlulate("foo bar y\nn\nasdf asdf\n0\n");
        assertFalse(simulation.parseYesOrNo(prompt));
        assertEquals(0, simulation.parseValidMove(prompt));
    }

}
