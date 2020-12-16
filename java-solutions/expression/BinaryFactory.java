package expression;

import java.util.function.BinaryOperator;

public interface BinaryFactory {
    BinaryOperator<CommonExpression> getFactory();
}
