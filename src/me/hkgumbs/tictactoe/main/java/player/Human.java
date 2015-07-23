package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Human implements Player {

    private final Scanner input;
    private final PrintStream error;
    private final Board.Mark mark;
    private Rules rules;

    public Human(Board.Mark mark,
                 InputStream inputStream, OutputStream errorStream) {
        this.mark = mark;
        input = new Scanner(inputStream);
        error = new PrintStream(errorStream);
        rules = new DefaultRules(3);
    }

    @Override
    public int determineNextMove(Board board) {
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
            error.print("Invalid move! ");
        }
    }

    @Override
    public Board.Mark getMark() {
        return mark;
    }

    @Override
    public void setRules(Rules rules) {
        this.rules = rules;
    }

    @Override
    public boolean yesOrNo() {
        while (true) {
            String response = input.nextLine().toLowerCase();
            response = response.split(" ", 2)[0];
            if (response.equals("y") || response.equals("yes"))
                return true;
            else if (response.equals("n") || response.equals("no"))
                return false;
            error.print("Invalid response! ");
        }
    }
}
