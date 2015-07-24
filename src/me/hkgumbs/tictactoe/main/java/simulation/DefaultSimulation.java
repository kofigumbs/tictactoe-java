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

    private static final String PROMPT = " >> ";


    private State state = State.INITIAL;
    private Board board;

    private int size;
    private Player[] players;
    private BoardFormatter formatter =
            new SquareBoardFormatter(3, new DefaultSlotRepresentation());
    private Rules rules = new DefaultRules(3);
    int turn = 0;

    private PrintStream output;

    public DefaultSimulation(
            InputStream userInput, OutputStream outputStream) {
        Player cpu = new Minimax(Board.Mark.O);
        Player human = new Human(Board.Mark.X, userInput, outputStream);
        output = new PrintStream(outputStream);
        players = new Player[]{human, cpu};
        for (Player player : players)
            player.onboard();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public State nextState() {
        if (state == State.INITIAL) {
            board = new SquareBoard(size);
            output.println(formatter.print(board));
            state = State.IN_PROGRESS;

        } else if (state == State.IN_PROGRESS) {
            Player player = players[turn];
            int move = player.determineNextMove(board);
            board = board.add(move, player.getMark());
            state = rules.gameIsOver(board) ?  State.COMPLETED : State.IN_PROGRESS;
            turn = (turn + 1) % players.length;

        } else if (state == State.COMPLETED) {
            Board.Mark winner = rules.determineWinner(board);
            output.println((winner == null ? "Nobody" : winner) + " wins!");
            boolean playAgain = true;
            for (Player player : players)
                playAgain &= player.playAgain();
            state = playAgain ? State.INITIAL : State.TERMINATED;
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
