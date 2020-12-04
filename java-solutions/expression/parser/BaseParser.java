package expression.parser;

public abstract class BaseParser {
    public static final char END = '\0';
    private final CharSource source;
    protected char ch = 0xffff;

    protected BaseParser(CharSource source) {
        this.source = source;
    }
}
