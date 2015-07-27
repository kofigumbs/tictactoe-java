package me.hkgumbs.tictactoe.main.java.simulation;

import java.util.List;

public class Extractor {
    public static int parseInt(List<String> args, String key, int defaultValue)
            throws NumberFormatException {
        int index = args.indexOf(key);
        if (index == -1)
            return defaultValue;
        String sizeString = args.get(index + 1);
        args.remove(index);
        args.remove(index);
        return Integer.valueOf(sizeString);
    }
}
