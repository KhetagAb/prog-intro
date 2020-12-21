package expression.exceptions;

import expression.Add;
import expression.CommonExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int operate(int left, int right) {
        if (MyMath.checkAddOverflow(left, right)) {
            throw new OperationOverflowException(left + " add " + right + " = overflow");
        }

        return super.operate(left, right);
    }
}
