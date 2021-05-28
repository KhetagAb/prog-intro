package expression.exceptions;

import expression.*;
import expression.parser.AbstractExpressionParser;
import expression.parser.StringSource;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class ExpressionParser extends AbstractExpressionParser implements Parser {
    private static final List<Map<String, BinaryOperator<CommonExpression>>> BINARY_OPERATORS = List.of(
            Map.of("gcd", CheckedGCD::new, "lcm", CheckedLCM::new),
            Map.of("+", CheckedAdd::new, "-", CheckedSubtract::new),
            Map.of("*", CheckedMultiply::new, "/", CheckedDivide::new)
    );

    private static final Map<String, UnaryOperator<CommonExpression>> UNARY_OPERATORS = Map.of(
            "-", CheckedNegate::new, "abs", CheckedAbs::new, "sqrt", CheckedSqrt::new
    );

    private static final List<String> VARIABLES = List.of(
            "x", "y", "z"
    );

    private static final Map<Character, Character> BRACKETS = Map.of(
            '(', ')'
    );

    public ExpressionParser() {
        super(BINARY_OPERATORS, UNARY_OPERATORS, VARIABLES, BRACKETS);
    }

    @Override
    public TripleExpression parse(String expression) throws ParserException {
        setSource(new StringSource(expression));
        return parseExpression();
    }
}
