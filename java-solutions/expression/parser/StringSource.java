package expression.parser;

public class StringSource implements CharSource {
    private final String source;
    private int pos;

    public StringSource(String source) {
        this.source = source;
        this.pos = 0;
    }

    @Override
    public boolean hasNext() {
        return pos < source.length();
    }

    @Override
    public char next() {
        return source.charAt(pos++);
    }
}
