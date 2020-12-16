package expression;

import java.util.function.BinaryOperator;

public class Add extends BinaryOperation {
    public Add() { super(); }

    public Add(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int getRank() {
        return 40000000;
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public BinaryOperator<CommonExpression> getFactory() {
        return Add::new;
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
        return left + right;
    }

    @Override
    protected double operate(double left, double right) {
        return left + right;
    }
}
