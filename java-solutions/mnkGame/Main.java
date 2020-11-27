package mnkGame;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    private final static boolean LOGGING = true;

    public static void main(String[] args) {
        int n, m, k;
        int countOfGames, firstPlayerType, secondPlayerType, boardType;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter n, m, k, <countOfGames>, <firstPlayerType>, <secondPlayerType>, <boardType> parameters: ");
        while (true) {
            System.out.println("(Player types: 0 - HumanPlayer, 1 - SequentialPlayer, 2 - RandomPlayer)");
            System.out.println("(Board types: 0 - Square, 1 - Rhombus, 2 - Circle)");

            try {
                // :NOTE: Копипаста
                try (Scanner tsc = new Scanner(sc.nextLine())) {
                    n = Integer.parseInt(tsc.next());
                    m = Integer.parseInt(tsc.next());
                    k = Integer.parseInt(tsc.next());
                    countOfGames = Integer.parseInt(tsc.next());
                    firstPlayerType = Integer.parseInt(tsc.next());
                    secondPlayerType = Integer.parseInt(tsc.next());
                    boardType = Integer.parseInt(tsc.next());
                }
            } catch (NumberFormatException e) {
                System.out.println("Parameters must be integers.");
                continue;
            } catch (NoSuchElementException e) {
                System.out.println("Too few type parameters.");
                continue;
            }

            if (countOfGames <= 0) {
                System.out.println("Invalid count of games!");
            } else if (firstPlayerType < 0 || firstPlayerType > 2 || secondPlayerType < 0 || secondPlayerType > 2) {
                System.out.println("Invalid player types!");
            } else if (boardType < 0 || boardType > 2) {
                System.out.println("Invalid board type!");
            } else if (n <= 0 || m <= 0 || k < 0) {
                System.out.println("Invalid board parameters!");
            } else if ((boardType == 1 || boardType == 2) && n != m) {
                System.out.println("This board type support only square size board (n = m)");
            } else {
                break;
            }
        }

        IPlayer firstPlayer = selectPlayerType(firstPlayerType);
        IPlayer secondPlayer = selectPlayerType(secondPlayerType);

        final Game game = new Game(LOGGING, firstPlayer, secondPlayer);

        int firstWins = 0, draws = 0, firstLoses = 0;
        for (int i = 0; i < countOfGames; i++) {
            int result = game.play(selectBoardType(boardType, n, m, k));

            draws += result == 0 ? 1 : 0;
            firstWins += result == 1 ? 1 : 0;
            firstLoses += result == 2 ? 1 : 0;
        }

        System.out.println("Count: " + countOfGames + ". First's wins: " + firstWins + ". First loses: " + firstLoses + ". Draws: " + draws);
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
        IBoard board;
        switch (boardType) {
            case 1:
                board = new RhombusMnkBoard(n, k, 4);
                break;
            case 2:
                board = new CircleMnkBoard(n, k, 4);
                break;
            default:
                board = new SquareMnkBoard(n, m, k, 4);
                break;
        }
        return board;
    }
}
