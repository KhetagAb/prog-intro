package expression.exceptions;

import expression.CommonExpression;
import expression.IntegerUnaryOperation;

public class CheckedAbs extends IntegerUnaryOperation {
    public CheckedAbs(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSymbol() {
        return "abs";
    }

    @Override
    protected int operate(int value) {
        return MyMath.abs(value);
    }
}
