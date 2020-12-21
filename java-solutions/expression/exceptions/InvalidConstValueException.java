package expression.exceptions;

public class InvalidConstValueException extends ParserException {
    public InvalidConstValueException(String msg) {
        super(msg);
    }

    public InvalidConstValueException() {
        this("Invalid const value");
    }
}
