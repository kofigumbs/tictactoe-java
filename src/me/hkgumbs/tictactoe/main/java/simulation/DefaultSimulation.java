package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

public class DefaultSimulation implements Simulation {

    private int turn;
    private int size;
    private Board board;
    private Rules rules;
    private State state = State.INITIAL;
    private BoardFormatter formatter;
    private Player[] players = {};

    @Override
    public State getState() {
        return state;
    }

    @Override
    public State nextState() {
        if (state == State.INITIAL) {
            turn = 0;
            board = new SquareBoard(size);
            state = State.IN_PROGRESS;

        } else if (state == State.IN_PROGRESS) {
            Player player = players[turn];
            int move = player.determineNextMove(board);
            board = board.add(move, player.getMark());
            boolean completed = rules.gameIsOver(board);
            state = completed ? State.COMPLETED : State.IN_PROGRESS;
            turn = (turn + 1) % players.length;

        } else if (state == State.COMPLETED) {
            rules.printWinnerMessage(board);
            boolean playAgain = false;
            for (Player player : players)
                playAgain |= player.playAgain();
            state = playAgain ? State.INITIAL : State.TERMINATED;
        }

        return state;
    }

    @Override
    public Player[] getPlayers() {
        return this.players;
    }

    @Override
    public Rules getRules() {
        return rules;
    }

    public int getSize() {
        return size;
    }

    @Override
    public BoardFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setPlayers(Player... players) {
        this.players = players;
    }

    @Override
    public void setRules(Rules rules) {
        this.rules = rules;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void setFormatter(BoardFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void start() {
        while (nextState() != State.TERMINATED) {
        }
    }
}
