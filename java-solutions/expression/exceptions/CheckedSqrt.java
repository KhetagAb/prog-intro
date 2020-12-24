package expression.exceptions;

import expression.CommonExpression;
import expression.IntegerUnaryOperation;

public class CheckedSqrt extends IntegerUnaryOperation {
    public CheckedSqrt(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSymbol() {
        return "sqrt";
    }

    @Override
    protected int operate(int value) {
        if (!check(value)) {
            throw new SqrtOfNegativeException(Integer.toString(value));
        }

        return (int) Math.sqrt(value);
    }

    public static boolean check(int value) {
        return value >= 0;
    }
}
