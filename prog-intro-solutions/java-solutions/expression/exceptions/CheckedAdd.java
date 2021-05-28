package expression.exceptions;

import expression.Add;
import expression.CommonExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        if (!check(left, right)) {
            throw new ExpressionOverflowException(left + " " + getSymbol() + " " + right);
        }

        return left + right;
    }

    public static boolean check(int left, int right) {
        return !(left > 0 && Integer.MAX_VALUE - left < right ||
                left < 0 && Integer.MIN_VALUE - left > right);
    }
}
