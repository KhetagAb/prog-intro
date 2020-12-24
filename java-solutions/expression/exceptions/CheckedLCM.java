package expression.exceptions;

import expression.CommonExpression;
import expression.IntegerBinaryOperation;

public class CheckedLCM extends IntegerBinaryOperation {
    public CheckedLCM(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int operate(int left, int right) {
        if (!check(left, right)) {
            throw new ExpressionOverflowException(left + " lcm " + right);
        }

        if (left == 0 || right == 0) {
            return 0;
        }

        return left / MyMath.gcd(left, right) * right;
    }

    public static boolean check(int a, int b) {
        return a == 0 || b == 0 || CheckedGCD.check(a, b) && CheckedMultiply.check(a / MyMath.gcd(a, b), b);
    }

    @Override
    public String getSymbol() {
        return "lcm";
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
