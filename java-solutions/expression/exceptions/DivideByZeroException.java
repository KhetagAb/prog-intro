package expression.exceptions;

public class DivideByZeroException extends ExpressionException {
    public DivideByZeroException(String msg) {
        super(msg);
    }

    public DivideByZeroException() {
        this("Division by zero");
    }
}
