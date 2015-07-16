import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Simulation {

    private Game game = new Game();
    private Scanner userInputScanner;

    Simulation(InputStream userInput) {
        userInputScanner = new Scanner(userInput);
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public void userMove(){
        int position = userInputScanner.nextInt();
        game.play(position);
    }

    public void start(OutputStream outputSream) {
        PrintStream printStream = new PrintStream(outputSream);
        printStream.print("Would you like to go first? (y/n) ");
        boolean userTurn = userInputScanner.next().toLowerCase().startsWith("y");
        while (!game.isOver()) {
            if (userTurn)
                userMove();
            else
                Minimax.run(game);
            userTurn = !userTurn;
            printStream.println(game.getBoard() + "\n");
        }
        printStream.println(game.getBoard());
    }

    public boolean ended() {
        return true;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(System.in);
        simulation.start(System.out);
    }
}
