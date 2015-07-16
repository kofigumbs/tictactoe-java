import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulationTest {

    ByteArrayInputStream mockUser = new ByteArrayInputStream("".getBytes());

    @Test
    public void newSimulationBoardEmpty() {
        assertTrue(new Simulation(mockUser).getBoard().empty());
    }

    @Test
    public void sendOneMove() {
        String input = "0\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Simulation simulation = new Simulation(inputStream);
        simulation.userMove();
        assertEquals(simulation.getBoard().toString(), "X--\n---\n---");
    }

    @Test
    public void twoUserMoves() {
        String input = "0\n1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Simulation simulation = new Simulation(inputStream);
        simulation.userMove();
        simulation.userMove();
        assertEquals(simulation.getBoard().toString(), "XO-\n---\n---");
    }

}
