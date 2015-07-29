package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultController;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import org.junit.Test;

import java.io.*;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DefaultControllerTest {

    private static final String HUMAN_ERROR =
            "Human v human simulation should be valid input";

    Simulation simulation;
    OutputStream outputStream;
    InputStream inputStream;
    DefaultController controller;

    private void assertInputStreamEmpty() {
        try {
            assertEquals(-1, inputStream.read());
        } catch (IOException e) {
            fail("Input stream should be empty");
        }
    }

    private void saveSimulation(String input)
            throws Configuration.CannotApplyException {
        String[] args = {"human", "human"};
        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream(input.getBytes());
        controller = new DefaultController(inputStream, outputStream, args);
        simulation = controller.getSimulation();
    }

    private void saveDefaultSimulation() {
        try {
            saveSimulation("");
        } catch (Configuration.CannotApplyException e) {
            fail(HUMAN_ERROR);
        }
    }

    @Test
    public void defaultSimulationSetup() {
        saveDefaultSimulation();
        assertEquals(3, simulation.size);
        assertTrue(simulation.formatter instanceof SquareBoardFormatter);
        assertTrue(simulation.rules instanceof DefaultRules);
    }

    @Test(expected = Configuration.CannotApplyException.class)
    public void throwsExceptionOnNonsenseOption()
            throws Configuration.CannotApplyException {
        String[] args = {"--nonsense", "human", "human"};
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        new DefaultController(inputStream, outputStream, args);
        fail("Should fail because of nonsense option");
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationWhenSwappingSize() {
        saveDefaultSimulation();
        simulation.size = 4;
        controller.startSimulation();
        fail("Simulation should be uneditable once initialized");
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationWhenSwappingPlayer() {
        saveDefaultSimulation();
        simulation.players[1] = simulation.players[0];
        controller.startSimulation();
        fail("Simulation should be uneditable once initialized");
    }

    @Test
    public void initialState() {
        saveDefaultSimulation();
        assertEquals(Simulation.State.INITIAL, simulation.getState());
    }

    @Test
    public void haltPlayerInputThenCheckInProgress() {
        saveDefaultSimulation();
        try {
            controller.startSimulation();
        } catch (NoSuchElementException e) {
        }

        assertEquals(Simulation.State.IN_PROGRESS, simulation.getState());
        assertInputStreamEmpty();
    }

    @Test
    public void haltPlayerThenCheckReplayInProgress() {
        try {
            saveSimulation("0\n6\n1\n7\n2\n");
            controller.startSimulation();
        } catch (Configuration.CannotApplyException e) {
            fail(HUMAN_ERROR);
        } catch (NoSuchElementException e) {
        }

        assertEquals(Simulation.State.IN_PROGRESS, simulation.getState());
        assertInputStreamEmpty();
    }
}
