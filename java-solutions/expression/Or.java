package expression;

import java.util.function.BinaryOperator;

public class Or extends BitwiseOperation {
    public Or() { super(); }

    public Or(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int getRank() {
        return 10000000;
    }

    @Override
    public String getSymbol() {
        return "|";
    }

    @Override
    public BinaryOperator<CommonExpression> getFactory() {
        return Or::new;
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
        return left | right;
    }
}
