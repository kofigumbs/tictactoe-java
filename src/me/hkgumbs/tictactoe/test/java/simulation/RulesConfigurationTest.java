package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.simulation.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RulesConfigurationTest {
    @Test
    public void defaultRules() {
        Simulation simulation = new DefaultSimulation();
        OutputStream outputStream = new ByteArrayOutputStream();
        Configuration[] configurations = new Configuration[]{
                new SizeConfiguration(),
                new RulesConfiguration(outputStream)
        };
        try {
            for (Configuration configuration : configurations)
                configuration.apply(Arrays.asList(), simulation);
        } catch (Configuration.CannotApplyException e) {
            e.printStackTrace();
        }
        simulation.getRules().printWinnerMessage(new SquareBoard(3));
        assertEquals("Game is in progress.\n", outputStream.toString());
    }
}
