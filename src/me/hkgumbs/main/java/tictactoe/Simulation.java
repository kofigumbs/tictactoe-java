package me.hkgumbs.main.java.tictactoe;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

public class Simulation {

    private static final String PROMPT = " >> ";
    private static final String ONBOARD = "Welcome to Tic Tac Toe!\n" +
            "Make a move by entering an empty space id\n" +
            new Board().format() +
            "Would you like to go first? (y/n) ";

    private Board board = new Board();
    private Board.Mark turn = Board.Mark.X;
    private Minimax cpu;
    private Scanner userInputScanner;
    private PrintStream printStream;

    public Simulation(InputStream userInput, OutputStream outputStream) {
        userInputScanner = new Scanner(userInput);
        printStream = new PrintStream(outputStream);
    }

    /* reads first valid move from input stream */
    public int parseValidMove(String initialPrompt) {
        printStream.print(initialPrompt);
        while (true) {
            try {
                String response = userInputScanner.nextLine();
                response = response.split(" ", 2)[0];
                int move = Integer.parseInt(response);
                if (Game.validate(board, move))
                    return move;
            } catch (NumberFormatException e) {
                /* caused when non-numeric text is entered */
            }
            printStream.print("Invalid move!" + PROMPT);
        }
    }

    public boolean parseYesOrNo(String initialPrompt) {
        printStream.print(initialPrompt);
        while (true) {
            String response = userInputScanner.nextLine().toLowerCase();
            response = response.split(" ", 2)[0];
            if (response.equals("y") || response.equals("yes"))
                return true;
            else if (response.equals("n") || response.equals("no"))
                return false;
            printStream.print("Invalid response!" + PROMPT);
        }
    }

    public void start() {
        cpu = new Minimax(parseYesOrNo(ONBOARD) ? Board.Mark.X : Board.Mark.O);

        while (!Game.over(board)) {
            int move = turn == cpu.mark ?
                    parseValidMove(turn + PROMPT) : cpu.run(board);
            board = board.add(move, turn);
            turn = turn.other();

            if (turn == cpu.mark)
                /* printing the move keeps format consistent between human and
                 * cpu players */
                printStream.println(Integer.toString(move));
            printStream.println(board.format());
        }

        Board.Mark winner = Game.winner(board);
        String label = winner == null ? "Nobody" : winner.toString();
        printStream.println(label + " wins!");
    }

    public static void main(String[] args) {
        try {
            new Simulation(System.in, System.out).start();
        } catch (NoSuchElementException e) {
            /* user terminated program */
        }
    }
}
