package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface IPosition {
    boolean isValid(Move move);

    int getCols();
    int getRows();
    int getWinCond();

    Cell getCell(int row, int col);
}
