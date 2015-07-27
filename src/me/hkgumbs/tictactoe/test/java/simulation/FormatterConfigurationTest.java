package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.simulation.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FormatterConfigurationTest {

    private Simulation configure(String... args) {
        Simulation simulation = new DefaultSimulation();
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        Configuration[] configurations = new Configuration[]{
                new SizeConfiguration(),
                new FormatterConfiguration()
        };
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
        String board = simulation.getFormatter().format(new SquareBoard(3));
        assertEquals("(0)|(1)|(2)\n" +
                "-----------\n" +
                "(3)|(4)|(5)\n" +
                "-----------\n" +
                "(6)|(7)|(8)", board);
    }

    @Test
    public void onePadding() {
        Simulation simulation = configure("--padding", "2");
        String board = simulation.getFormatter().format(new SquareBoard(3));
        assertEquals("       |       |       \n" +
                        "       |       |       \n" +
                        "  (0)  |  (1)  |  (2)  \n" +
                        "       |       |       \n" +
                        "       |       |       \n" +
                        "-----------------------\n" +
                        "       |       |       \n" +
                        "       |       |       \n" +
                        "  (3)  |  (4)  |  (5)  \n" +
                        "       |       |       \n" +
                        "       |       |       \n" +
                        "-----------------------\n" +
                        "       |       |       \n" +
                        "       |       |       \n" +
                        "  (6)  |  (7)  |  (8)  \n" +
                        "       |       |       \n" +
                        "       |       |       ",
                board);
    }
}
