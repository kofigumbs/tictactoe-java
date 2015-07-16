import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulationTest {

    ByteArrayOutputStream outputStream;
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
        simulation.userMove();
        assertEquals("X--------", simulation.getBoard().toString());
        simulation.userMove();
        assertEquals("XO-------", simulation.getBoard().toString());
    }

    @Test
    public void fullSimulation() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("y\n0\n2\n5\n".getBytes());
        Simulation simulation = new Simulation(inputStream, outputStream);
        simulation.start();
        assertTrue(simulation.ended());
        assertEquals("XOX-OX-O-", simulation.getBoard().toString());
    }

    @Test
    public void prettyPrintEmpty() {
        String pretty = "   - | - | - " +
                "   - | - | - " +
                "   - | - | - " + "\n";
        new Simulation(emptyInputStream, outputStream).prettyPrint();
        assertEquals(pretty, outputStream.toString());
    }

    @Test
    public void prettyPrintFirstMove() {
        String pretty = "   X | - | - " +
                "   - | - | - " +
                "   - | - | - " + "\n";
        ByteArrayInputStream userInput = new ByteArrayInputStream("0\n".getBytes());
        Simulation simulation = new Simulation(userInput, outputStream);
        simulation.userMove();
        simulation.prettyPrint();
        assertEquals(pretty, outputStream.toString());
    }
}
