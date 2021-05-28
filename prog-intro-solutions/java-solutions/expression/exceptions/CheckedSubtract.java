package expression.exceptions;

import expression.CommonExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int operate(int left, int right) {
        if (!check(left, right)) {
            throw new ExpressionOverflowException(left + " " + getSymbol() + " " + right);
        }

        return left - right;
    }

    public static boolean check(int left, int right) {
        return !(right < 0 && Integer.MAX_VALUE + right < left ||
                right > 0 && Integer.MIN_VALUE + right > left);
    }
}
