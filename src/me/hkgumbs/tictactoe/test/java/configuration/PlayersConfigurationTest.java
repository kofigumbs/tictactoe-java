package me.hkgumbs.tictactoe.test.java.configuration;

import me.hkgumbs.tictactoe.main.java.player.Human;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.configuration.PlayersConfiguration;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.test.java.simulation.StubSimulation;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class PlayersConfigurationTest {

    public Simulation configure(String... arguments) {
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        Simulation simulation = new StubSimulation();
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
        Simulation simulation = configure();
        int humanCount = 0;
        int minimaxCount = 0;
        for (Player player : simulation.players)
            if (player instanceof Human)
                humanCount++;
            else if (player instanceof Minimax)
                minimaxCount++;
        assertEquals(1, humanCount);
        assertEquals(1, minimaxCount);
        assertEquals(2, simulation.players.length);
    }

    @Test
    public void twoMinimax() {
        Simulation simulation = configure("--minimax");
        assertTrue(simulation.players[0] instanceof Minimax);
        assertTrue(simulation.players[1] instanceof Minimax);
        assertEquals(2, simulation.players.length);
    }

    @Test
    public void twoHumans() {
        Simulation simulation = configure("--humans");
        assertTrue(simulation.players[0] instanceof Human);
        assertTrue(simulation.players[1] instanceof Human);
        assertEquals(2, simulation.players.length);
    }

}
