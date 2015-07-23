package me.hkgumbs.tictactoe.main.java.simulation;

import me.hkgumbs.tictactoe.main.java.board.Board;
import me.hkgumbs.tictactoe.main.java.board.SquareBoard;
import me.hkgumbs.tictactoe.main.java.formatter.BoardFormatter;
import me.hkgumbs.tictactoe.main.java.formatter.DefaultSlotRepresentation;
import me.hkgumbs.tictactoe.main.java.formatter.SquareBoardFormatter;
import me.hkgumbs.tictactoe.main.java.player.Human;
import me.hkgumbs.tictactoe.main.java.player.Minimax;
import me.hkgumbs.tictactoe.main.java.player.Player;
import me.hkgumbs.tictactoe.main.java.rules.Rules;
import me.hkgumbs.tictactoe.main.java.rules.DefaultRules;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class DefaultSimulation implements Simulation {


    public enum State {
        INITIAL, HUMAN_TURN, CPU_TURN, COMPLETED, TERMINATED;
    }

    private static final String PROMPT = " >> ";
    private static final String GO_FIRST = "Would you like to go first? (y/n) ";

    private static final String ONBOARD = "Welcome to Tic Tac Toe!\n" +
            "Make a move by entering an empty space id\n";

    private static final String PLAY_AGAIN =
            "Would you like to play again? (y/n) ";

    private State state = State.INITIAL;
    private Board board;

    private int size = 3;
    private Player[] players;
    private BoardFormatter formatter =
            new SquareBoardFormatter(3, new DefaultSlotRepresentation());
    private Rules rules = new DefaultRules(3);

    private Player cpu;
    private Player human;
    private PrintStream output;

    public DefaultSimulation(
            InputStream userInput, OutputStream outputStream) {
        cpu = new Minimax(Board.Mark.O);
        human = new Human(Board.Mark.X, userInput, outputStream);
        output = new PrintStream(outputStream);
        players = new Player[]{human, cpu};
        output.println(ONBOARD);
    }

    public State getState() {
        return state;
    }

    public State next() {
        if (state == State.INITIAL) {
            board = new SquareBoard(size);
            output.println(formatter.print(board));
            output.print(GO_FIRST);
            state = human.yesOrNo() ? State.HUMAN_TURN : State.CPU_TURN;

        } else if (state == State.HUMAN_TURN) {
            output.print(human.getMark() + PROMPT);
            board = board.add(human.determineNextMove(board), human.getMark());
            output.println(formatter.print(board));
            state = rules.gameIsOver(board) ? State.COMPLETED : State.CPU_TURN;

        } else if (state == State.CPU_TURN) {
            int move = cpu.determineNextMove(board);
            board = board.add(move, cpu.getMark());
            String boardFormat = formatter.print(board);
            output.println(cpu.getMark() + PROMPT + move + "\n" + boardFormat);
            state = rules.gameIsOver(board) ? State.COMPLETED : State.HUMAN_TURN;

        } else if (state == State.COMPLETED) {
            Board.Mark winner = rules.determineWinner(board);
            output.println((winner == null ? "Nobody" : winner) + " wins!");
            output.print(PLAY_AGAIN);
            state = human.yesOrNo() ? State.INITIAL : State.TERMINATED;
        }

        return state;
    }

    @Override
    public Player[] getPlayers() {
        return this.players;
    }

    public int getSize() {
        return size;
    }

    @Override
    public BoardFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setPlayers(Player... players) {
        this.players = players;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void setFormatter(BoardFormatter formatter) {
        this.formatter = formatter;
    }
}
