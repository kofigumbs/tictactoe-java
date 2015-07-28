package me.hkgumbs.tictactoe.test.java.configuration;

import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;
import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.configuration.FormatterConfiguration;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.test.java.simulation.StubSimulation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FormatterConfigurationTest {

    private Simulation configure(String... args) {
        Simulation simulation = new StubSimulation();
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        Configuration[] configurations = new Configuration[]{
                new FormatterConfiguration()
        };

        simulation.size = 3;
        try {
            for (Configuration configuration : configurations)
                configuration.apply(arguments, simulation);
        } catch (Configuration.CannotApplyException e) {
            fail("Could not apply configurations");
        }
        return simulation;
    }

    @Test
    public void defaultFormatter() {
        Simulation simulation = configure();
        assertTrue(simulation.formatter instanceof SquareBoardFormatter);
    }

    @Test
    public void onePadding() {
        Simulation simulation = configure("--padding", "2");
        assertEquals(2, simulation.formatter.getPadding());
    }
}
