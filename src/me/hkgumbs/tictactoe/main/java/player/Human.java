package me.hkgumbs.tictactoe.main.java.player;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.simulation.Simulation;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Human implements Player {

    private static final String GO_FIRST =
            "Would you (%s) like to go first? (y/n) ";
    private static final String ONBOARD =
            "Make a move by entering an empty space id\n";
    private static final String PLAY_AGAIN =
            "Would you (%s) like to play again? (y/n) ";

    private final Scanner input;
    private final PrintStream output;
    private final Board.Mark mark;
    private final Simulation simulation;

    private int respondValidMove(Board board) {
        while (true) {
            try {
                String response = input.nextLine();
                response = response.split(" ", 2)[0];
                int position = Integer.parseInt(response);
                if (simulation.rules.validateMove(board, position))
                    return position;
            } catch (NumberFormatException e) {
                /* caused when non-numeric text is entered */
            }
            output.print("Invalid move! ");
        }
    }

    private Response getResponse(String question) {
        output.format(question, mark);
        return getResponse() ? Response.YES : Response.NO;
    }

    private boolean getResponse() {
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
                 InputStream inputStream,
                 OutputStream outputStream,
                 Simulation simulation) {
        this.mark = mark;
        this.simulation = simulation;
        input = new Scanner(inputStream);
        output = new PrintStream(outputStream);
    }

    @Override
    public int determineNextMove(Board board) {
        output.println(simulation.formatter.format(board));
        output.print(mark + " >> ");
        return respondValidMove(board);
    }

    @Override
    public Board.Mark getMark() {
        return mark;
    }


    @Override
    public void onboard() {
        output.print(ONBOARD);
    }

    @Override
    public Response requestGoFirst() {
        return getResponse(GO_FIRST);
    }

    @Override
    public Response requestPlayAgain() {
        return getResponse(PLAY_AGAIN);
    }

}
