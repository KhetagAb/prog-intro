package expression.exceptions;

public class SqrtOfNegativeException extends ExpressionException {
    public SqrtOfNegativeException(String expression) {
        super("Sqrt of number: " + expression);
    }

    public SqrtOfNegativeException() {
        this("Sqrt of negative number exception");
    }
}
