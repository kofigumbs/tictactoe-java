package me.hkgumbs.tictactoe.main.java.rules;

import me.hkgumbs.tictactoe.main.java.board.Board;

import java.util.Arrays;
import java.util.List;

public class Rules {

    private static final List[] WINNING_COMBINATIONS = new List[]{
            Arrays.asList(0, 1, 2), Arrays.asList(3, 4, 5),
            Arrays.asList(6, 7, 8), Arrays.asList(0, 4, 8),
            Arrays.asList(0, 3, 6), Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8), Arrays.asList(2, 4, 6)
    };

    public static boolean gameIsOver(Board board) {
        return board.isFull() || determineWinner(board) != null;
    }

    public static Board.Mark determineWinner(Board board) {
        for (Board.Mark mark : Board.Mark.values())
            for (List winningCombination : WINNING_COMBINATIONS)
                if (board.getSpaceIds(mark).containsAll(winningCombination))
                    return mark;
        return null;
    }

    public static boolean validate(Board board, int move) {
        return board.getEmptySpaceIds().contains(move);
    }
}
