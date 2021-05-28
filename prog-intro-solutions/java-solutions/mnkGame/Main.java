package mnkGame;

import java.util.function.Predicate;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    private final static boolean LOGGING = true;
    private final static int SKIP_COND = 4;

    private static IController gc;

    private static int getIntInput(String name, Predicate<Integer> validator) {
        while (true) {
            int input = gc.intInputFor(name);
            if (validator.test(input)) {
                return input;
            } else {
                gc.showMsg("Invalid " + name + " parameter.");
            }
        }
    }

    public static void main(String[] args) {
        gc = new GameController(System.out, System.in);

        int n, m, k;
        int countOfGames, firstPlayerType, secondPlayerType, boardType;

        Predicate<Integer> isPositive = x -> x > 0;
        Predicate<Integer> inRange = x -> 0 <= x && x <= 2;

        try {
            gc.showMsg("(Board types: 0 - Rectangle, 1 - Rhombus, 2 - Circle)");
            boardType = getIntInput("board type", inRange);

            if (boardType == 0) {
                n = getIntInput("n", isPositive);
                m = getIntInput("m", isPositive);
            } else {
                m = n = getIntInput("side", isPositive);
            }
            k = getIntInput("k", isPositive);
            countOfGames = getIntInput("count of games", isPositive);

            gc.showMsg("(Player types: 0 - HumanPlayer, 1 - SequentialPlayer, 2 - RandomPlayer)");
            firstPlayerType = getIntInput("first player type", inRange);
            secondPlayerType = getIntInput("second player type", inRange);
        } catch (IllegalStateException e) {
            gc.showMsg("Input error: " + e.getMessage() + ".");
            exit();
            return;
        }

        IPlayer firstPlayer = selectPlayerType(firstPlayerType);
        IPlayer secondPlayer = selectPlayerType(secondPlayerType);

        final Game game = new Game(LOGGING, firstPlayer, secondPlayer);

        int firstWins = 0, draws = 0, firstLoses = 0;
        for (int i = 0; i < countOfGames; i++) {
            int result;
            try {
                result = game.play(selectBoardType(boardType, n, m, k));
            } catch (IllegalStateException | OutOfMemoryError e) {
                gc.showMsg("Error during game: " + e.getMessage() + ".");
                exit();
                return;
            }

            draws += result == 0 ? 1 : 0;
            firstWins += result == 1 ? 1 : 0;
            firstLoses += result == 2 ? 1 : 0;
        }

        gc.showMsg("Count: " + countOfGames + ". First's wins: " + firstWins + ". First loses: " + firstLoses + ". Draws: " + draws);

        exit();
    }

    private static void exit() {
        gc.close();
    }

    private static IPlayer selectPlayerType(int playerType) {
        switch (playerType) {
            case 1:
                return new SequentialPlayer();
            case 2:
                return new RandomPlayer();
            default:
                return new HumanPlayer();
        }
    }

    private static IBoard selectBoardType(int boardType, int n, int m, int k) {
        switch (boardType) {
            case 1:
                return new RhombusMnkBoard(n, k, SKIP_COND);
            case 2:
                return new CircleMnkBoard(n, k, SKIP_COND);
            default:
                return new SquareMnkBoard(n, m, k, SKIP_COND);
        }
    }
}
