package expression.parser;

import expression.*;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class ExpressionParser extends AbstractExpressionParser implements Parser {
    private static final List<List<BinaryOperation>> BINARY_OPERATORS = List.of(
            List.of(new Or(null, null)),
            List.of(new XOR(0, 0)),
            List.of(new And(0, 0)),
            List.of(new Add(0, 0), new Subtract(0, 0)),
            List.of(new Multiply(0, 0), new Divide(0, 0))
    );

    private static final UnaryOperation[] UNARY_OPERATORS = new UnaryOperation[] {
            new Negate(0), new Flip(0), new Low(0) };


    public ExpressionParser() {
        super(BINARY_OPERATORS, UNARY_OPERATORS);
    }

    @Override
    public TripleExpression parse(String expression) {
        setSource(new StringSource(expression));
        return parseExpression();
    }

    private CommonExpression parseExpression() {
        return parseLevel(0);
    }

    private CommonExpression parseLevel(int level) {
        if (level == ranks.getMaxRank()) {
            return parseValue();
        }

        skipWhitespace();
        int nextLevel = ranks.getNextRank(level);

        CommonExpression parsed = parseLevel(nextLevel);
        String operator = parseBinaryOperator();

        while (operator != null && level == ranks.getRank(operator)) {
            expect(operator);
            parsed = buildExpression(operator, parsed, parseLevel(nextLevel));
            operator = parseBinaryOperator();
        }

        return parsed;
    }

    protected CommonExpression buildExpression(String operator, CommonExpression left, CommonExpression right) {
        BinaryOperator<CommonExpression> factory = binFactories.getOperator(operator);

        if (factory != null) {
            return factory.apply(left, right);
        }

        throw new IllegalStateException("Unknown operator");
    }

    private CommonExpression parseValue() {
        skipWhitespace();

        if (test('(')) {
            CommonExpression parsed = parseExpression();
            skipWhitespace();
            expect(')');

            return parsed;
        }

        if (test('-')) {
            if (isDigit()) {
                return parseConst("-");
            } else {
                skipWhitespace();
                return new Negate(parseValue());
            }
        }

        if (isDigit()) {
            return parseConst("");
        }

        UnaryOperator<CommonExpression> unary = parseUnaryOperator();
        return (unary == null ? parseVariable() : unary.apply(parseValue()));
    }

    private String parseBinaryOperator() {
        skipWhitespace();

        if (ch == ')' || ch == 0) {
            return null;
        } else if (isLetter()) {
            return checkToken(ch -> isLetter(ch) || isDigit(ch));
        } else {
            return Character.toString(ch);
        }
    }

    private UnaryOperator<CommonExpression> parseUnaryOperator() {
        skipWhitespace();

        StringBuilder prefix = new StringBuilder();
        boolean isWordType = true;

        char current = forwardChar(prefix.length());
        while (current != '(' && current != 0 && !isWhiteSpace(current)) {
            if (!isDigit(current) && !isLetter(current)) {
                isWordType = false;
            }

            prefix.append(current);

            String operator = prefix.toString();
            UnaryOperator<CommonExpression> unary = unFactories.getOperator(operator);
            if (unary != null) {
                if (isWordType) {
                    char next = forwardChar(prefix.length());
                    if (!isDigit(next) && !isLetter(next)) {
                        expect(operator);
                        return unary;
                    }
                } else {
                    expect(operator);
                    return unary;
                }
            }

            current = forwardChar(prefix.length());
        }

        return null;
    }

    private CommonExpression parseConst(final String prefix) {
        skipWhitespace();
        return new Const(Integer.parseInt(prefix + parseToken(BaseParser::isDigit)));
    }

    private CommonExpression parseVariable() {
        skipWhitespace();
        return new Variable(parseToken(BaseParser::isLetter));
    }
}