package expression;

public class Flip extends IntegerUnaryOperation {
    public Flip(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSymbol() {
        return "flip";
    }

    @Override
    protected int operate(int value) {
        return Integer.reverse(value) >>> Integer.numberOfLeadingZeros(value);
    }
}
