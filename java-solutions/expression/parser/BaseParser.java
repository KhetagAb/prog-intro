package expression.parser;

import java.util.InputMismatchException;
import java.util.function.Predicate;

public abstract class BaseParser {
    private ExpressionSource source;
    protected char ch;

    protected void setSource(final ExpressionSource source) {
        this.source = source;
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected char forwardChar(int forward) {
        return source.hasNext(forward) ? source.getNext(forward) : '\0';
    }

    protected boolean test(final char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }

        return false;
    }

    protected boolean test(final Predicate<Character> tokenChecker) {
        if (tokenChecker.test(ch)) {
            nextChar();
            return true;
        }

        return false;
    }

    protected String parseToken(final Predicate<Character> tokenChecker) {
        final StringBuilder sb = new StringBuilder();

        while (ch != 0 && tokenChecker.test(ch)) {
            sb.append(ch);
            nextChar();
        }

        return sb.length() == 0 ? null : sb.toString();
    }

    protected String checkToken(final Predicate<Character> tokenChecker) {
        final StringBuilder sb = new StringBuilder();

        char cur = forwardChar(sb.length());
        while (ch != 0 && tokenChecker.test(cur)) {
            sb.append(cur);
            cur = forwardChar(sb.length());
        }

        return sb.length() == 0 ? null : sb.toString();
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

    protected boolean isDigit() {
        return isDigit(ch);
    }

    protected boolean isLetter() {
        return isLetter(ch);
    }

    protected void skipWhitespace() {
        while (test(BaseParser::isWhiteSpace)) {
            // Empty body
        }
    }

    protected static boolean isDigit(final char ch) {
        return '0' <= ch && ch <= '9';
    }

    protected static boolean isLetter(final char ch) {
        return Character.isLetter(ch);
    }

    protected static boolean isWhiteSpace(final char ch) {
        return ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t';
    }
}
