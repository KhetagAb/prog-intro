package expression.exceptions;

public class OperationOverflowException extends ExpressionException {
    // toDo very bad?
    public OperationOverflowException(String msg) {
        super("Overflow exception" + (msg == null ? "" : (": " + msg)));
    }

    public OperationOverflowException() {
        this(null);
    }
}
