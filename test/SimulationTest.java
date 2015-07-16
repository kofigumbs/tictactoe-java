import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulationTest {

    @Test
    public void newSimulationBoardEmpty() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes());
        assertTrue(new Simulation(inputStream).getBoard().empty());
    }

    @Test
    public void userMoves() {
        String input = "0\n1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Simulation simulation = new Simulation(inputStream);
        simulation.userMove();
        assertEquals("X--------", simulation.getBoard().toString());
        simulation.userMove();
        assertEquals("XO-------", simulation.getBoard().toString());
    }

    @Test
    public void fullSimulation() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("y\n0\n2\n5\n".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Simulation simulation = new Simulation(inputStream);
        simulation.start(outputStream);
        assertTrue(simulation.ended());
        assertEquals("XOX-OX-O-", simulation.getBoard().toString());
    }
}
