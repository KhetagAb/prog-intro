package expression;

public class And extends BitwiseOperation {
    public And(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int getRank() {
        return 30000000;
    }

    @Override
    public String getSymbol() {
        return "&";
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
}
