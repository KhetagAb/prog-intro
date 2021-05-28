package expression;

public abstract class IntegerBinaryOperation extends BinaryOperation {
    protected IntegerBinaryOperation(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public double operate(double left, double right) {
        throw new UnsupportedOperationException("Bitwise don't support double operation.");
    }
}
