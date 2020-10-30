import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordStatCountFirstIndex {
    private static boolean characterChecker(char ch) {
        return Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'';
    }

    public static void main(String[] args) {
        Map<String, WordsEnters> wordsStat = new LinkedHashMap<String, WordsEnters>();

        try {
            FastScanner sc = new FastScanner(args[0], StandardCharsets.UTF_8, WordStatCountFirstIndex::characterChecker);

            try {
                for (int currentRow = 0; sc.hasNext(); currentRow++) {
                    for (int numInLine = 1; sc.hasNext() && sc.getLineNumOfNext() == currentRow; numInLine++) {
                        String str = sc.next().toLowerCase();

                        wordsStat.computeIfAbsent(str, k -> new WordsEnters());
                        wordsStat.get(str).tryPushEntry(currentRow, numInLine);
                    }
                }
            } finally {
                sc.close();
            }
        } catch (IOException e) {
            System.out.println("Exception in reading. IOException: " + e.getMessage());
        }

        List<Map.Entry<String, WordsEnters>> array = new ArrayList<>(wordsStat.entrySet());
        array.sort((a, b) -> Integer.compare(a.getValue().countOfWords, b.getValue().countOfWords));

        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                new FileOutputStream(args[1]), StandardCharsets.UTF_8));

            try {
                for (Map.Entry<String, WordsEnters> ent: array) {
                    WordsEnters temp = ent.getValue();

                    writer.write(String.format("%s %d %s%n", ent.getKey(), temp.countOfWords, temp.getEntries().toString()));
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Exception in writing. IOException: " + e.getMessage());
        }
    }
}
