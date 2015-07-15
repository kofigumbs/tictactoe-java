import java.io.InputStream;
import java.util.Scanner;

public class Simulation {

    private Board board = new Board();
    private Scanner inputScanner = new Scanner(System.in);
    private Board.Mark nextTurn = Board.Mark.X;

    public Board getBoard() {
        return board;
    }

    public Board.Mark nextTurn() {
        return nextTurn;
    }

    public void setInput(InputStream inputStream) {
        inputScanner = new Scanner(inputStream);
    }

    public void userMove(){
        int position = inputScanner.nextInt();
        board = board.add(position, nextTurn());
        nextTurn = nextTurn.equals(Board.Mark.X) ? Board.Mark.O : Board.Mark.X;
    }

}
