package expression;

public class Flip extends UnaryOperation {
    @Override
    protected int operate(int value) {
        int result = 0;

        while (value != 0) {
            result <<= 1;
            result += value & 1;
            value >>>= 1;
        }

        return result;
    }

    @Override
    protected double operate(double value) {
        throw new UnsupportedOperationException("Double don't support FLIP operator.");
    }

    public Flip(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected String getSymbol() {
        return Operations.FLIP.getSymbol();
    }
}
