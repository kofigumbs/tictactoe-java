import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MinimaxTest {

    Game game;

    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void testFirstMove() {
        Minimax.run(game);
        assertEquals("X--------", game.getBoard().toString());
    }

    @Test
    public void blockWinningPlay() {
        game.play(0);
        game.play(4);
        game.play(6);
        Minimax.run(game);
        assertEquals("X--OO-X--", game.getBoard().toString());
    }

    @Test
    public void xMakeWinningPlay() {
        game.play(0);
        game.play(1);
        game.play(4);
        game.play(5);
        Minimax.run(game);
        assertEquals("XO--XO--X", game.getBoard().toString());
    }

    @Test
    public void oMakeWinningPlay() {
        game.play(0);
        game.play(2);
        game.play(1);
        game.play(5);
        game.play(3);
        Minimax.run(game);
        assertEquals("XXOX-O--O", game.getBoard().toString());
    }

    @Test
    public void twoSolversTie() {
        while (!game.isOver())
            Minimax.run(game);
        assertNull(game.getWinner());
        assertTrue(game.getBoard().full());
    }
}
