package me.hkgumbs.tictactoe.main.java.simulation;

import java.util.List;

public class SizeConfiguration implements Configuration {

    private static final String key = "--size";
    private static final int defaultSize = 3;

    private int parseSize(List<String> args) {
        int index = args.indexOf(key);
        if (index == -1)
            return defaultSize;
        String sizeString = args.get(index + 1);
        return Integer.valueOf(sizeString);
    }

    @Override
    public void apply(List<String> args, Simulation simulation)
            throws CannotApplyException {
        try {
            int size = parseSize(args);
            simulation.setSize(size);
        } catch (NumberFormatException e) {
            throw new CannotApplyException();
        }
    }
}
