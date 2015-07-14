import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulationTest {

    @Test
    public void newSimulationBoardEmpty() {
        assertTrue(new Simulation().getBoard().isEmpty());
    }

    @Test
    public void firstMoveX() {
        assertEquals("X", new Simulation().nextTurn());
    }

    @Test
    public void sendOneMove() {
        String input = "0\n0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Simulation simulation = new Simulation();
        simulation.setInput(inputStream);
        simulation.userMove();
        assertEquals(simulation.getBoard().toString(), "X--\n---\n---");
    }
}
