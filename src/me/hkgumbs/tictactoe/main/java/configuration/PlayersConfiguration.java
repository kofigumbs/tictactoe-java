package me.hkgumbs.tictactoe.main.java.configuration;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.player.Human;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import me.hkgumbs.tictactoe.main.java.player.Player;
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
        return new Player[]{
                new Minimax(Board.Mark.X, outputStream, simulation),
                new Minimax(Board.Mark.O, outputStream, simulation),
        };
    }

    private Player[] getTwoHumans() {
        return new Player[]{
                new Human(Board.Mark.X, inputStream, outputStream, simulation),
                new Human(Board.Mark.O, inputStream, outputStream, simulation)
        };
    }

    private Player[] getHumanAndMinimax() {
        return new Player[]{
                new Human(Board.Mark.O, inputStream, outputStream, simulation),
                new Minimax(Board.Mark.X, outputStream, simulation)
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
