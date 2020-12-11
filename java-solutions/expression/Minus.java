package expression;

public class Minus extends UnaryOperation {
    @Override
    protected int operate(int value) {
        return -value;
    }

    @Override
    protected double operate(double value) {
        return -value;
    }

    public Minus(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected String getSymbol() {
        return Operations.MINUS.getSymbol();
    }
}
