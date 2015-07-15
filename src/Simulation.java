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

    public Board.Mark nextTurn() {
        return game.whoseTurn();
    }

    public void userMove(){
        int position = inputScanner.nextInt();
        game.play(position);
    }

}
