package me.hkgumbs.tictactoe.main.java.utility;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Snapshot {
    private final int hashCode;

    private Snapshot(Object object) {
        Field[] fields = object.getClass().getFields();
        Object[] values = new Object[fields.length];
        for (int i = 0; i < fields.length; i++)
            try {
                values[i] = fields[i].get(object);
            } catch (IllegalAccessException e) {
            }
        hashCode = Arrays.deepHashCode(values);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Snapshot))
            return false;
        return hashCode == ((Snapshot) obj).hashCode;
    }

    public static Snapshot of(Object object) {
        return new Snapshot(object);
    }
}
