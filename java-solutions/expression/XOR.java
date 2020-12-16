package expression;

import java.util.function.BinaryOperator;

public class XOR extends BitwiseOperation {
    public XOR() { super(); }

    public XOR(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int getRank() {
        return 20000000;
    }

    @Override
    public String getSymbol() {
        return "^";
    }

    @Override
    public BinaryOperator<CommonExpression> getFactory() {
        return XOR::new;
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
        return left ^ right;
    }
}
