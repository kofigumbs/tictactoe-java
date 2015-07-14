import java.io.InputStream;
import java.util.Scanner;

public class Simulation {

    private Board board = new Board();
    private Scanner inputScanner = new Scanner(System.in);

    public Board getBoard() {
        return board;
    }

    public String nextTurn() {
        return "X";
    }

    public void setInput(InputStream inputStream) {
        inputScanner = new Scanner(inputStream);
    }

    public void userMove(){
        int row = inputScanner.nextInt();
        int column = inputScanner.nextInt();
        board = board.add(row, column, nextTurn());
    }

}
