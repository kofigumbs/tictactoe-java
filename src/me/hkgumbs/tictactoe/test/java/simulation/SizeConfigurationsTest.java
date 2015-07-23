package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.simulation.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultSimulation;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.main.java.simulation.SizeConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SizeConfigurationsTest {

    Simulation simulation;
    InputStream input = new ByteArrayInputStream("".getBytes());
    OutputStream output = new ByteArrayOutputStream();

    @Before
    public void simulate() {
        simulation = new DefaultSimulation(input, output);
    }

    @Test
    public void sizeThreeFromArgs() {
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
        List<String> args = Arrays.asList("--size", "5");
        Configuration configuration = new SizeConfiguration(args);
        try {
            configuration.applyTo(simulation);
        } catch (Configuration.CannotApplyException e) {
            e.printStackTrace();
        }
        assertEquals(5, simulation.getSize());
    }

    @Test
    public void throwsCannotApply() {
        boolean flag = false;
        List<String> args = Arrays.asList("--size", "asdf 5");
        Configuration configuration = new SizeConfiguration(args);
        try {
            configuration.applyTo(simulation);
        } catch (Configuration.CannotApplyException e) {
            flag = true;
        }
        assertTrue(flag);
    }

}
