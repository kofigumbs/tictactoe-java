package me.hkgumbs.tictactoe.main.java.simulation;

import java.util.List;

public class SizeConfiguration implements Configuration {

    private static final String KEY = "--size";
    private static final int DEFAULT_SIZE = 3;

    private int extractSize(List<String> args) {
        int index = args.indexOf(KEY);
        if (index == -1)
            return DEFAULT_SIZE;
        String sizeString = args.get(index + 1);
        args.remove(index);
        args.remove(index);
        return Integer.valueOf(sizeString);
    }

    @Override
    public void apply(List<String> args, Simulation simulation)
            throws CannotApplyException {
        try {
            int size = extractSize(args);
            simulation.setSize(size);
        } catch (NumberFormatException e) {
            throw new CannotApplyException();
        }
    }
}
