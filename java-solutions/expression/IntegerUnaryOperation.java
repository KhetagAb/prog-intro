package expression;

public abstract class IntegerUnaryOperation extends UnaryOperation {
    public IntegerUnaryOperation(int value) { super(value); }

    protected IntegerUnaryOperation(CommonExpression expression) {
        super(expression);
    }

    @Override
    public double operate(double value) {
        throw new UnsupportedOperationException("Double don't support LOW operator.");
    }
}
