package expression;

public class Divide extends BinaryOperation {
    public Divide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "/";
    }

    @Override
    public int getRank() {
        return 1;
    }

    @Override
    protected boolean isOrdered() {
        return true;
    }

    @Override
    protected int operate(int left, int right) {
        return left / right;
    }

    @Override
    protected double operate(double left, double right) {
        return left / right;
    }
}
