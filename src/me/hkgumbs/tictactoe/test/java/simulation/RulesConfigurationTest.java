package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.simulation.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.RulesConfiguration;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNull;

public class RulesConfigurationTest {
    @Test
    public void defaultRules() throws Configuration.CannotApplyException {
        Simulation simulation = new StubSimulation();
        Configuration[] configurations = new Configuration[]{
                new RulesConfiguration(null)
        };

        simulation.size = 3;
        for (Configuration configuration : configurations)
            configuration.apply(Arrays.asList(), simulation);
        Board.Mark mark = simulation.rules.determineWinner(new SquareBoard(3));
        assertNull(null, mark);
    }
}
