package me.hkgumbs.tictactoe.main.java;

import me.hkgumbs.tictactoe.main.java.simulation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    private static final String USAGE = "usage: java -jar tictactoe-java.jar" +
            " [--size <number>]" +
            " [--padding <number>]" +
            " [--humans|--minimax]";

    private static final Configuration[] CONFIGURATIONS = new Configuration[]{
            new SizeConfiguration(),
            /* size must be applied first */
            new PlayersConfiguration(System.in, System.out),
            new RulesConfiguration(System.out),
            new FormatterConfiguration()
    };

    public static void main(String[] args) {
        Simulation simulation = new DefaultSimulation();
        List<String> arguments = new ArrayList<>(Arrays.asList(args));

        try {
            for (Configuration configuration : CONFIGURATIONS)
                configuration.apply(arguments, simulation);
            if (!arguments.isEmpty())
                throw new Configuration.CannotApplyException();
            simulation.start();

        } catch (Configuration.CannotApplyException e) {
            System.out.println(USAGE);
        } catch (NoSuchElementException e) {
            /* user terminated program */
        }
    }
}
