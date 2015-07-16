import java.io.InputStream;
import java.util.Scanner;

public class Simulation {

    private Game game = new Game();
    private Scanner inputScanner;

    Simulation(InputStream userInput) {
        inputScanner = new Scanner(userInput);
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public void userMove(){
        int position = inputScanner.nextInt();
        game.play(position);
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(System.in);
        int i = 0;
        while (!simulation.game.getBoard().isGameOver()) {
            System.out.println(simulation.getBoard());
            if (i % 2 == 0) {
                simulation.userMove();
            }
            else {
                Minimax.run(simulation.game);
            }
        }
        System.out.println(simulation.getBoard());
    }

}
