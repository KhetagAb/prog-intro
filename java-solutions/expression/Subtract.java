package expression;

public class Subtract extends BinaryOperation {
    public Subtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "-";
    }

    @Override
    public int getRank() {
        return 0;
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }

    @Override
    protected int operate(int left, int right) {
        return left - right;
    }

    @Override
    protected double operate(double left, double right) {
        return left - right;
    }
}
