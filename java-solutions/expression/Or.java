package expression;

public class BitwiseOr extends BinaryOperation {
    protected BitwiseOr(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "|";
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
        return left | right;
    }

    @Override
    protected double operate(double left, double right) {
        throw new UnsupportedOperationException("Double don't support OR operation.");
    }

    @Override
    public int getRank() {
        return -3;
    }
}
