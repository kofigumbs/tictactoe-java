package me.hkgumbs.tictactoe.main.java.simulation;

import java.util.List;

public interface Configuration {
    void apply(List<String> arguments, Simulation simulation)
            throws CannotApplyException;

    class CannotApplyException extends Exception {
    }
}
