package me.hkgumbs.main.java.tictactoe;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class Simulation {

    public enum State {
        INITIAL, HUMAN_TURN, CPU_TURN, COMPLETED, TERMINATED
    }

    private static final String PROMPT = " >> ";
    private static final String GO_FIRST = "Would you like to go first? (y/n) ";
    private static final String ONBOARD = "Welcome to Tic Tac Toe!\n" +
            "Make a move by entering an empty space id\n";
    private static final String PLAY_AGAIN =
            "Would you like to play again? (y/n) ";

    private State state = State.INITIAL;
    private Board board;
    private Player cpu;
    private Player human;
    private PrintStream output;

    public Simulation(InputStream userInput, OutputStream outputStream) {
        cpu = new Minimax(Board.Mark.O);
        human = new Human(Board.Mark.X, userInput, outputStream);
        output = new PrintStream(outputStream);
        output.println(ONBOARD);
    }

    public State state() {
        return state;
    }

    public State next() {
        if (state == State.INITIAL) {
            board = new Board();
            output.println(board.format());
            output.print(GO_FIRST);
            state = human.yesOrNo() ? State.HUMAN_TURN : State.CPU_TURN;

        } else if (state == State.HUMAN_TURN) {
            output.print(human.mark + PROMPT);
            board = board.add(human.consider(board), human.mark);
            output.println(board.format());
            state = Game.over(board) ? State.COMPLETED : State.CPU_TURN;

        } else if (state == State.CPU_TURN) {
            int move = cpu.consider(board);
            board = board.add(move, cpu.mark);
            output.println(cpu.mark + PROMPT + move + "\n" + board.format());
            state = Game.over(board) ? State.COMPLETED : State.HUMAN_TURN;

        } else if (state == State.COMPLETED) {
            Board.Mark winner = Game.winner(board);
            output.println((winner == null ? "Nobody" : winner) + " wins!");
            output.print(PLAY_AGAIN);
            state = human.yesOrNo() ? State.INITIAL : State.TERMINATED;
        }

        return state;
    }

    public static void main(String[] args) {
        try {
            Simulation simulation = new Simulation(System.in, System.out);
            while (simulation.next() != State.TERMINATED) ;
        } catch (NoSuchElementException e) {
            /* user terminated program */
        }
    }
}
