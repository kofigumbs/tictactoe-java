import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SolverTest {

    Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void testFirstMove() {
        board = Solver.move(board, Board.Mark.X);
        assertEquals("X--\n---\n---", board.toString());
    }

//    @Test
//    public void secondMoveIsMiddle() {
//        board = board.add(0, Board.Mark.X);
//        board = Solver.move(board, Board.Mark.O);
//        assertEquals("X--\n-O-\n---", board.toString());
//    }

    @Test
    public void blockWinningPlay() {
        board = board
                .add(0, Board.Mark.X).add(4, Board.Mark.O).add(1, Board.Mark.X);
        board = Solver.move(board, Board.Mark.O);
        assertEquals("XXO\n-O-\n---", board.toString());
    }

    @Test
    public void makeWinningPlay() {
        board = board
                .add(0, Board.Mark.X).add(4, Board.Mark.O)
                .add(1, Board.Mark.X).add(5, Board.Mark.O);
        board = Solver.move(board, Board.Mark.X);
        assertEquals("XXX\n-OO\n---", board.toString());
    }

    @Test
    public void moveScoreComparison() {
        Solver.MoveScore oneTwo = new Solver.MoveScore(1, 2);
        Solver.MoveScore twoThree = new Solver.MoveScore(2, 3);
        assertTrue(oneTwo.compareTo(twoThree) < 0);
        assertTrue(twoThree.compareTo(oneTwo) > 0);
    }

    @Test
    public void score() {
        Board board = new Board()
                .add(0, Board.Mark.X).add(1, Board.Mark.X).add(2, Board.Mark.O)
                .add(3, Board.Mark.X).add(4, Board.Mark.O).add(5, Board.Mark.X)
                .add(6, Board.Mark.O).add(7, Board.Mark.O).add(8, Board.Mark.X);
        assertEquals(10, Solver.score(board, Board.Mark.O, 0));
        assertEquals(-10, Solver.score(board, Board.Mark.X, 0));
    }
}
