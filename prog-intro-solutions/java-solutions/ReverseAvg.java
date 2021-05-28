import java.util.*;

public class ReverseAvg {
    private static int[] tryExtend(int[] array, int n) {
        if (array.length <= n) {
            int new_len = array.length;
            while (new_len <= n) {
                new_len *= 2;
            }

            return Arrays.copyOf(array, new_len);
        } else {
            return array;
        }
    }
    private static long[] tryExtend(long[] array, int n) {
        if (array.length <= n) {
            int new_len = array.length;
            while (new_len <= n) {
                new_len *= 2;
            }

            return Arrays.copyOf(array, new_len);
        } else {
            return array;
        }
    }
    private static int[][] tryExtend(int[][] array, int n) {
        if (array.length <= n) {
            int new_len = array.length;
            while (new_len <= n) {
                new_len *= 2;
            }

            return Arrays.copyOf(array, new_len);
        } else {
            return array;
        }
    }

    public static void main(String[] args) {
        Scanner onFile = new Scanner(System.in);

        int[][] array = new int[1][];

        long[] sumInRows = new long[1];
        long[] sumInCols = new long[1];
        int[] cntInRows = new int[1];
        int[] cntInCols = new int[1];

        int row_cnt = 0;
        while (onFile.hasNextLine()) {
            array = tryExtend(array, row_cnt);
            sumInRows = tryExtend(sumInRows, row_cnt);
            cntInRows = tryExtend(cntInRows, row_cnt);

            array[row_cnt] = new int[1];

            Scanner onLine = new Scanner(onFile.nextLine());
            for (int col_cnt = 0; onLine.hasNextInt(); col_cnt++)  {
                array[row_cnt] = tryExtend(array[row_cnt], col_cnt);
                array[row_cnt][col_cnt] = onLine.nextInt();

                sumInCols = tryExtend(sumInCols, col_cnt);
                cntInCols = tryExtend(cntInCols, col_cnt);

                sumInRows[row_cnt] += array[row_cnt][col_cnt];
                cntInRows[row_cnt]++;

                sumInCols[col_cnt] += array[row_cnt][col_cnt];
                cntInCols[col_cnt]++;
            }

            row_cnt++;
        }

        for (int i = 0; i < row_cnt; i++) {
            for (int j = 0; j < cntInRows[i]; j++) {
                long avr = (sumInRows[i] + sumInCols[j] - array[i][j]) / (cntInRows[i] + cntInCols[j] - 1);

                System.out.print(avr);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
