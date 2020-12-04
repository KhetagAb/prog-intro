package expression;

import java.util.Objects;

public abstract class BinaryOperation implements CommonExpression {
    protected final CommonExpression left, right;

    protected abstract String getSymbol();
    protected abstract boolean isOrdered();
    protected abstract int operate(int left, int right);
    protected abstract double operate(double left, double right);

    protected BinaryOperation(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(int x) {
        return operate(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public double evaluate(double x) {
        return operate(left.evaluate(x), right.evaluate(x));
    }

    private String getMinExpression(final CommonExpression ex, boolean isRight) {
        boolean isBinBrackets = ex instanceof BinaryOperation && isRight && (((BinaryOperation) ex).isOrdered() || isOrdered());
        if (ex.getRank() < getRank() || ex.getRank() == getRank() && isBinBrackets) {
            return "(" + ex.toMiniString() + ")";
        }

        return ex.toMiniString();
    }

    @Override
    public String toMiniString() {
        return getMinExpression(left,false) +
                " " + getSymbol() + " " +
                getMinExpression(right, true);
    }

    @Override
    public String toString() {
        return "(" + left.toString() +
                " " + getSymbol() + " " +
                right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            BinaryOperation bo = (BinaryOperation) obj;
            return left.equals(bo.left) && right.equals(bo.right);
        }
    } 

    @Override
    public int hashCode() {
        return Objects.hash(left.hashCode(), right.hashCode(), this.getClass());
    }
}
