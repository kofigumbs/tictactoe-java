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
        game.play(6);
        Solver.move(game);
        assertEquals("X--\nOO-\nX--", game.getBoard().toString());
    }

    @Test
    public void xMakeWinningPlay() {
        game.play(0);
        game.play(1);
        game.play(4);
        game.play(5);
        Solver.move(game);
        assertEquals("XO-\n-XO\n--X", game.getBoard().toString());
    }

    @Test
    public void oMakeWinningPlay() {
        game.play(0);
        game.play(2);
        game.play(1);
        game.play(5);
        game.play(3);
        Solver.move(game);
        assertEquals("XXO\nX-O\n--O", game.getBoard().toString());
    }

    @Test
    public void twoSolversTie() {
        while (!game.isOver())
            Solver.move(game);
        System.out.println(game.getBoard());
        assertNull(game.getWinner());
        assertTrue(game.getBoard().full());
    }
}
