package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Human implements Player {

    private static final String ONBOARD = "Welcome to Tic Tac Toe!\n" +
            "Make a move by entering an empty space id\n";
    private static final String PLAY_AGAIN =
            "Would you like to play again? (y/n) ";

    private final Scanner input;
    private final PrintStream output;
    private final Board.Mark mark;
    private Rules rules;
    private BoardFormatter formatter;

    private void printMovePrompt(Board board) {
        output.println();
        if (formatter != null)
            output.println(formatter.format(board));
        output.print(mark + " >> ");
    }

    private int respondValidMove(Board board) {
        while (true) {
            try {
                String response = input.nextLine();
                response = response.split(" ", 2)[0];
                int position = Integer.parseInt(response);
                if (rules.validateMove(board, position))
                    return position;
            } catch (NumberFormatException e) {
                /* caused when non-numeric text is entered */
            }
            output.print("Invalid move! ");
        }
    }

    private boolean respondYesOrNo() {
        while (true) {
            String response = input.nextLine().toLowerCase();
            response = response.split(" ", 2)[0];
            if (response.equals("y") || response.equals("yes"))
                return true;
            else if (response.equals("n") || response.equals("no"))
                return false;
            output.print("Invalid response! ");
        }
    }

    public Human(Board.Mark mark,
                 InputStream inputStream, OutputStream outputStream) {
        this.mark = mark;
        input = new Scanner(inputStream);
        output = new PrintStream(outputStream);
        rules = new DefaultRules(3);
    }

    public boolean respondYesOrNo(String question) {
        output.print(question);
        return respondYesOrNo();
    }

    @Override
    public int determineNextMove(Board board) {
        printMovePrompt(board);
        return respondValidMove(board);
    }

    @Override
    public Board.Mark getMark() {
        return mark;
    }

    @Override
    public BoardFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void onboard() {
        output.print(ONBOARD);
    }

    @Override
    public boolean playAgain() {
        return respondYesOrNo(PLAY_AGAIN);
    }

    @Override
    public void setFormatter(BoardFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void setRules(Rules rules) {
        this.rules = rules;
    }
}
