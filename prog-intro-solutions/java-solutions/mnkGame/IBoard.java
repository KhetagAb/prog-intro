package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface IBoard {
    IPosition getPosition();
    Cell getTurnCell();
    Result makeMove(Move move);
}
