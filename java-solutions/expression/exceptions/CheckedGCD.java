package expression.exceptions;

import expression.CommonExpression;
import expression.IntegerBinaryOperation;

public class CheckedGCD extends IntegerBinaryOperation {
    public CheckedGCD(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "gcd";
    }

    @Override
    public int getRank() {
        return 30000000;
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
    protected int operate(int left, int right) {
        return MyMath.gcd(left, right);
    }
}
