package expression;

public abstract class BitwiseOperation extends BinaryOperation {
    protected BitwiseOperation() { }

    protected BitwiseOperation(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected double operate(double left, double right) {
        throw new UnsupportedOperationException("Bitwise don't support double operation.");
    }
}
