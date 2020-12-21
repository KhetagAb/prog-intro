package expression.exceptions;

import expression.CommonExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int operate(int left, int right) {
        if (MyMath.checkMultiplyOverflow(left, right)) {
            throw new OperationOverflowException();
        }

        return super.operate(left, right);
    }
}
