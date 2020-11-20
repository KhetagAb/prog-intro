package mnkGame;

public class RhombusMnkBoard extends MnkBoard {
    public RhombusMnkBoard(int side, int winCntCond, int bonusCntCond) {
        super(2 * side - 1, 2 * side - 1, winCntCond, bonusCntCond,
                ((row, col) ->  Math.abs(side - col - 1) + Math.abs(side - row - 1) <= side - 1));
    }
}