package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.simulation.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DefaultSimulationTest {

    private Simulation simulate() {
        Simulation simulation = new DefaultSimulation();
        List<String> args = new ArrayList<>(Arrays.asList("--minimax"));
        try {
            new SizeConfiguration().apply(args, simulation);
            new PlayersConfiguration(null, null).apply(args, simulation);
            new RulesConfiguration(null).apply(args, simulation);
        } catch (Configuration.CannotApplyException e) {
            fail("Could not apply configurations");
        }
        return simulation;
    }

    @Test
    public void initialState() {
        assertEquals(Simulation.State.INITIAL, simulate().getState());
    }

    @Test
    public void playerSecondState() {
        assertEquals(Simulation.State.IN_PROGRESS, simulate().nextState());
    }

    @Test
    public void completedAfterPlayer() {
        /* simulation state is not dependent on cpu moves */
        Simulation simulation = simulate();
        while (simulation.nextState() == Simulation.State.IN_PROGRESS) ;
        assertEquals(Simulation.State.COMPLETED, simulation.getState());
    }

    @Test
    public void start() {
        /* simulation state is not dependent on cpu moves */
        Simulation simulation = simulate();
        simulation.start();
        assertEquals(Simulation.State.TERMINATED, simulation.getState());
    }

}
