package expression.exceptions;

public class InvalidVariableException extends ParserException {
    public InvalidVariableException(String msg) {
        super(msg);
    }

    public InvalidVariableException() {
        this("Invalid variable");
    }
}
