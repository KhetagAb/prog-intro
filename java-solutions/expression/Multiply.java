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
    protected int getRank() {
        return 1;
    }

    @Override
    protected boolean isOrdered() {
        return false;
    }

    @Override
    protected int operate(int first, int second) {
        return first * second;
    }

    @Override
    protected double operate(double first, double second) {
        return first * second;
    }
}
