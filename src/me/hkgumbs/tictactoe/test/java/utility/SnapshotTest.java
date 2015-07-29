package me.hkgumbs.tictactoe.test.java.utility;

import me.hkgumbs.tictactoe.main.java.utility.Snapshot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SnapshotTest {
    public class MockObject {
        public int intField = 5;
        public Object objectField = new Object();
        public String[] arrayField = {"Hello", "World"};
    }

    private MockObject mock;
    private Snapshot snapshot;

    @Before
    public void setup() {
        mock = new MockObject();
        snapshot = new Snapshot(mock);
    }

    @Test
    public void notModified() {
        assertEquals(snapshot, new Snapshot(mock));
    }

    @Test
    public void nullNotEqual() {
        assertNotEquals(snapshot, null);
    }

    @Test
    public void differentClassNotEqual() {
        assertNotEquals(snapshot, new Snapshot(new Object()));
    }

    @Test
    public void modifyIntField() {
        mock.intField = 2;
        assertNotEquals(snapshot, new Snapshot(mock));
    }

    @Test
    public void modifyObjectField() {
        mock.objectField = new Object();
        assertNotEquals(snapshot, new Snapshot(mock));
    }

    @Test
    public void modifyArrayField() {
        mock.arrayField[1] = "modification";
        assertNotEquals(snapshot, new Snapshot(mock));
    }
}
