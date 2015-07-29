package me.hkgumbs.tictactoe.main.java.configuration;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.player.*;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class PlayersConfiguration implements Configuration {

    private static final String HUMANS_ONLY_KEY = "--humans";
    private static final String MINIMAX_ONLY_KEY = "--minimax";

    private final InputStream inputStream;
    private final OutputStream outputStream;
    private Simulation simulation;

    private Player[] getTwoMinimax() {
        Algorithm x = new Minimax(Board.Mark.X, simulation.rules);
        Algorithm o = new Minimax(Board.Mark.O, simulation.rules);
        return new Player[]{
                new Computer(Board.Mark.X, outputStream, x),
                new Computer(Board.Mark.O, outputStream, o),
        };
    }

    private Player[] getTwoHumans() {
        return new Player[]{
                new Human(Board.Mark.X, inputStream, outputStream, simulation),
                new Human(Board.Mark.O, inputStream, outputStream, simulation)
        };
    }

    private Player[] getHumanAndMinimax() {
        Algorithm minimax = new Minimax(Board.Mark.X, simulation.rules);
        return new Player[]{
                new Human(Board.Mark.O, inputStream, outputStream, simulation),
                new Computer(Board.Mark.X, outputStream, minimax)
        };
    }

    public PlayersConfiguration(
            InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public void apply(List<String> arguments, Simulation simulation)
            throws CannotApplyException {
        this.simulation = simulation;
        if (arguments.contains(MINIMAX_ONLY_KEY)) {
            simulation.players = getTwoMinimax();
            arguments.remove(MINIMAX_ONLY_KEY);
        } else if (arguments.contains(HUMANS_ONLY_KEY)) {
            simulation.players = getTwoHumans();
            arguments.remove(HUMANS_ONLY_KEY);
        } else
            simulation.players = getHumanAndMinimax();
    }
}
