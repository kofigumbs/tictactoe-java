import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void emptyWhenCreated() {
        assertTrue(new Board().isEmpty());
    }

    @Test
    public void oneMoveNotEmpty() {
        Board board = new Board();
        board = board.add(0, 0, "X");
        assertFalse(board.isEmpty());
    }

    @Test
    public void emptyToString() {
        assertEquals("---\n---\n---", new Board().toString());
    }

    @Test
    public void oneMoveToString() {
        Board board = new Board();
        board = board.add(0, 0, "X");
        assertEquals("X--\n---\n---", board.toString());
    }

    @Test
    public void twoMoveToString() {
        Board board = new Board();
        board = board.add(0, 0, "X").add(0, 1, "O");
        assertEquals("XO-\n---\n---", board.toString());
    }

    @Test
    public void emptyIsFreeAt() {
        assertTrue(new Board().isFreeAt(0, 0));
    }

    @Test
    public void isNotFreeAt() {
        Board board = new Board();
        board = board.add(0, 0, "X");
        assertFalse(board.isFreeAt(0, 0));
    }

    @Test
    public void immutableBoard() {
        Board board = new Board();
        board = board.add(0, 0, "X");
        assertFalse(board.isEmpty());
    }
}
