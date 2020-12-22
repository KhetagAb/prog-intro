package expression.exceptions;

public class SqrtOfNegativeException extends ExpressionException {
    public SqrtOfNegativeException(String msg) {
        super(msg);
    }

    public SqrtOfNegativeException() {
        this("Sqrt of negative number exception");
    }
}
