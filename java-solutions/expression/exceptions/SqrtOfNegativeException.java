package expression.exceptions;

public class SqrtOfNegativeException extends ExpressionException {
    public SqrtOfNegativeException(String msg) {
        super(msg);
    }

    public SqrtOfNegativeException() {
        this("Invalid number exception: Sqrt of negative value");
    }
}
