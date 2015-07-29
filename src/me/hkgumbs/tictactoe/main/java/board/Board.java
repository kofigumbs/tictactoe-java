package me.hkgumbs.tictactoe.main.java.board;

import java.util.List;

public interface Board extends Iterable<Board.Mark> {

    enum Mark {
        O, X;

        public Mark other() {
            return this == X ? O : X;
        }
    }

    Board add(int position, Mark mark);

    int getCapacity();

    List<Integer> getEmptySpaceIds();

    List<Integer> getSpaceIds(Mark mark);

    default boolean isFull() {
        return getEmptySpaceIds().size() == 0;
    }

    default boolean isEmpty() {
        return getEmptySpaceIds().size() == getCapacity();
    }


}
