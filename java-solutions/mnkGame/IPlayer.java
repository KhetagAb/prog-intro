package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface IPlayer {
    Move move(IPosition position, Cell cell);
}
