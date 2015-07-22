package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.Game;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Human extends Player {

    private final Scanner input;
    private final PrintStream error;
    private final Board.Mark mark;

    public Human(Board.Mark mark,
                 InputStream inputStream, OutputStream errorStream) {
        this.mark = mark;
        input = new Scanner(inputStream);
        error = new PrintStream(errorStream);
    }

    @Override
    public int consider(Board board) {
        while (true) {
            try {
                String response = input.nextLine();
                response = response.split(" ", 2)[0];
                int move = Integer.parseInt(response);
                if (Game.validate(board, move))
                    return move;
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
