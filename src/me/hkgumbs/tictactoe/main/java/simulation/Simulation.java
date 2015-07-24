package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

public interface Simulation {

    enum State {
        INITIAL, IN_PROGRESS, COMPLETED, TERMINATED;
    }

    BoardFormatter getFormatter();

    Player[] getPlayers();

    Rules getRules();

    int getSize();

    State getState();

    State nextState();

    void setFormatter(BoardFormatter formatter);

    void setPlayers(Player... players);

    void setRules(Rules rules);

    void setSize(int size);

    void start();
}
