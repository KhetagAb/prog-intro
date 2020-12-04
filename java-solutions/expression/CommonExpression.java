package expression;

public interface CommonExpression extends Expression, TripleExpression, DoubleExpression {
    int getRank();
}
