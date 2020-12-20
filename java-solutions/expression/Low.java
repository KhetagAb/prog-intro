package expression;

public class Low extends IntegerUnaryOperation {
    public Low(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSymbol() {
        return "low";
    }

    @Override
    protected int operate(int value) {
        return Integer.lowestOneBit(value);
    }
}
