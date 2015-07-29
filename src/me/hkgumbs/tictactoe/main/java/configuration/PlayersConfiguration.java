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
    private static final String NAIVE_KEY = "naive";

    private final InputStream inputStream;
    private final OutputStream outputStream;

    private Board.Mark next = Board.Mark.X;
    private Simulation simulation;

    private Player assignPlayer(String key) throws CannotApplyException {
        Player player;

        if (key.equals(MINIMAX_KEY)) {
            Algorithm minimax = new Minimax(next, simulation.rules);
            player = new Computer(next, outputStream, minimax);

        } else if (key.equals(NAIVE_KEY)) {
            Algorithm naive = new NaiveChoice();
            player = new Computer(next, outputStream, naive);

        } else if (key.equals(HUMAN_KEY))
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
        if (arguments.size() < 2)
            throw new CannotApplyException();

        this.simulation = simulation;
        Player[] players = new Player[2];

        for (int i = players.length - 1; i >= 0; i--) {
            int index = arguments.size() - 1;
            String key = arguments.remove(index);
            players[i] = assignPlayer(key);
        }
        simulation.players = players;
    }
}
