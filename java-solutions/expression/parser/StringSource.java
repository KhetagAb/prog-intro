package expression.parser;

public class StringSource implements ExpressionSource {
    private final String source;
    private int pos;

    public StringSource(final String source) {
        this.source = source;
    }

    @Override
    public boolean hasNext(int forward) {
        return forward + pos - 1 < source.length();
    }

    @Override
    public boolean hasNext() {
        return hasNext(1);
    }

    public char getNext(int forward) {
        return source.charAt(pos + forward - 1);
    }

    @Override
    public String posExceptionMessage(String message) {
        return "Exception from pos " + (pos - 1) + ": " + message;
    }

    public char next() {
        return source.charAt(pos++);
    }
}
