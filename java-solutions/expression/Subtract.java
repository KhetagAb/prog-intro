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
    protected int getRank() {
        return 0;
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }

    @Override
    protected int operate(int first, int second) {
        return first - second;
    }

    @Override
    protected double operate(double first, double second) {
        return first - second;
    }
}
