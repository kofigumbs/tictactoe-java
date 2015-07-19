package main.java;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Simulation {

    private static final String PROMPT = " >> ";
    private static final String PRETTY_BOARD =
            "   %c | %c | %c \n" +
                    "  -----------\n" +
                    "   %c | %c | %c \n" +
                    "  -----------\n" +
                    "   %c | %c | %c \n\n";

    private Game game = new Game();
    private Scanner userInputScanner;
    private PrintStream printStream;

    /* prints all the information in the beginning of the simulation */
    private boolean onboard() {
        Object[] ids = new Object[Board.CAPACITY];
        for (int i = 0; i < ids.length; i++)
            ids[i] = Character.forDigit(i, 10);
        printStream.println("Welcome to Tic Tac Toe!");
        printStream.println("Make a move by entering an empty space id");
        printStream.format(PRETTY_BOARD, ids);
        printStream.print("Would you like to go first? (y/n) ");
        return userInputScanner.next().toLowerCase().startsWith("y");
    }

    /* prints the game board in a nice format */
    private void prettyPrint() {
        String ugly = getBoard().toString();
        Object[] marks = new Object[Board.CAPACITY];
        for (int i = 0; i < marks.length; i++)
            marks[i] = ugly.charAt(i);
        printStream.format(PRETTY_BOARD, marks);
    }

    public Simulation(InputStream userInput, OutputStream outputStream) {
        userInputScanner = new Scanner(userInput);
        printStream = new PrintStream(outputStream);
    }

    public Board getBoard() {
        return game.getBoard();
    }

    /* reads first valid move from input stream */
    public void consumeMove() {
        while (true) {
            if (game.play(userInputScanner.nextInt()))
                return;
            printStream.print("Invalid move!" + PROMPT);
        }
    }

    public void start() {
        boolean userTurn = onboard();
        while (!game.isOver()) {
            printStream.print(game.whoseTurn() + PROMPT);
            if (userTurn) {
                consumeMove();
            } else {
                int move = Minimax.run(game);
                printStream.println(Integer.toString(move));
            }
            userTurn = !userTurn;
            prettyPrint();
        }

        Board.Mark winner = game.getWinner();
        String label = winner == null ? "Nobody" : winner.toString();
        printStream.println(label + " wins!");
    }

    public static void main(String[] args) {
        new Simulation(System.in, System.out).start();
    }
}
