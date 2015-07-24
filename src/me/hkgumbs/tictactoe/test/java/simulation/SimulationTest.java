package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class SimulationTest {

    private Simulation simulate(String contents) {
        return new Simulation(
                new ByteArrayInputStream(contents.getBytes()),
                new ByteArrayOutputStream(), 0);
    }

    @Test
    public void initialState() {
        assertEquals(Simulation.State.INITIAL, simulate("").state());
    }

    @Test
    public void whoGoesFirstState() {
        assertEquals(Simulation.State.HUMAN_TURN, simulate("y\n").next());
        assertEquals(Simulation.State.CPU_TURN, simulate("n\n").next());
    }

    @Test
    public void humanMovesThenCpuMoves() {
        Simulation simulation = simulate("y\n0\n");
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
    }

    @Test
    public void cpuMovesThenHumanMovesThenCpuMoves() {
        Simulation simulation = simulate("n\n1\n");
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
    }

    @Test
    public void simulatedGameCompletes() {
        Simulation simulation = simulate("n\n1\n4\n");
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.COMPLETED, simulation.next());
    }

    @Test
    public void replayGame() {
        Simulation simulation = simulate("n\n1\n4\ny\n");
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
        Simulation simulation = simulate("n\n1\n4\nn\n");
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.HUMAN_TURN, simulation.next());
        assertEquals(Simulation.State.CPU_TURN, simulation.next());
        assertEquals(Simulation.State.COMPLETED, simulation.next());
        assertEquals(Simulation.State.TERMINATED, simulation.next());
    }
}
