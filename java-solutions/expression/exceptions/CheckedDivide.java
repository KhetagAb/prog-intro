package expression.exceptions;

import expression.CommonExpression;
import expression.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int operate(int left, int right) {
        if (right == 0) {
            throw new DivideByZeroException();
        } else if (MyMath.checkDivideOverflow(left, right)) {
            throw new OperationOverflowException(left + " divide " + right + " = overflow");
        }

        return super.operate(left, right);
    }
}
