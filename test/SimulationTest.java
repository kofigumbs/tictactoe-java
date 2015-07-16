import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulationTest {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ByteArrayInputStream emptyInputStream
            = new ByteArrayInputStream("".getBytes());

    @Before
    public void setup() {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void newSimulationBoardEmpty() {
        assertTrue(new Simulation(emptyInputStream, outputStream).getBoard().empty());
    }

    @Test
    public void userMoves() {
        String input = "0\n1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.consumeMove();
        assertEquals("X--------", simulation.getBoard().toString());
        simulation.consumeMove();
        assertEquals("XO-------", simulation.getBoard().toString());
        assertEquals(-1, inputStream.read());
    }

    @Test
    public void fullSimulation() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("y\n0\n2\n5\n".getBytes());
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.start();
        assertEquals("XOX-OX-O-", simulation.getBoard().toString());
        assertEquals(-1, inputStream.read());
    }

    @Test
    public void moveOutOfRange() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("-1\n99\n4".getBytes());
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.consumeMove();
        assertEquals("----X----", simulation.getBoard().toString());
        assertEquals(-1, inputStream.read());
    }

    @Test
    public void moveOnOccupiedSpace() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("4\n4\n5\n".getBytes());
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.consumeMove();
        simulation.consumeMove();
        assertEquals("----XO---", simulation.getBoard().toString());
        assertEquals(-1, inputStream.read());
    }

}
