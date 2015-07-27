package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

import java.io.OutputStream;
import java.util.List;

public class RulesConfiguration implements Configuration{

    OutputStream outputStream;

    public RulesConfiguration(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    @Override
    public void apply(List<String> args, Simulation simulation)
            throws CannotApplyException {
        Rules rules = new DefaultRules(simulation.getSize());
        rules.setMessageListener(outputStream);
        simulation.setRules(rules);
        for (Player player : simulation.getPlayers())
            player.setRules(rules);
    }
}