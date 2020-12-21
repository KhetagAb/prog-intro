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
        if (value < 0) {
            throw new SqrtOfNegativeException("Invalid number exception: Sqrt of negative value: " + value);
        }

        return (int) Math.sqrt(value);
    }
}
