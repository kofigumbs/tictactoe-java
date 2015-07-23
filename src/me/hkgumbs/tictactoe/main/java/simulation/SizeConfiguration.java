package me.hkgumbs.tictactoe.main.java.simulation;

import java.util.List;

public class SizeConfiguration implements Configuration {

    private static final String key = "--size";
    private static final int defaultSize = 3;

    private final List<String> args;

    private int parseSize() {
        int index = args.indexOf(key);
        if (index == -1)
            return defaultSize;
        String sizeString = args.get(index + 1);
        return Integer.valueOf(sizeString);
    }

    public SizeConfiguration(List<String> args) {
        this.args = args;
    }

    @Override
    public void applyTo(Simulation simulation) throws CannotApplyException {
        try {
            int size = parseSize();
            simulation.setSize(size);
        } catch (Exception e) {
            throw new CannotApplyException();
        }
    }
}
