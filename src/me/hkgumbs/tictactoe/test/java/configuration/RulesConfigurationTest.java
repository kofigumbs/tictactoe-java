package me.hkgumbs.tictactoe.test.java.configuration;

import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.configuration.RulesConfiguration;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.test.java.simulation.StubSimulation;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class RulesConfigurationTest {
    @Test
    public void defaultRules() throws Configuration.CannotApplyException {
        Simulation simulation = new StubSimulation();
        Configuration[] configurations = new Configuration[]{
                new RulesConfiguration()
        };

        simulation.size = 3;
        for (Configuration configuration : configurations)
            configuration.apply(Arrays.asList(), simulation);
        assertTrue(simulation.rules instanceof DefaultRules);
    }
}
