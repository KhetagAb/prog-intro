package mnkGame;

import java.util.Arrays;
import java.util.Map;

public class MnkBoard implements IBoard, IPosition {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private final int rows, cols, winCntCond, skipCntCond;
    private final isInFieldChecker isInFieldChecker;

    private int fullCells = 0;
    private Cell turn;

    public MnkBoard(int rows, int cols, int winCntCond, int skipCntCond, isInFieldChecker isInFieldChecker) {
        this.rows = rows;
        this.cols = cols;
        this.winCntCond = winCntCond;
        this.skipCntCond = skipCntCond;
        this.cells = new Cell[rows][cols];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.isInFieldChecker = isInFieldChecker;
    }

    private int countInDirection(Move move, int x, int y) {
//        for (
//                int curRow = move.getRow(), curCol = move.getColumn();
//                getCell(curRow, curCol) == move.getValue();
//                curRow += x, curCol += y, cnt++
//        ) { /*empty*/ }
        int curRow = move.getRow(), curCol = move.getColumn();

        int cnt = 0;
        while (getCell(curRow, curCol) == move.getValue()) {
            curRow += x;
            curCol += y;
            cnt++;
        }

        return cnt;
    }

    private int longestInDir(Move move, int x, int y) {
        return countInDirection(move, x, y) + countInDirection(move, -x, -y) - 1;
    }

    @FunctionalInterface
    public interface isInFieldChecker {
        boolean test(int row, int col);
    }

    @Override
    public boolean isValid(Move move) {
        return move != null &&
                getCell(move.getRow(), move.getColumn()) == Cell.E &&
                turn == move.getValue();
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        int maxInAnyLine = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x != 0 || y != 0)) {
                    // :NOTE: Меньше проверок
                    maxInAnyLine = Integer.max(maxInAnyLine, longestInDir(move, x, y));
                }
            }
        }

        if (maxInAnyLine >= winCntCond) {
            return Result.WIN;
        }

        fullCells++;
        if (fullCells == cols * rows) {
            return Result.DRAW;
        }

        if (maxInAnyLine >= skipCntCond) {
            return Result.SKIP;
        } else {
            turn = turn == Cell.X ? Cell.O : Cell.X;
        }

        return Result.UNKNOWN;
    }

    @Override
    public String toString() {
        int colsTitleWidth = Integer.toString(cols).length();
        int rowsTitleLength = Integer.toString(rows).length();
        final StringBuilder sb = new StringBuilder();

        sb.append("POSITION (win condition: ").append(winCntCond)
                .append(", bonus turn condition: ").append(skipCntCond).append("):").append(System.lineSeparator())
                .append(" ".repeat(rowsTitleLength));
        for (int col = 1; col <= cols; col++) {
            sb.append(" ".repeat(colsTitleWidth + 1 - Integer.toString(col).length()))
                    .append(col);
        }

        for (int row = 0; row < rows; row++) {
            sb.append(System.lineSeparator())
                    .append(" ".repeat(rowsTitleLength - Integer.toString(row + 1).length()))
                    .append(row + 1);
            for (int col = 0; col < cols; col++) {
                if (isInFieldChecker.test(row, col)) {
                    sb.append(" ".repeat(colsTitleWidth))
                            .append(SYMBOLS.get(cells[row][col]));
                } else {
                    sb.append(" ".repeat(colsTitleWidth))
                            .append(row % 4 == 0 && col % 4 == 0 ? '+' : (row % 4 == 0 ? '-' : (col % 4 == 0 ? '|' : ' ')));
                }
            }
        }

        return sb.toString();
    }

    @Override
    public IPosition getPosition() {
        return new ProxyPosition(this);
    }

    @Override
    public Cell getTurnCell() {
        return turn;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getWinCntCond() {
        return winCntCond;
    }

    @Override
    public Cell getCell(int row, int col) {
        if (isInFieldChecker.test(row, col)) {
            return cells[row][col];
        } else {
            return null;
        }
    }
}
