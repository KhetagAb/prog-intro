package expression;

import java.util.function.UnaryOperator;

public class Negate extends UnaryOperation {
    public Negate(int value) {
        super(value);
    }

    public Negate(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public UnaryOperator<CommonExpression> getFactory() {
        return Negate::new;
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
