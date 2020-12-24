package expression.exceptions;

import expression.CommonExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected int operate(int value) {
        if (!check(value)) {
            throw new ExpressionOverflowException(getSymbol() + value);
        }

        return -value;
    }

    public static boolean check(int value) {
        return value != Integer.MIN_VALUE;
    }
}
