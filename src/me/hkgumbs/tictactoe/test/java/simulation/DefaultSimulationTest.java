package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.simulation.DefaultSimulation;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class DefaultSimulationTest {

    private DefaultSimulation simulate(String contents) {
        return new DefaultSimulation(
                new ByteArrayInputStream(contents.getBytes()),
                new ByteArrayOutputStream());
    }

    @Test
    public void initialState() {
        assertEquals(DefaultSimulation.State.INITIAL, simulate("").getState());
    }

    @Test
    public void whoGoesFirstState() {
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulate("y\n").next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulate("n\n").next());
    }

    @Test
    public void humanMovesThenCpuMoves() {
        DefaultSimulation simulation = simulate("y\n0\n");
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
    }

    @Test
    public void cpuMovesThenHumanMovesThenCpuMoves() {
        DefaultSimulation simulation = simulate("n\n1\n");
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
    }

    @Test
    public void simulatedGameCompletes() {
        DefaultSimulation simulation = simulate("n\n1\n4\n");
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.COMPLETED, simulation.next());
    }

    @Test
    public void replayGame() {
        DefaultSimulation simulation = simulate("n\n1\n4\ny\n");
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.COMPLETED, simulation.next());
        assertEquals(DefaultSimulation.State.INITIAL, simulation.next());
    }

    @Test
    public void declineReplayGame() {
        DefaultSimulation simulation = simulate("n\n1\n4\nn\n");
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.HUMAN_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.CPU_TURN, simulation.next());
        assertEquals(DefaultSimulation.State.COMPLETED, simulation.next());
        assertEquals(DefaultSimulation.State.TERMINATED, simulation.next());
    }
}
