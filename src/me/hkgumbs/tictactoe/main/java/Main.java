package me.hkgumbs.tictactoe.main.java;

import me.hkgumbs.tictactoe.main.java.simulation.DefaultSimulation;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        DefaultSimulation simulation = new DefaultSimulation(System.in, System.out);
        try {
            while (simulation.next() != DefaultSimulation.State.TERMINATED) ;
        } catch (NoSuchElementException e) {
            /* user terminated program */
        }
    }
}
