package me.hkgumbs.tictactoe.main.java.board;

import java.util.Set;

public abstract class Board implements Iterable<Board.Mark> {

    public enum Mark {
        O, X;

        public Mark other() {
            return this == X ? O : X;
        }
    }

    public abstract Board add(int position, Mark mark);

    public abstract int getCapacity();

    public abstract Set<Integer> getEmptySpaceIds();

    public abstract Set<Integer> getSpaceIds(Mark mark);

    public boolean isFull() {
        return getEmptySpaceIds().size() == 0;
    }

    public boolean isEmpty() {
        return getEmptySpaceIds().size() == getCapacity();
    }


}
