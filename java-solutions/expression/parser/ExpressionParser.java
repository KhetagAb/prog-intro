package expression.parser;

import expression.*;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class ExpressionParser extends AbstractExpressionParser implements Parser {
    private static final BinaryOperation[] BINARY_OPERATORS = new BinaryOperation[] {
            new Or(), new And(), new XOR(), new Add(), new Subtract(), new Multiply(), new Divide() };

    private static final UnaryOperation[] UNARY_OPERATORS = new UnaryOperation[] {
            new Negate(), new Flip(), new Low() };


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

        int nextLevel = ranks.getNextRank(level);

        skipWhitespace();
        CommonExpression parsed = parseLevel(nextLevel);

        // (z   )/10
        while (true) {
            skipWhitespace();

            if (ch == ')')
                return parsed;

            String operator = parseBinaryOperator();
            if (operator == null || level != ranks.getRank(operator)) {
                return parsed;
            }

            expect(operator);

            parsed = binFactories.buildExpression(operator, parsed, parseLevel(nextLevel));
        }
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

        if (isLetter()) {
            return parseToken(ch -> isLetter(ch) || isDigit(ch));
        } else {
            return ch == 0 ? null : Character.toString(ch);
        }
    }

    private UnaryOperator<CommonExpression> parseUnaryOperator() {
        skipWhitespace();

        while (true) {
            if (unFactories.check(ch) && !unFactories.isOperator()) {
                nextChar();
            } else {
                break;
            }
        }

        if (unFactories.isOperator()) {
            nextChar();
            return unFactories.getOperator();
        } else {
            return null;
        }
    }

    private CommonExpression parseConst(final String prefix) {
        skipWhitespace();

        String token = parseToken(BaseParser::isDigit);
        expect(token);

        return new Const(Integer.parseInt(prefix + token));
    }

    private CommonExpression parseVariable() {
        skipWhitespace();

        String token = parseToken(BaseParser::isLetter);
        expect(token);

        return new Variable(token);
    }
}