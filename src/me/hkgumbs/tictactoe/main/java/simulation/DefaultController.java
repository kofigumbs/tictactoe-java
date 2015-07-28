package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.configuration.*;
import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.utility.Snapshot;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class DefaultController {

    private final Configuration[] configurations;
    private final List<String> arguments;

    private DefaultSimulation simulation;
    private Simulation.State state;
    private Snapshot snapshot;
    private Board board;
    private int turn;

    private void applyConfigurations()
            throws Configuration.CannotApplyException {
        for (Configuration configuration : configurations)
            configuration.apply(arguments, simulation);
        if (!arguments.isEmpty())
            throw new Configuration.CannotApplyException();
    }

    private void makeSimulation() throws Configuration.CannotApplyException {
        simulation = new DefaultSimulation();
        applyConfigurations();
        snapshot = new Snapshot(simulation);
        state = Simulation.State.INITIAL;
    }

    public DefaultController(
            InputStream inputStream, OutputStream outputStream, String[] args)
            throws Configuration.CannotApplyException {
        configurations = new Configuration[]{
                new SizeConfiguration(),
                new RulesConfiguration(outputStream),
                new FormatterConfiguration(),
                new PlayersConfiguration(inputStream, outputStream)
        };
        arguments = new ArrayList<>(Arrays.asList(args));
        makeSimulation();
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void startSimulation() {
        simulation.start();
    }

    private class DefaultSimulation extends Simulation {
        void checkSnapshot() {
            if (!new Snapshot(this).equals(snapshot))
                throw new ConcurrentModificationException();
        }

        void start() {
            while (state != State.TERMINATED) {
                checkSnapshot();
                if (state == State.INITIAL)
                    stateInitial();
                else if (state == State.IN_PROGRESS)
                    stateInProgress();
                else if (state == State.COMPLETED)
                    stateCompleted();
            }
        }

        void stateInitial() {
            for (Player player : players)
                player.onboard();
            turn = players[0].requestGoFirst() ? 0 : 1;
            board = new SquareBoard(size);
            state = State.IN_PROGRESS;
        }

        void stateInProgress() {
            Player current = players[turn];
            int position = current.determineNextMove(board);
            board = board.add(position, current.getMark());
            turn = (turn + 1) % players.length;
            if (simulation.rules.gameIsOver(board))
                state = State.COMPLETED;
        }

        void stateCompleted() {
            rules.printWinnerMessage(board);
            if (players[0].requestPlayAgain())
                state = State.INITIAL;
            else
                state = State.TERMINATED;
        }

        @Override
        public State getState() {
            return state;
        }
    }
}
