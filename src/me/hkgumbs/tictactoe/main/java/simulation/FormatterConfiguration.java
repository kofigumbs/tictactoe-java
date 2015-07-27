package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.formatter.DefaultSlotRepresentation;
import me.hkgumbs.tictactoe.main.java.formatter.SlotRepresentation;
import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;

import java.util.List;

public class FormatterConfiguration implements Configuration {

    private static final String KEY = "--padding";
    private static final int DEFAULT_PADDING = 0;
    private static final SlotRepresentation SLOT =
            new DefaultSlotRepresentation();

    @Override
    public void apply(List<String> arguments, Simulation simulation)
            throws CannotApplyException {
        int size = simulation.size;
        int padding = Extractor.parseInt(arguments, KEY, DEFAULT_PADDING);
        BoardFormatter formatter = new SquareBoardFormatter(size, SLOT);
        simulation.formatter = formatter;
        formatter.setPadding(padding);
    }
}