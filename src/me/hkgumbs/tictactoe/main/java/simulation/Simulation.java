package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;
import me.hkgumbs.tictactoe.main.java.formatter.ThreeCharacterSlot;
import me.hkgumbs.tictactoe.main.java.player.Human;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.rules.Rules;

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
    private final BoardFormatter formatter;

    public Simulation(
            InputStream userInput, OutputStream outputStream, int padding) {
        cpu = new Minimax(Board.Mark.O);
        human = new Human(Board.Mark.X, userInput, outputStream);
        output = new PrintStream(outputStream);
        formatter = new SquareBoardFormatter(padding, new ThreeCharacterSlot());
        output.println(ONBOARD);
    }

    public State state() {
        return state;
    }

    public State next() {
        if (state == State.INITIAL) {
            board = new SquareBoard(3);
            output.println(formatter.print(board));
            output.print(GO_FIRST);
            state = human.yesOrNo() ? State.HUMAN_TURN : State.CPU_TURN;

        } else if (state == State.HUMAN_TURN) {
            output.print(human.getMark() + PROMPT);
            board = board.add(human.evaluate(board), human.getMark());
            output.println(formatter.print(board));
            state = Rules.gameIsOver(board) ? State.COMPLETED : State.CPU_TURN;

        } else if (state == State.CPU_TURN) {
            int move = cpu.evaluate(board);
            board = board.add(move, cpu.getMark());
            String boardFormat = formatter.print(board);
            output.println(cpu.getMark() + PROMPT + move + "\n" + boardFormat);
            state = Rules.gameIsOver(board) ? State.COMPLETED : State.HUMAN_TURN;

        } else if (state == State.COMPLETED) {
            Board.Mark winner = Rules.determineWinner(board);
            output.println((winner == null ? "Nobody" : winner) + " wins!");
            output.print(PLAY_AGAIN);
            state = human.yesOrNo() ? State.INITIAL : State.TERMINATED;
        }

        return state;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(System.in, System.out, 0);
        try {
            while (simulation.next() != State.TERMINATED) ;
        } catch (NoSuchElementException e) {
            /* user terminated program */
        }
    }
}
