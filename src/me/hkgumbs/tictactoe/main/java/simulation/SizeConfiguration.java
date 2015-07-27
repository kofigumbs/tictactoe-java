package me.hkgumbs.tictactoe.main.java.simulation;

import java.util.List;

public class SizeConfiguration implements Configuration {

    private static final String KEY = "--size";
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
