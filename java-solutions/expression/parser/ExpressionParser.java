package expression.parser;

import expression.*;
import expression.exceptions.ParserException;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class ExpressionParser extends AbstractExpressionParser implements Parser {
    private static final List<Map<String, BinaryOperator<CommonExpression>>> BINARY_OPERATORS = List.of(
            Map.of("|", Or::new),
            Map.of("^", XOR::new),
            Map.of("&", And::new),
            Map.of("+", Add::new, "-", Subtract::new),
            Map.of("*", Multiply::new, "/", Divide::new)
    );

    private static final Map<String, UnaryOperator<CommonExpression>> UNARY_OPERATORS = Map.of(
            "-", Negate::new, "flip", Flip::new, "low", Low::new
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
    public TripleExpression parse(String expression) {
        setSource(new StringSource(expression));

        TripleExpression parsed;
        try {
            parsed = parseExpression();
        } catch (ParserException e) {
            throw new IllegalStateException("Unreachable statement!");
        }

        return parsed;
    }
}