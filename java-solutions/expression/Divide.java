package expression;

public class Divide extends BinaryOperation {
    public Divide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int getRank() {
        return 50000000;
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    protected boolean isAssociative() {
        return false;
    }

    @Override
    protected boolean isContinuous() {
        return false;
    }

    protected int operate(int left, int right) {
        return left / right;
    }

    @Override
    protected double operate(double left, double right) {
        return left / right;
    }
}
