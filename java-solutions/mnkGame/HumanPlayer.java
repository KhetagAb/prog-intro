package mnkGame;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements IPlayer {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final IPosition position, final Cell cell) {
        while (true) {
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column on a new line (for example \"1 1\"):");

            final Move move;
            try {
                // :NOTE: Управление на исключениях
                try (Scanner sc = new Scanner(in.nextLine())) {
                    move = new Move(sc.nextInt() - 1, sc.nextInt() - 1, cell);
                }
            } catch (InputMismatchException e) {
                out.println("Row and column must be integers!");
                continue;
            } catch (NoSuchElementException e) {
                out.println("There should be 2 integers!");
                continue;
            }

            if (position.isValid(move)) {
                return move;
            }

            out.println("Move " + move + " is invalid");
        }
    }
}
