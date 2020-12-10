package expression;

public class XOR extends BinaryOperation {
    protected XOR(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "^";
    }

    @Override
    public int getRank() {
        return -2;
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
        return left ^ right;
    }

    @Override
    protected double operate(double left, double right) {
        throw new UnsupportedOperationException("Double don't support XOR operation.");
    }
}
