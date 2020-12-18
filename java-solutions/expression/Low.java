package expression;

import java.util.function.UnaryOperator;

public class Low extends IntegerUnaryOperation {
    public Low(int value) {
        super(value);
    }

    public Low(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSymbol() {
        return "low";
    }

    @Override
    public UnaryOperator<CommonExpression> getFactory() {
        return Low::new;
    }
    @Override
    protected int operate(int value) {
        return Integer.lowestOneBit(value);
    }
}
