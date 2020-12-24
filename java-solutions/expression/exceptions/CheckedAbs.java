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
        if (!check(value)) {
            throw new ExpressionOverflowException(getSymbol() + " " + value);
        }

        return MyMath.abs(value);
    }

    public static boolean check(int value) {
        return value != Integer.MIN_VALUE;
    }
}
