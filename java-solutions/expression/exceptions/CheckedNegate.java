package expression.exceptions;

import expression.CommonExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression expression) {
        super(expression);
    }

    @Override
    protected int operate(int value) {
        return MyMath.checkedNegate(value);
    }
}
