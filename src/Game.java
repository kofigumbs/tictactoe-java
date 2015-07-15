import java.util.Stack;

public class Game {

    private Board.Mark next = Board.Mark.X;
    private Stack<Integer> history = new Stack<>();
    private Board board = new Board();

    private Board.Mark other(Board.Mark mark) {
        if (mark == Board.Mark.X)
            return Board.Mark.O;
        else
            return Board.Mark.X;
    }

    public boolean isOver() {
        return false;
    }

    public void play(int position) {
        history.push(position);
        board = board.add(position, next);
        next = other(next);
    }

    public String lastMove() {
        return other(next) + " >> " + history.peek();
    }

    public Board getBoard() {
        return board;
    }

    public Board.Mark whoseTurn() {
        return next;
    }
}
