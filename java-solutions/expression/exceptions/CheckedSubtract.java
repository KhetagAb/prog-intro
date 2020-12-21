package expression.exceptions;

import expression.CommonExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int operate(int left, int right) {
        if (MyMath.checkSubtractOverflow(left, right)) {
            throw new OperationOverflowException(left + getSymbol() + right);
        }

        return super.operate(left, right);
    }
}
