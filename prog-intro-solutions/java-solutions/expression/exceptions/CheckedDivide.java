package expression.exceptions;

import expression.CommonExpression;
import expression.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        if (right == 0) {
            throw new DivideByZeroException(left + " / 0");
        } else if (left == Integer.MIN_VALUE && right == -1) {
            throw new ExpressionOverflowException(left + " " + getSymbol() + " " + right);
        }

        return left / right;
    }

    public static boolean check(int left, int right) {
        return !(right == 0 || left == Integer.MIN_VALUE && right == -1);
    }
}
