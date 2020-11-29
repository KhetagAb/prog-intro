import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReverseHexAbc {
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

    private static int getNum(String str) {
        int num = 0;
        if (str.startsWith("0x")) {
            num = (int) Integer.parseUnsignedInt(str.substring(2), 16);
        } else {
            char[] toNum = str.toCharArray();
            for (int i = 0; i < toNum.length; i++) {
                if (!Character.isDigit(toNum[i]) && toNum[i] != '-') {
                    toNum[i] = (char) (toNum[i] - 'a' + '0');
                }
            }
            num = Integer.parseInt(new String(toNum));
        }

        return num;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in, StandardCharsets.UTF_8, a -> !Character.isWhitespace(a));

        int[][] array = new int[1][];
        int[] cntInRows = new int[1];

        int rowCnt = 0;
        while (sc.hasNext()) {
            array = tryExtend(array, rowCnt);
            cntInRows = tryExtend(cntInRows, rowCnt);

            array[rowCnt] = new int[1];

            for (int colCnt = 0; sc.hasNext() && rowCnt == sc.getLineNumOfNext(); colCnt++) {
                array[rowCnt] = tryExtend(array[rowCnt], colCnt);
                array[rowCnt][colCnt] = getNum(sc.next().toLowerCase());

                cntInRows[rowCnt]++;
            }

            rowCnt++;
        }

        rowCnt = Math.max(rowCnt, sc.getLineNumOfNext());
        cntInRows = tryExtend(cntInRows, rowCnt);

        for (int i = rowCnt - 1; i >= 0; i--) {
            for (int j = cntInRows[i] - 1; j >= 0; j--) {
                System.out.print(array[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
