package me.hkgumbs.main.java.tictactoe;

import java.util.Arrays;
import java.util.List;

public class Game {

    private static final List[] WINNING_COMBINATIONS = new List[]{
            Arrays.asList(0, 1, 2), Arrays.asList(3, 4, 5),
            Arrays.asList(6, 7, 8), Arrays.asList(0, 4, 8),
            Arrays.asList(0, 3, 6), Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8), Arrays.asList(2, 4, 6)
    };

    public static boolean over(Board board) {
        return board.full() || winner(board) != null;
    }

    /* returns null if game is not over or if game ended in tie */
    public static Mark winner(Board board) {
        for (Mark mark : Mark.values())
            for (List winningCombination : WINNING_COMBINATIONS)
                if (board.get(mark).containsAll(winningCombination))
                    return mark;
        return null;
    }

    public static boolean validate(Board board, int move) {
        return board.getEmpty().contains(move);
    }
}
