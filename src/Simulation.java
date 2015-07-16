import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    private Game game = new Game();
    private Scanner userInputScanner;
    private OutputStream outputStream;

    Simulation(InputStream userInput, OutputStream outputStream) {
        userInputScanner = new Scanner(userInput);
        this.outputStream = outputStream;
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public void userMove() {
        int position = userInputScanner.nextInt();
        game.play(position);
    }

    public void start() {
        PrintStream printStream = new PrintStream(outputStream);
        printStream.print("Would you like to go first? (y/n) ");
        boolean userTurn = userInputScanner.next().toLowerCase().startsWith("y");
        while (!game.isOver()) {
            if (userTurn)
                userMove();
            else
                Minimax.run(game);
            userTurn = !userTurn;
            prettyPrint();
            printStream.println();
        }
        printStream.println(game.getBoard());
    }

    public boolean ended() {
        return true;
    }

    public void prettyPrint() {
        char[] ugly = getBoard().toString().toCharArray();
        List<Object> marks = new ArrayList<>(Board.CAPACITY);
        for (char mark : ugly)
            marks.add(mark);
        PrintStream printStream = new PrintStream(outputStream);
        String pretty = "   %c | %c | %c " +
                "   %c | %c | %c " +
                "   %c | %c | %c " + "\n";
        printStream.format(pretty, marks.toArray());
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(System.in, System.out);
        simulation.start();
    }
}
