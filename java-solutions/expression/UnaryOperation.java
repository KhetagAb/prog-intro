package expression;

import java.util.Objects;

public abstract class UnaryOperation extends Operation implements UnaryFactory {
    protected final CommonExpression expression;

    protected abstract int operate(int value);
    protected abstract double operate(double value);

    protected UnaryOperation() { this(null); }

    protected UnaryOperation(CommonExpression expression) {
        this.expression = expression;
    }

    @Override
    public int getRank() {
        return Integer.MAX_VALUE;
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
        int t = expression.evaluate(x, y, z);
        return operate(t);
    }

    @Override
    public String toMiniString() {
        if (expression instanceof BinaryOperation) {
            return getSymbol() + "(" + expression.toMiniString() + ")";
        } else {
            return getSymbol() + expression.toMiniString();
        }
    }

    @Override
    public String toString() {
        return "(" + getSymbol() + expression.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            UnaryOperation uo = (UnaryOperation) obj;
            return expression.equals(uo.expression);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression.hashCode(), this.getClass());
    }
}
