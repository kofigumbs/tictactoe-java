package me.hkgumbs.tictactoe.test.java.configuration;

import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.main.java.configuration.SizeConfiguration;
import me.hkgumbs.tictactoe.test.java.simulation.StubSimulation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class SizeConfigurationTest {


    public Simulation configure(String... args) {
        Simulation simulation = new StubSimulation();
        Configuration configuration = new SizeConfiguration();
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        try {
            configuration.apply(arguments, simulation);
        } catch (Configuration.CannotApplyException e) {
            fail("Could not apply configurations");
        }
        return simulation;
    }

    @Test
    public void sizeThreeDefault() {
        Simulation simulation = configure();
        assertEquals(3, simulation.size);
    }

    @Test
    public void sizeThreeFromArgs() {
        Simulation simulation = configure("--size", "3");
        assertEquals(3, simulation.size);
    }

    @Test
    public void sizeFiveFromArgs() {
        Simulation simulation = configure("--size", "5");
        assertEquals(5, simulation.size);
    }

    @Test(expected = Configuration.CannotApplyException.class)
    public void throwsCannotApply() throws Configuration.CannotApplyException {
        Simulation simulation = null; // new DefaultSimulation();
        List<String> args = new ArrayList<>(Arrays.asList("--size", "zx", "5"));
        Configuration configuration = new SizeConfiguration();
        configuration.apply(args, simulation);
        fail("Should not have been able to apply exception!");
    }

}
