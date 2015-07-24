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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SizeConfigurationsTest {


    public Simulation configure(String... arguments) {
        Simulation simulation = new DefaultSimulation();
        List<String> args = new ArrayList<>(Arrays.asList(arguments));
        Configuration configuration = new SizeConfiguration();
        try {
            configuration.apply(args, simulation);
        } catch (Configuration.CannotApplyException e) {
            e.printStackTrace();
        }
        return simulation;
    }

    @Test
    public void sizeThreeDefault() {
        Simulation simulation = configure();
        assertEquals(3, simulation.getSize());
    }

    @Test
    public void sizeThreeFromArgs() {
        Simulation simulation = configure("--size", "3");
        assertEquals(3, simulation.getSize());
    }

    @Test
    public void sizeFiveFromArgs() {
        Simulation simulation = configure("--size", "5");
        assertEquals(5, simulation.getSize());
    }

    @Test
    public void throwsCannotApply() {
        InputStream input = new ByteArrayInputStream("".getBytes());
        OutputStream output = new ByteArrayOutputStream();
        Simulation simulation = new DefaultSimulation();
        List<String> args = new ArrayList<>(Arrays.asList("--size", "zx", "5"));
        Configuration configuration = new SizeConfiguration();
        boolean flag = false;
        try {
            configuration.apply(args, simulation);
        } catch (Configuration.CannotApplyException e) {
            flag = true;
        }
        assertTrue(flag);
    }

}
