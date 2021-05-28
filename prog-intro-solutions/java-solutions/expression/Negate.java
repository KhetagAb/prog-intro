package expression;

public class Negate extends UnaryOperation {
    public Negate(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    protected int operate(int value) {
        return -value;
    }

    @Override
    protected double operate(double value) {
        return -value;
    }
}
