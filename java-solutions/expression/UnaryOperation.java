package expression;

public abstract class UnaryOperator implements CommonExpression {
    protected final CommonExpression expression;

    protected abstract int operate(int value);
    protected abstract double operate(double value);

    protected UnaryOperator(CommonExpression expression) {
        this.expression = expression;
    }

    @Override
    public double evaluate(double x) {
        return operate(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return operate(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(expression.evaluate(x, y, z));
    }
}
