package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.simulation.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultSimulation;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.main.java.simulation.SizeConfiguration;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ConfigurationsTest {


    @Test
    public void sizeThreeFromArgs() {
        Simulation simulation = new DefaultSimulation(
                new ByteArrayInputStream("".getBytes()),
                new ByteArrayOutputStream()
        );
        List<String> args = Arrays.asList("--size", "3");
        Configuration configuration = new SizeConfiguration(args);
        try {
            configuration.applyTo(simulation);
        } catch (Configuration.CannotApplyException e) {
            e.printStackTrace();
        }
        assertEquals(3, simulation.getSize());
    }

    @Test
    public void sizeFiveFromArgs() {
        Simulation simulation = new DefaultSimulation(
                new ByteArrayInputStream("".getBytes()),
                new ByteArrayOutputStream()
        );
        List<String> args = Arrays.asList("--size", "5");
        Configuration configuration = new SizeConfiguration(args);
        try {
            configuration.applyTo(simulation);
        } catch (Configuration.CannotApplyException e) {
            e.printStackTrace();
        }
        assertEquals(5, simulation.getSize());
    }

}
