package me.hkgumbs.tictactoe.main.java.simulation;

import java.util.List;

public class SizeConfiguration implements Configuration {

    private static final String KEY = "--size";
    private static final int DEFAULT_SIZE = 3;

    @Override
    public void apply(List<String> args, Simulation simulation)
            throws CannotApplyException {
        try {
            int size = Extractor.extract(args, KEY, DEFAULT_SIZE);
            simulation.setSize(size);
        } catch (NumberFormatException e) {
            throw new CannotApplyException();
        }
    }
}
