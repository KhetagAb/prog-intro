package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        int n = 10, m = 5, k = 8;

        int countOfGames = 1;
        final Game game = new Game(true, new HumanPlayer(), new RandomPlayer());

        int firstWins = 0, draws = 0, firstLoses = 0;
        for (int i = 0; i < countOfGames; i++) {
            int result = game.play(new RhombusMnkBoard(n, k, 4));

            draws += result == 0 ? 1 : 0;
            firstWins += result == 1 ? 1 : 0;
            firstLoses += result == 2 ? 1 : 0;
        }

        System.out.println("Count: " + countOfGames + ". First's wins: " + firstWins + ". First loses: " + firstLoses + ". Draws: " + draws);
    }
}
