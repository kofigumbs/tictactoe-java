package me.hkgumbs.tictactoe.main.java.configuration;

import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;

import java.util.List;

public class RulesConfiguration implements Configuration {

    @Override
    public void apply(List<String> arguments, Simulation simulation)
            throws CannotApplyException {
        Rules rules = new DefaultRules(
                simulation.size);
        simulation.rules = rules;
    }
}
