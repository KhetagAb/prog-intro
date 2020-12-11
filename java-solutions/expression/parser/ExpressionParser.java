package expression.parser;

import expression.*;

public class ExpressionParser extends BaseParser implements Parser {
    @Override
    public TripleExpression parse(String expression) {
        setSource(new StringSource(expression));
        return parseExpression();
    }

    private CommonExpression parseExpression() {
        return parseLevel(0);
    }

    private CommonExpression parseLevel(int level) {
        skipWhitespace();

        if (level == Operations.CONST.getRank()) {
            return parseValue();
        }

        CommonExpression parsed = parseLevel(level + 1);

        while (true) {
            skipWhitespace();

            final Operations current = Operations.BINARY_OPERAND.get(Character.toString(ch));
            // toDo HM-12
            if (current == null || level != current.getRank()) {
                return parsed;
            }

            nextChar();

            parsed = composeExpressions(parsed, parseLevel(level + 1), current);
        }
    }

    private CommonExpression composeExpressions(CommonExpression left, CommonExpression right, Operations operation) {
        switch (operation) {
            case OR:
                return new Or(left, right);
            case XOR:
                return new XOR(left, right);
            case AND:
                return new And(left, right);
            case ADD:
                return new Add(left, right);
            case SUB:
                return new Subtract(left, right);
            case MUL:
                return new Multiply(left, right);
            case DIV:
                return new Divide(left, right);
            default:
                throw new IllegalStateException("Unreachable");
                // toDo HM-12
        }
    }

    private CommonExpression parseValue() {
        skipWhitespace();

        if (test('(')) {
            CommonExpression parsed = parseExpression();
            skipWhitespace();
            expect(')');
            return parsed;
        } else if (test('-')) {
            skipWhitespace();
            if (isDigit()) {
                return parseConst(false);
            } else {
                return new Minus(parseValue());
            }
        } else if (testForward("flip")) {
            expect("flip");
            skipWhitespace();

            return new Flip(parseValue());
        } else if (testForward("low")) {
            expect("low");
            skipWhitespace();

            return new Low(parseValue());
        } else if (isDigit()) {
            return parseConst(true);
        } else {
            return parseVariable();
        }
    }

    private CommonExpression parseConst(boolean isPositive) {
        skipWhitespace();

        final StringBuilder sb = new StringBuilder();
        if (!isPositive) {
            sb.append('-');
        }

        parseInteger(sb);

        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Unreachable"); // toDo hm-12
        }
    }

    private CommonExpression parseVariable() {
        skipWhitespace();

        final StringBuilder sb = new StringBuilder();
        parseString(sb);

        try {
            return new Variable(sb.toString());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Unreached"); // toDo HM-12
        }
    }
}