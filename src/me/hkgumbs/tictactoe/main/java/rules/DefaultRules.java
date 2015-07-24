package me.hkgumbs.tictactoe.main.java.rules;

import me.hkgumbs.tictactoe.main.java.board.Board;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class DefaultRules implements Rules {

    private Set<Set<Integer>> winningCombinations;
    private int dimension;
    private OutputStream outputStream;

    private void generateColumns() {
        for (int i = 0; i < dimension; i++) {
            HashSet<Integer> column = new HashSet<>();
            for (int j = i; column.size() < dimension; j += dimension)
                column.add(j);
            winningCombinations.add(column);
        }
    }

    private void generateLeftDiagonal() {
        HashSet<Integer> left = new HashSet<>();
        for (int i = dimension - 1; left.size() < dimension; i += dimension - 1)
            left.add(i);
        winningCombinations.add(left);
    }

    private void generateRows() {
        for (int i = 0; i < dimension; i++) {
            HashSet<Integer> row = new HashSet<>();
            for (int j = i * dimension; row.size() < dimension; j++)
                row.add(j);
            winningCombinations.add(row);
        }
    }

    private void generateRightDiagonal() {
        HashSet<Integer> right = new HashSet<>();
        for (int i = 0; right.size() < dimension; i += dimension + 1)
            right.add(i);
        winningCombinations.add(right);
    }

    public String generateWinnerMessage(Board board) {
        if (gameIsOver(board)) {
            Board.Mark winner = determineWinner(board);
            String label = winner == null ? "Nobody" : winner.toString();
            return label + " wins!";
        } else
            return "Game is in progress.";
    }

    public DefaultRules(int dimension) {
        winningCombinations = new HashSet<>();
        this.dimension = dimension;
        generateRows();
        generateColumns();
        generateRightDiagonal();
        generateLeftDiagonal();
    }

    @Override
    public boolean gameIsOver(Board board) {
        return board.isFull() || determineWinner(board) != null;
    }

    @Override
    public Board.Mark determineWinner(Board board) {
        for (Board.Mark mark : Board.Mark.values()) {
            Set<Integer> marks = board.getSpaceIds(mark);
            for (Set combination : winningCombinations)
                if (marks.containsAll(combination))
                    return mark;
        }
        return null;
    }

    @Override
    public void printWinnerMessage(Board board) {
        if (outputStream != null) {
            String message = generateWinnerMessage(board);
            new PrintStream(outputStream).println(message);
        }
    }

    @Override
    public void setMessageListener(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public boolean validateMove(Board board, int move) {
        return board.getEmptySpaceIds().contains(move);
    }
}
