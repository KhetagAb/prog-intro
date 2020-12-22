package expression.exceptions;

public class OperationOverflowException extends ExpressionException {
    public OperationOverflowException(String msg) {
        super("Overflow exception: " + msg);
    }

    public OperationOverflowException() {
        super("Overflow exception");
    }
}
