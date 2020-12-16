package expression.parser;

import java.util.InputMismatchException;
import java.util.function.Predicate;

public abstract class BaseParser {
    private ExpressionSource source;
    protected char ch = '\0';

    protected void setSource(final ExpressionSource source) {
        this.source = source;
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected boolean test(final char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }

        return false;
    }

    protected boolean test(Predicate<Character> tokenChecker) {
        if (tokenChecker.test(ch)) {
            nextChar();
            return true;
        }

        return false;
    }

    @Deprecated
    protected boolean checkForward(final String expected) {
        for (int i = 0; i < expected.length(); i++) {
            if (!source.hasNext(i) || source.getNext(i) != expected.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    protected void expect(final char expected) {
        // toDo HM-12
        if (ch != expected) {
            throw new InputMismatchException("Mismatch exception. Expected: " + expected + ", found: " + ch);
        }

        nextChar();
    }

    protected void expect(final String expected) {
        // toDo HM-12
        int i = 0;
        try {
            while (i < expected.length()) {
                expect(expected.charAt(i++));
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Mismatch exception. Expected: " + expected + ", found: " + (expected.substring(0, i) + ch));
        }
    }

    protected String parseToken(Predicate<Character> tokenChecker) {
        final StringBuilder sb = new StringBuilder();

        while (tokenChecker.test(source.getNext(sb.length())) && ch != 0) {
            sb.append(source.getNext(sb.length()));
        }

        return sb.toString();
    }

    protected boolean isDigit() {
        return isDigit(ch);
    }

    protected boolean isLetter() {
        return isLetter(ch);
    }

    protected static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    protected static boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }

    protected static boolean isWhiteSpace(char ch) {
        return ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t';
    }

    protected void skipWhitespace() {
        while (test(BaseParser::isWhiteSpace)) {
            // Empty body
        }
    }
}
