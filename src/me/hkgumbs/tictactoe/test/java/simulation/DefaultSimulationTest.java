package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.simulation.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultSimulation;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.main.java.simulation.SizeConfiguration;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DefaultSimulationTest {

    private Simulation simulate(String contents) {
        InputStream input = new ByteArrayInputStream(contents.getBytes());
        OutputStream output = new ByteArrayOutputStream();
        Simulation simulation = new DefaultSimulation(input, output);
        List<String> args = Arrays.asList("--size", "3");
        try {
            new SizeConfiguration().apply(args, simulation);
        } catch (Configuration.CannotApplyException e) {
            e.printStackTrace();
        }
        return simulation;
    }

    @Test
    public void initialState() {
        assertEquals(Simulation.State.INITIAL, simulate("").getState());
    }

    @Test
    public void playerSecondState() {
        assertEquals(Simulation.State.IN_PROGRESS, simulate("y\n").nextState());
        assertEquals(Simulation.State.IN_PROGRESS, simulate("n\n").nextState());
    }

    @Test
    public void completedAfterPlayer() {
        /* simulation state is not dependent on cpu moves */
        Simulation simulation = simulate("y\n0\n1\n2\n3\n4\n5\n6\n7\n8\n");
        while (simulation.nextState() == Simulation.State.IN_PROGRESS);
        assertEquals(Simulation.State.COMPLETED, simulation.getState());
    }

}
