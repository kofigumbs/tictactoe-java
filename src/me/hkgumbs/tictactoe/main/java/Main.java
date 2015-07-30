package me.hkgumbs.tictactoe.main.java;

import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.configuration.FormatterConfiguration;
import me.hkgumbs.tictactoe.main.java.configuration.PlayersConfiguration;
import me.hkgumbs.tictactoe.main.java.configuration.SizeConfiguration;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultController;

import java.util.NoSuchElementException;

public class Main {
    private static final String USAGE = "usage: java -jar tictactoe-java.jar" +
            " [" + SizeConfiguration.KEY + " <number>]" +
            " [" + FormatterConfiguration.KEY + "--padding <number>]" +
            " PLAYER1 PLAYER2" +
            "\nPLAYER must be one of the following: " +
            PlayersConfiguration.HUMAN_KEY +
            PlayersConfiguration.MINIMAX_KEY +
            PlayersConfiguration.NAIVE_KEY;

    public static void main(String[] args) {
        try {
            DefaultController controller =
                    new DefaultController(System.in, System.out, args);
            controller.startSimulation();
        } catch (Configuration.CannotApplyException e) {
            System.out.println(USAGE);
        } catch (NoSuchElementException e) {
            /* user terminated program */
        }
    }
}
