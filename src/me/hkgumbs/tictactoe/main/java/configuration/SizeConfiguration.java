package me.hkgumbs.tictactoe.main.java.configuration;

import me.hkgumbs.tictactoe.main.java.simulation.Simulation;
import me.hkgumbs.tictactoe.main.java.utility.Extractor;

import java.util.List;

public class SizeConfiguration implements Configuration {

    public static final String KEY = "--size";
    private static final int DEFAULT_SIZE = 3;

    @Override
    public void apply(List<String> arguments, Simulation simulation)
            throws CannotApplyException {
        try {
            int size = Extractor.parseInt(arguments, KEY, DEFAULT_SIZE);
            simulation.size = size;
        } catch (NumberFormatException e) {
            throw new CannotApplyException();
        }
    }
}
