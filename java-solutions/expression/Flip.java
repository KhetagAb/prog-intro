package expression;

import java.util.function.UnaryOperator;

public class Flip extends IntegerUnaryOperation {
    public Flip() { super(); };

    public Flip(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSymbol() {
        return "flip";
    }

    @Override
    public UnaryOperator<CommonExpression> getFactory() {
        return Flip::new;
    }

    @Override
    protected int operate(int value) {
        int result = 0;

        while (value != 0) {
            result <<= 1;
            result += value & 1;
            value >>>= 1;
        }

        return result;
    }
}
