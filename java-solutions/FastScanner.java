import java.util.*;
import java.io.*;
import java.nio.charset.Charset;

public class FastScanner implements AutoCloseable {
    @FunctionalInterface
    public interface TokenChecker {
        boolean test(char ch);
    }

    private final TokenChecker chChecker;
    private final BufferedReader reader;

    private boolean hasToken = false;
    private String token;

    private int lineNumOfToken = 0;
    private int lastChar = -1;

    public FastScanner(String fileName, Charset cs, TokenChecker checker) throws FileNotFoundException {
        reader = new BufferedReader(
                new InputStreamReader(
                new FileInputStream(fileName), cs)
            );
        chChecker = checker;
    }

    public FastScanner(InputStream in, Charset cs, TokenChecker checker) throws IOException {
        reader = new BufferedReader(
                new InputStreamReader(in, cs)
            );
        chChecker = checker;
    }

    public int getLineNumOfNext() {
        return lineNumOfToken;
    }

    private void switchToNextChar() throws IOException {
        int ch;
        try {
            ch = reader.read();
        } catch (IOException e) { // По канонам Java Scanner считаем обрыв потока концом ввода, но можно выкидывать ошибку вверх
            ch = -1;
        }

        if (ch == '\r' || (ch == '\n' && lastChar != '\r')) {
            lineNumOfToken++;
        }

        lastChar = ch;
        if (lastChar == -1) {
            close();
        }
    }

    private boolean hasNextToken() throws IOException {
        if (hasToken || (lastChar != -1 && chChecker.test((char) lastChar))) {
            return true;
        }

        do {
            switchToNextChar();
        } while (lastChar != - 1 && !chChecker.test((char) lastChar));

        return lastChar != -1;
    }

    private void readToken() throws IOException {
        if (hasNextToken()) {
            if (!hasToken) {
                StringBuilder sb = new StringBuilder();

                while (lastChar != -1 && chChecker.test((char) lastChar)) {
                    sb.append((char) lastChar);
                    switchToNextChar();
                }

                token = sb.toString();
                hasToken = true;
            }
        } else {
            throw new InputMismatchException("Trying to access empty next token!");
        }
    }

    public boolean hasNext() throws IOException {
        return hasNextToken();
    }

    public String next() throws IOException {
        readToken();

        hasToken = false;
        return token;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
