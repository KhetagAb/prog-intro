package expression;

public class Low extends UnaryOperation {
    @Override
    protected int operate(int value) {
        return Integer.lowestOneBit(value);
    }

    @Override
    protected double operate(double value) {
        throw new UnsupportedOperationException("Double don't support LOW operator.");
    }

    public Low(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected String getSymbol() {
        return Operations.LOW.getSymbol();
    }
}
