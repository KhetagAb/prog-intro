package expression;

public abstract class IntegerUnaryOperation extends UnaryOperation {
    protected IntegerUnaryOperation(CommonExpression expression) {
        super(expression);
    }

    @Override
    public double operate(double value) {
        throw new UnsupportedOperationException("Double don't support LOW operator.");
    }
}
