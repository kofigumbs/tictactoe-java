package me.hkgumbs.tictactoe.main.java.simulation;

public interface Configuration {
    void applyTo(Simulation simulation) throws CannotApplyException;

    class CannotApplyException extends Exception {
    }
}
