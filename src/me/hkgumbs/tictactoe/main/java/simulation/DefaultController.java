package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.player.Player;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class DefaultController {

    private final Configuration[] configurations;
    private final String[] args;

    private DefaultSimulation simulation;
    private Simulation.State state;
    private List<Object> snapshot;
    private Board board;
    private int turn;

    private void applyConfigurations(
            List<String> arguments, Simulation simulation)
            throws Configuration.CannotApplyException {
        for (Configuration configuration : configurations)
            configuration.apply(arguments, simulation);
        if (!arguments.isEmpty())
            throw new Configuration.CannotApplyException();
    }

    private void makeSimulation() throws Configuration.CannotApplyException {
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        simulation = new DefaultSimulation();
        applyConfigurations(arguments, simulation);
        snapshot = simulation.getSnapshot();
        state = Simulation.State.INITIAL;
    }

    public DefaultController(
            InputStream inputStream, OutputStream outputStream, String[] args)
            throws Configuration.CannotApplyException {
        this.args = args;
        configurations = new Configuration[]{
                new SizeConfiguration(),
                new RulesConfiguration(outputStream),
                new FormatterConfiguration(),
                new PlayersConfiguration(inputStream, outputStream)
        };
        makeSimulation();
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void startSimulation() {
        simulation.start();
    }

    private class DefaultSimulation extends Simulation {

        void assignTurn() {
            turn = 0;
            for (int i = 0; i < players.length; i++)
                if (players[i].requestGoFirst() == Player.Response.YES) {
                    turn = i;
                    break;
                }
        }

        void checkSnapshot() {
            if (!getSnapshot().containsAll(snapshot))
                throw new ConcurrentModificationException();
        }

        List<Object> getSnapshot() {
            Field[] fields = getClass().getFields();
            Object[] snapshot = new Object[fields.length];
            for (int i = 0; i < fields.length; i++)
                try {
                    snapshot[i] = fields[i].get(this);
                } catch (IllegalAccessException e) {
                }
            return Arrays.asList(snapshot);
        }

        void start() {
            checkSnapshot();
            if (state == State.INITIAL)
                stateInitial();
            else if (state == State.IN_PROGRESS)
                stateInProgress();
            else if (state == State.COMPLETED)
                stateCompleted();
            else
                return;

            start();
        }

        private void stateInitial() {
            for (Player player : players)
                player.onboard();
            assignTurn();
            board = new SquareBoard(size);
            state = State.IN_PROGRESS;
        }

        private void stateInProgress() {
            Player current = players[turn];
            int position = current.determineNextMove(board);
            board = board.add(position, current.getMark());
            turn = (turn + 1) % players.length;
            if (simulation.rules.gameIsOver(board))
                state = State.COMPLETED;
        }

        private void stateCompleted() {
            rules.printWinnerMessage(board);
            boolean yes = false;
            boolean no = false;
            for (Player player : players) {
                Player.Response response = player.requestPlayAgain();
                if (response == Player.Response.YES)
                    yes = true;
                else if (response == Player.Response.NO)
                    no = true;
            }
            if (yes && !no)
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
