package me.hkgumbs.tictactoe.main.java;

import me.hkgumbs.tictactoe.main.java.simulation.Configuration;
import me.hkgumbs.tictactoe.main.java.simulation.DefaultController;

import java.util.NoSuchElementException;

public class Main {
    private static final String USAGE = "usage: java -jar tictactoe-java.jar" +
            " [--size <number>]" +
            " [--padding <number>]" +
            " [--humans|--minimax]";

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
