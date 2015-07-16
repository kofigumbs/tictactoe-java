import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Simulation {

    private static final String PRETTY_BOARD =
            "   %c | %c | %c \n" +
                    "  -----------\n" +
                    "   %c | %c | %c \n" +
                    "  -----------\n" +
                    "   %c | %c | %c \n\n";

    private Game game = new Game();
    private Scanner userInputScanner;
    private PrintStream printStream;

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

    Simulation(InputStream userInput, OutputStream outputStream) {
        userInputScanner = new Scanner(userInput);
        printStream = new PrintStream(outputStream);
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public void consumeMove() {
        int move;
        do {
            printStream.print(game.whoseTurn() + " >> ");
            move = userInputScanner.nextInt();
            if (move < 0 || move > Board.CAPACITY)
                printStream.println("Invalid move!");
        } while (move < 0 || move > Board.CAPACITY);
        game.play(move);
    }

    public void prettyPrint() {
        String ugly = getBoard().toString();
        Object[] marks = new Object[Board.CAPACITY];
        for (int i = 0; i < marks.length; i++)
            marks[i] = ugly.charAt(i);
        printStream.format(PRETTY_BOARD, marks);
    }

    public void start() {
        boolean userTurn = onboard();
        while (!game.isOver()) {
            if (userTurn) {
                consumeMove();
            } else {
                Minimax.run(game);
                printStream.println(game.lastMove());
            }
            userTurn = !userTurn;
            prettyPrint();
        }
    }

    public static void main(String[] args) {
        new Simulation(System.in, System.out).start();
    }
}
