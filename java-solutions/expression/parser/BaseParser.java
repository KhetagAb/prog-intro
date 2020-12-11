package expression.parser;

import java.util.InputMismatchException;

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

    protected boolean test(final char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }

        return false;
    }

    protected boolean testForward(final String expected) {
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

    protected void parseInteger(final StringBuilder sb) {
        while (isDigit()) {
            sb.append(ch);
            nextChar();
        }
    }

    protected void parseString(final StringBuilder sb) {
        while (isLetter()) {
            sb.append(ch);
            nextChar();
        }
    }

    protected boolean isDigit() {
        return '0' <= ch && ch <= '9';
    }

    protected boolean isLetter() {
        return Character.isLetter(ch);
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // Empty body...
        }
    }
}
