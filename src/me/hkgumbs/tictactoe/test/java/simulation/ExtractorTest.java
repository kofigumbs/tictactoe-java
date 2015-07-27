package me.hkgumbs.tictactoe.test.java.simulation;

import me.hkgumbs.tictactoe.main.java.simulation.Extractor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ExtractorTest {
    @Test
    public void extractTen() {
        List<String> args = new ArrayList<>(Arrays.asList("--key", "10"));
        assertEquals(10, Extractor.parseInt(args, "--key", 5));
        assertEquals(0, args.size());
    }

    @Test
    public void extractFiftyFive() {
        List<String> args = new ArrayList<>(Arrays.asList("--key", "55"));
        assertEquals(55, Extractor.parseInt(args, "--key", 5));
        assertEquals(0, args.size());
    }

    @Test(expected = NumberFormatException.class)
    public void numberFormateException() {
        List<String> args = new ArrayList<>(Arrays.asList("--key", "asdf"));
        assertEquals(55, Extractor.parseInt(args, "--key", 5));
        fail("Should have thrown NumberFormatException");
    }

    @Test
    public void defaultValue() {
        List<String> args = Arrays.asList("--someOtherConfiguration");
        assertEquals(5, Extractor.parseInt(args, "--key", 5));
        assertEquals(1, args.size());
    }
}
