import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordStatCountShingles {
    final static int BLOCK = 3;

    private static boolean characterChecker(char ch) {
        return Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'';
    }

    public static void main(String[] args) {
        Map<String, Integer> wordsStat = new LinkedHashMap<>();

        try {
            try (FastScanner sc = new FastScanner(args[0], StandardCharsets.UTF_8, WordStatCountShingles::characterChecker)) {
                while (sc.hasNext()) {
                    String str = sc.next().toLowerCase();

                    for (int i = BLOCK - 1; i < str.length(); i++) {
                        String substr = str.substring(i - BLOCK + 1, i + 1);

                        wordsStat.merge(substr, 1, Integer::sum);
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                List<Map.Entry<String, Integer>> array = new ArrayList<>(wordsStat.entrySet());

                array.sort(Map.Entry.comparingByValue());

                for (Map.Entry<String, Integer> it : array) {
                    writer.write(String.format("%s %d%s", it.getKey(), it.getValue(), System.lineSeparator()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Exception IOException: " + e.getMessage());
        }
    }
}
