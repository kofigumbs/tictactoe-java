package me.hkgumbs.tictactoe.main.java.configuration;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.player.*;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class PlayersConfiguration implements Configuration {

    private static final String HUMAN_KEY = "human";
    private static final String MINIMAX_KEY = "minimax";

    private final InputStream inputStream;
    private final OutputStream outputStream;

    private Board.Mark next = Board.Mark.X;
    private Simulation simulation;

    private Player assignPlayer(String key) throws CannotApplyException {
        Player player;

        if (key == MINIMAX_KEY) {
            Algorithm minimax = new Minimax(next, simulation.rules);
            player = new Computer(next, outputStream, minimax);

        } else if (key == HUMAN_KEY)
            player = new Human(next, inputStream, outputStream, simulation);

        else
            throw new CannotApplyException();

        next = next.other();
        return player;
    }

    public PlayersConfiguration(
            InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public void apply(List<String> arguments, Simulation simulation)
            throws CannotApplyException {
        int length = arguments.size();
        this.simulation = simulation;
        simulation.players = new Player[2];
        simulation.players[0] = assignPlayer(arguments.get(length - 2));
        simulation.players[1] = assignPlayer(arguments.get(length - 1));
        arguments.remove(length - 1);
        arguments.remove(length - 2);
    }
}
