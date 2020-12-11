package expression;

public class Add extends BinaryOperation {
    public Add(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return Operations.ADD.getSymbol();
    }

    @Override
    public int getRank() {
        return Operations.ADD.getRank();
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
        return left + right;
    }

    @Override
    protected double operate(double left, double right) {
        return left + right;
    }
}
