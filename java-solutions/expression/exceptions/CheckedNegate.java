package expression.exceptions;

import expression.CommonExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected int operate(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OperationOverflowException(value + " = overflow");
        }

        return super.operate(value);
    }
}
