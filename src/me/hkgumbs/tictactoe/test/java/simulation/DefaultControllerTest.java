package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;
import me.hkgumbs.tictactoe.main.java.player.Human;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultController;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import org.junit.Test;

import java.io.*;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DefaultControllerTest {

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

    private void assertSimulationState(Simulation.State state) {
        controller.startSimulation();
        assertEquals(state, simulation.getState());
        assertInputStreamEmpty();
    }

    private void saveDefaultSimulation(String input, String[] args)
            throws Configuration.CannotApplyException {
        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream(input.getBytes());
        controller = new DefaultController(inputStream, outputStream, args);
        simulation = controller.getSimulation();
    }

    private void saveDefaultSimulation(String input) {
        try {
            saveDefaultSimulation(input, new String[]{});
        } catch (Configuration.CannotApplyException e) {
            fail("Default configurations should always be valid");
        }
    }

    private void saveDefaultSimulation() {
        saveDefaultSimulation("");
    }

    @Test
    public void defaultSimulationSetup() {
        saveDefaultSimulation();
        assertEquals(3, simulation.size);
        assertTrue(simulation.formatter instanceof SquareBoardFormatter);
        assertTrue(simulation.rules instanceof DefaultRules);
        assertTrue(simulation.players[0] instanceof Minimax);
        assertTrue(simulation.players[1] instanceof Human);
    }

    @Test(expected = Configuration.CannotApplyException.class)
    public void throwsExceptionOnNonsenseOption()
            throws Configuration.CannotApplyException {
        saveDefaultSimulation("", new String[]{"--asdf"});
        controller.getSimulation();
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
        simulation.players[1] = new Minimax(
                Board.Mark.O, outputStream, simulation);
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
        saveDefaultSimulation("y\n");
        try {
            controller.startSimulation();
        } catch (NoSuchElementException e) {
        }

        assertEquals(Simulation.State.IN_PROGRESS, simulation.getState());
        assertInputStreamEmpty();
    }

    @Test
    public void haltPlayerThenCheckCompleted() {
        saveDefaultSimulation("n\n5\n3\n");
        try {
            controller.startSimulation();
        } catch (NoSuchElementException e) {
        }

        assertEquals(Simulation.State.COMPLETED, simulation.getState());
        assertInputStreamEmpty();
    }

    @Test
    public void haltPlayerThenCheckInitialAgain() {
        saveDefaultSimulation("n\n5\n3\ny\n");
        try {
            controller.startSimulation();
        } catch (NoSuchElementException e) {
        }

        assertEquals(Simulation.State.INITIAL, simulation.getState());
        assertInputStreamEmpty();
    }

    @Test
    public void fullSimulation() {
        saveDefaultSimulation("n\n5\n3\nn\n");
        assertSimulationState(Simulation.State.TERMINATED);
    }

    @Test
    public void fullSimulatioWithReplay() {
        saveDefaultSimulation("n\n5\n3\ny\nn\n5\n3\nn\n");
        assertSimulationState(Simulation.State.TERMINATED);
    }

    @Test
    public void fullSimulationWithTwoMinimax() {
        String[] args = new String[]{"--minimax"};
        try {
            saveDefaultSimulation("", args);
        } catch (Configuration.CannotApplyException e) {
            fail("--minimax should be a valid option");
        }
        assertSimulationState(Simulation.State.TERMINATED);
    }
}
