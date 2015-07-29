package me.hkgumbs.tictactoe.test.java.configuration;

import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.configuration.PlayersConfiguration;
import me.hkgumbs.tictactoe.main.java.player.*;
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

    public Simulation configure(String... arguments)
            throws Configuration.CannotApplyException {
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        Simulation simulation = new StubSimulation();
        Configuration configuration =
                new PlayersConfiguration(inputStream, outputStream);
        List<String> args = new ArrayList<>(Arrays.asList(arguments));
        configuration.apply(args, simulation);
        return simulation;
    }

    @Test
    public void humanThenMinimax() throws Configuration.CannotApplyException {
        Simulation simulation = configure("human", "minimax");
        assertTrue(simulation.players[0] instanceof Human);
        assertTrue(simulation.players[1] instanceof Computer);
        assertEquals(Minimax.class,
                ((Computer) simulation.players[1]).getAlgorithm());
        assertEquals(2, simulation.players.length);
    }

    @Test
    public void minimaxThenHuman() throws Configuration.CannotApplyException {
        Simulation simulation = configure("minimax", "human");
        assertTrue(simulation.players[1] instanceof Human);
        assertTrue(simulation.players[0] instanceof Computer);
        assertEquals(Minimax.class,
                ((Computer) simulation.players[0]).getAlgorithm());
        assertEquals(2, simulation.players.length);
    }

    @Test
    public void twoMinimax() throws Configuration.CannotApplyException {
        Simulation simulation = configure("minimax", "minimax");
        for (Player player : simulation.players) {
            assertTrue(player instanceof Computer);
            assertEquals(Minimax.class, ((Computer) player).getAlgorithm());
        }
        assertEquals(2, simulation.players.length);
    }

    @Test
    public void twoHumans() throws Configuration.CannotApplyException {
        Simulation simulation = configure("human", "human");
        assertTrue(simulation.players[0] instanceof Human);
        assertTrue(simulation.players[1] instanceof Human);
        assertEquals(2, simulation.players.length);
    }

    @Test
    public void twoNaive() throws Configuration.CannotApplyException {
        Simulation simulation = configure("naive", "naive");
        for (Player player : simulation.players) {
            assertTrue(player instanceof Computer);
            assertEquals(NaiveChoice.class, ((Computer) player).getAlgorithm());
        }
        assertEquals(2, simulation.players.length);
    }

    @Test(expected = Configuration.CannotApplyException.class)
    public void nonsense() throws Configuration.CannotApplyException {
        configure("human", "nonsense");
        fail("Nonsense is not a valid player");
    }

    @Test(expected = Configuration.CannotApplyException.class)
    public void notEnoughPlayers() throws Configuration.CannotApplyException {
        configure("human");
        fail("Nonsense is not a valid player");
    }

}
