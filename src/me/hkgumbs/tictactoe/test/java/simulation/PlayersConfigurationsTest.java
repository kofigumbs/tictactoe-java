package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.player.Human;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import me.hkgumbs.tictactoe.main.java.simulation.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultSimulation;
import me.hkgumbs.tictactoe.main.java.simulation.PlayersConfiguration;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
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
import static org.junit.Assert.fail;


public class PlayersConfigurationsTest {

    public Simulation configure(String input, String... arguments) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        Simulation simulation =
                new DefaultSimulation();
        Configuration configuration =
                new PlayersConfiguration(inputStream, outputStream);
        List<String> args = new ArrayList<>(Arrays.asList(arguments));
        try {
            configuration.apply(args, simulation);
        } catch (Configuration.CannotApplyException e) {
            fail("Could not apply configurations");
        }
        return simulation;
    }

    @Test
    public void oneHumanOneMinimaxDefault() {
        Simulation simulation = configure("y\n");
        assertTrue(simulation.getPlayers()[0] instanceof Human);
        assertTrue(simulation.getPlayers()[1] instanceof Minimax);
        assertEquals(2, simulation.getPlayers().length);
    }

    @Test
    public void twoMinimax() {
        Simulation simulation = configure("", "--minimax");
        assertTrue(simulation.getPlayers()[0] instanceof Minimax);
        assertTrue(simulation.getPlayers()[1] instanceof Minimax);
        assertEquals(2, simulation.getPlayers().length);
    }

    @Test
    public void twoHumans() {
        Simulation simulation = configure("", "--humans");
        assertTrue(simulation.getPlayers()[0] instanceof Human);
        assertTrue(simulation.getPlayers()[1] instanceof Human);
        assertEquals(2, simulation.getPlayers().length);
    }

}
