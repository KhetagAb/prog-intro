package expression.exceptions;

public class ParserException extends Exception {
    public ParserException(String message) {
        super(message);
    }

    public ParserException() {
        super("Parser exception");
    }
}
