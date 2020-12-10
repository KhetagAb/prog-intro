package expression;

public class Multiply extends BinaryOperation {
    public Multiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "*";
    }

    @Override
    public int getRank() {
        return 1;
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
        return left * right;
    }

    @Override
    protected double operate(double left, double right) {
        return left * right;
    }
}
