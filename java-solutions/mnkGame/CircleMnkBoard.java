package mnkGame;

public class CircleMnkBoard extends MnkBoard {
    public CircleMnkBoard(int side, int winCntCond, int bonusCntCond) {
        super(2 * side - 1, 2 * side - 1, winCntCond, bonusCntCond,
                ((row, col) ->  (col - side + 1) * (col - side + 1) + (row - side + 1) * (row - side + 1) <= (side - 1)* (side - 1)));
    }
}
