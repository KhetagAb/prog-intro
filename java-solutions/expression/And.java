package expression;

import java.util.function.BinaryOperator;

public class And extends BitwiseOperation {
    public And() { super(); }

    public And(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int getRank() {
        return 30000000;
    }

    @Override
    public String getSymbol() {
        return "&";
    }

    @Override
    public BinaryOperator<CommonExpression> getFactory() {
        return And::new;
    }

    @Override
    protected boolean isAssociative() {
        return true;
    }

    @Override
    protected boolean isContinuous() {
        return true;
    }

    @Override
    protected int operate(int left, int right) {
        return left & right;
    }
}
