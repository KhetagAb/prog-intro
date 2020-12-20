package expression;

public class Or extends BitwiseOperation {
    public Or(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int getRank() {
        return 10000000;
    }

    @Override
    public String getSymbol() {
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
}
