package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

public abstract class Simulation {

    public enum State {
        INITIAL, IN_PROGRESS, COMPLETED, TERMINATED
    }

    public int size;

    public BoardFormatter formatter = null;

    public Player[] players = {};

    public Rules rules = null;

    public abstract State getState();

    public class AlreadyStartedException extends Throwable {
    }
}
