package expression;

import java.util.function.UnaryOperator;

public interface UnaryFactory {
    UnaryOperator<CommonExpression> getFactory();
}
