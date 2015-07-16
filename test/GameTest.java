import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    Game game;

    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void newGameIsNotOver() {
        assertFalse(game.isOver());
    }

    @Test
    public void playOnceNotOver() {
        game.play(0);
        assertFalse(game.isOver());
    }

    @Test
    public void getBoard() {
        game.play(0);
        game.play(1);
        assertEquals("XO-------", game.getBoard().toString());
    }

    @Test
    public void startOnX() {
        assertEquals(Board.Mark.X, game.whoseTurn());
    }

    @Test
    public void secondMoveO() {
        game.play(0);
        assertEquals(Board.Mark.O, game.whoseTurn());
    }

    @Test
    public void isActuallyOver() {
        game.play(0);
        game.play(3);
        game.play(1);
        game.play(4);
        game.play(2);
        assertTrue(game.isOver());
    }

    @Test
    public void catsGame() {
        game.play(0);
        game.play(4);
        game.play(1);
        game.play(2);
        game.play(6);
        game.play(3);
        game.play(5);
        game.play(8);
        game.play(7);
        assertTrue(game.isOver());
    }
    @Test
    public void copyConstructor() {
        game.play(0);
        Game copy = new Game(game);
        assertEquals(Board.Mark.O, copy.whoseTurn());
        copy.play(1);
        assertEquals(Board.Mark.X, copy.whoseTurn());
        assertEquals(Board.Mark.O, game.whoseTurn());
    }
    @Test
    public void moveOnOccupiedSpace() {
        game.play(4);
        assertFalse(game.play(4));
        assertEquals("----X----", game.getBoard().toString());
    }
}
