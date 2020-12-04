package expression;

public class Add extends BinaryOperation {
    public Add(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getSymbol() {
        return "+";
    }

    @Override
    public int getRank() {
        return 0;
    }

    @Override
    public boolean isOrdered() {
        return false;
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
