package me.hkgumbs.main.java.tictactoe;

import java.util.Arrays;

public class Mark {
    public static Mark O = new Mark("O");
    public static Mark X = new Mark("X");

    private final String value;

    private Mark(String value) {
        this.value = value;
    }

    public Mark other() {
        return this == X ? O : X;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Iterable<Mark> values() {
        return Arrays.asList(X, O);
    }
}
