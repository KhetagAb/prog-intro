package expression;

public class And extends BinaryOperation {
    public And(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return Operations.AND.getSymbol();
    }

    @Override
    public int getRank() {
        return Operations.AND.getRank();
    }

    @Override
    protected boolean isAssociative() {
        return true;
    }

    @Override
    protected boolean isContinuous() {
        return true;
    }

    @Override
    protected int operate(int left, int right) {
        return left & right;
    }

    @Override
    protected double operate(double left, double right) {
        throw new UnsupportedOperationException("Double don't support AND operation.");
    }
}
