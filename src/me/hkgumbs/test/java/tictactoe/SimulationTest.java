package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Simulation;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulationTest {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private ByteArrayInputStream inputStream(String contents) {
        return new ByteArrayInputStream(contents.getBytes());
    }

    @Before
    public void setup() {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void newSimulationBoardEmpty() {
        assertTrue(new Simulation(inputStream(""), outputStream).getBoard()
                .empty());
    }

    @Test
    public void userMoves() {
        ByteArrayInputStream inputStream = inputStream("0\n1\n");
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.consumeMove();
        assertEquals("X--------", simulation.getBoard().toString());
        simulation.consumeMove();
        assertEquals("XO-------", simulation.getBoard().toString());
        assertEquals(-1, inputStream.read());
    }

    @Test
    public void fullSimulation() {
        ByteArrayInputStream inputStream = inputStream("y\n0\n2\n5\n");
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.start();
        assertEquals("XOX-OX-O-", simulation.getBoard().toString());
        assertEquals(-1, inputStream.read());
    }

    @Test
    public void moveOutOfRange() {
        ByteArrayInputStream inputStream = inputStream("-1\n99\n4");
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.consumeMove();
        assertEquals("----X----", simulation.getBoard().toString());
        assertEquals(-1, inputStream.read());
    }

    @Test
    public void moveOnOccupiedSpace() {
        ByteArrayInputStream inputStream = inputStream("4\n4\n5\n");
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.consumeMove();
        simulation.consumeMove();
        assertEquals("----XO---", simulation.getBoard().toString());
        assertEquals(-1, inputStream.read());
    }

}
