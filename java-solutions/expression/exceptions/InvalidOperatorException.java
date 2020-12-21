package expression.exceptions;

public class InvalidOperatorException extends ParserException {
    public InvalidOperatorException(String msg) {
        super(msg);
    }

    public InvalidOperatorException() {
        this("Invalid operator");
    }
}
