import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SolverTest {

    Game game;

    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void testFirstMove() {
        Solver.move(game);
        assertEquals("X--\n---\n---", game.getBoard().toString());
    }

    @Test
    public void blockWinningPlay() {
        game.play(0);
        game.play(4);
        game.play(1);
        Solver.move(game);
        assertEquals("XXO\n-O-\n---", game.getBoard().toString());
    }

    @Test
    public void makeWinningPlay() {
        game.play(0);
        game.play(4);
        game.play(1);
        game.play(5);
        Solver.move(game);
        assertEquals("XXX\n-OO\n---", game.getBoard().toString());
    }

    @Test
    public void moveScoreComparison() {
        Solver.MoveScore oneTwo = new Solver.MoveScore(1, 2);
        Solver.MoveScore twoThree = new Solver.MoveScore(2, 3);
        assertTrue(oneTwo.compareTo(twoThree) < 0);
        assertTrue(twoThree.compareTo(oneTwo) > 0);
    }

    @Test
    public void twoSolversTie() {
        while (game.getBoard().isGameOver())
            Solver.move(game);
        assertNull(game.getWinner());
    }
}

