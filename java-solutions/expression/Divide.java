package expression;

public class Divide extends BinaryOperation {
    public Divide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return Operations.DIV.getSymbol();
    }

    @Override
    public int getRank() {
        return Operations.DIV.getRank();
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
