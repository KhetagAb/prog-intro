package expression.exceptions;

import expression.CommonExpression;
import expression.IntegerBinaryOperation;

public class CheckedGCD extends IntegerBinaryOperation {
    public CheckedGCD(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        if (!check(left, right)) {
            throw new ExpressionOverflowException(left + " " + getSymbol() + " " + right);
        }

        while (right != 0) {
            left %= right;

            left ^= right;
            right ^= left;
            left ^= right;
        }

        return MyMath.abs(left);
    }

    public static boolean check(int left, int right) {
        return left != Integer.MIN_VALUE || right != Integer.MIN_VALUE;
    }

    @Override
    public String getSymbol() {
        return "gcd";
    }

    @Override
    protected boolean isAssociative() {
        return true;
    }

    @Override
    protected boolean isContinuous() {
        return false;
    }

    @Override
    public int getRank() {
        return 30000000;
    }
}
