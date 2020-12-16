package expression;

public abstract class IntegerUnaryOperation extends UnaryOperation {
    public IntegerUnaryOperation() { }

    protected IntegerUnaryOperation(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected double operate(double value) {
        throw new UnsupportedOperationException("Double don't support LOW operator.");
    }
}
