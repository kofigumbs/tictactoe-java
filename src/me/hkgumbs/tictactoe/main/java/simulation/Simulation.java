package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.player.Player;

public interface Simulation {

    enum State {
        INITIAL, IN_PROGRESS, COMPLETED, TERMINATED;
    }

    BoardFormatter getFormatter();

    Player[] getPlayers();

    int getSize();

    State getState();

    State nextState();

    void setFormatter(BoardFormatter formatter);

    void setPlayers(Player... players);

    void setSize(int size);
}
