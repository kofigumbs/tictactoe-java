package me.hkgumbs.test.java.tictactoe;

import me.hkgumbs.main.java.tictactoe.Simulation;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class SimulationTest {

    private Simulation simlulate(String contents) {
        return new Simulation(
                new ByteArrayInputStream(contents.getBytes()),
                new ByteArrayOutputStream());
    }

    @Test
    public void initialState() {
        assertEquals(Simulation.State.INITIAL, simlulate("").state());
    }

    @Test
    public void whoGoesFirstState() {
        assertEquals(Simulation.State.HUMAN_TURN, simlulate("y\n").next());
        assertEquals(Simulation.State.CPU_TURN, simlulate("n\n").next());
    }

    @Test
    public void humanMovesThenCpuMoves() {
        Simulation simulation = simlulate("y\n0\n");
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
    }

    @Test
    public void cpuMovesThenHumanMovesThenCpuMoves() {
        Simulation simulation = simlulate("n\n1\n");
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
    }

    @Test
    public void simulatedGameCompletes() {
        Simulation simulation = simlulate("n\n1\n4\n");
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.COMPLETED, simulation.next());
    }

    @Test
    public void replayGame() {
        Simulation simulation = simlulate("n\n1\n4\ny\n");
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.COMPLETED, simulation.next());
        assertEquals(Simulation.State.INITIAL, simulation.next());
    }

    @Test
    public void declineReplayGame() {
        Simulation simulation = simlulate("n\n1\n4\nn\n");
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.COMPLETED, simulation.next());
        assertEquals(Simulation.State.TERMINATED, simulation.next());
    }
}
