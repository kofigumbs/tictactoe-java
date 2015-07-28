package me.hkgumbs.tictactoe.main.java.configuration;

import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;

import java.io.OutputStream;
import java.util.List;

public class RulesConfiguration implements Configuration {

    OutputStream outputStream;

    public RulesConfiguration(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    @Override
    public void apply(List<String> arguments, Simulation simulation)
            throws CannotApplyException {
        Rules rules = new DefaultRules(
                simulation.size, outputStream, simulation);
        simulation.rules = rules;
    }
}
