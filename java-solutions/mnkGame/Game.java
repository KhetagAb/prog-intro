package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final IPlayer player1, player2;

    public Game(final boolean log, final IPlayer player1, final IPlayer player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(IBoard board) {
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final IBoard board, final IPlayer player, final int no) {
        while (true) {
            final Move move = player.move(board.getPosition(), board.getTurnCell());
            final Result result = board.makeMove(move);
            log("Player " + no + " move: " + move);
            log(board.toString());
            if (result == Result.WIN) {
                log("Player " + no + " won");
                return no;
            } else if (result == Result.LOSE) {
                log("Player " + no + " lose");
                return 3 - no;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else if (result == Result.UNKNOWN) {
                return -1;
            }
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
