import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    Validator validator;
    Board board;

    @Before
    public void setup() {
        validator = new Validator();
        board = new Board();
    }

    @Test
    public void emptySpaceIsValid() {
        assertTrue(validator.checkMove(board, 0, Board.Mark.X));
    }

    @Test
    public void spaceAlreadyTaken() {
        board = board.add(0, Board.Mark.X);
        assertFalse(validator.checkMove(board, 0, Board.Mark.O));
    }

    @Test
    public void simpleGameOver() {
        assertFalse(validator.gameOver(board));
    }

    @Test
    public void filledBoardGameOver() {
        board = board
                .add(0, Board.Mark.X).add(1, Board.Mark.O).add(2, Board.Mark.X)
                .add(3, Board.Mark.O).add(4, Board.Mark.X).add(5, Board.Mark.O)
                .add(6, Board.Mark.X).add(7, Board.Mark.O).add(8, Board.Mark.X);
        assertTrue(validator.gameOver(board));
    }
    @Test
    public void winner() {
        board = board
                .add(0, Board.Mark.X).add(1, Board.Mark.O).add(2, Board.Mark.X)
                .add(3, Board.Mark.O).add(4, Board.Mark.X).add(5, Board.Mark.O)
                .add(6, Board.Mark.X).add(7, Board.Mark.O).add(8, Board.Mark.X);
        assertEquals(Board.Mark.X, validator.winner(board));
    }

    @Test
    public void catsGame() {
        board = board
                .add(0, Board.Mark.X).add(1, Board.Mark.X).add(2, Board.Mark.O)
                .add(3, Board.Mark.O).add(4, Board.Mark.O).add(5, Board.Mark.X)
                .add(6, Board.Mark.X).add(7, Board.Mark.O).add(8, Board.Mark.X);
        assertEquals(null, validator.winner(board));
        assertTrue(validator.gameOver(board));
    }
}
