package me.hkgumbs.tictactoe.main.java;

import me.hkgumbs.tictactoe.main.java.configuration.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultController;

import java.util.NoSuchElementException;

public class Main {
    private static final String USAGE = "usage: java -jar tictactoe-java.jar" +
            " [--size <number>]" +
            " [--padding <number>]" +
            " PLAYER1 PLAYER2" +
            "\n PLAYER must one of the following: human, minimax";

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
