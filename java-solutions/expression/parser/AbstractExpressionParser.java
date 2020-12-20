package expression.parser;

import expression.*;
import expression.exceptions.ParserException;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractExpressionParser extends BaseParser {
    // Binary/Unary operators & Variables
    protected final TrieExpression identifiers = new TrieExpression();

    protected final Map<Character, Character> brackets;

    private int maxRank = 0;
    private final Map<String, Integer> operatorToRank = new HashMap<>();
    private final Map<String, BinaryOperator<CommonExpression>> binaryFactories = new HashMap<>();
    private final Map<String, UnaryOperator<CommonExpression>> unaryFactories = new HashMap<>();

    private String lastOperator;

    protected AbstractExpressionParser(final List<Map<String, BinaryOperator<CommonExpression>>> binaryOperators,
                                       final Map<String, UnaryOperator<CommonExpression>> unaryOperators,
                                       final List<String> variables,
                                       final Map<Character, Character> brackets) {
        this.brackets = brackets;

        for (Map<String, BinaryOperator<CommonExpression>> bins: binaryOperators) {
            for (Map.Entry<String, BinaryOperator<CommonExpression>> bin: bins.entrySet()) {
                this.identifiers.put(bin.getKey(), Identifier.BINARY);
                this.binaryFactories.put(bin.getKey(), bin.getValue());
                this.operatorToRank.put(bin.getKey(), maxRank);
            }
            maxRank++;
        }

        for (Map.Entry<String, UnaryOperator<CommonExpression>> un: unaryOperators.entrySet()) {
            this.identifiers.put(un.getKey(), Identifier.UNARY);
            this.unaryFactories.put(un.getKey(), un.getValue());
        }

        for (String var: variables) {
            this.identifiers.put(var, Identifier.VAR);
        }
    }

    protected CommonExpression parseExpression() throws ParserException {
        CommonExpression parsed = parseLevel(0);

        skipWhitespace();
        expect('\0');

        return parsed;
    }

    protected CommonExpression parseLevel(int level) throws ParserException {
        if (level == getMaxRank()) {
            return parseMaxLevel();
        }

        CommonExpression parsed = parseLevel(level + 1);

        while (true) {
            String operator = parseBinaryOperator(level);
            if (operator == null)
                break;

            parsed = buildBinOperator(operator, parsed, parseLevel(level + 1));
        }

        return parsed;
    }

    protected String parseBinaryOperator(int level) throws ParserException {
        String operator = parseIdentifier(Identifier.BINARY);
        // toDo Check WTF WITH ')'?
        if (operator == null && ch != '\0' && ch != ')') {
            throw error("Mismatch exception: Expected binary operator, found: " + ch);
        } if (operator == null || operatorToRank.get(operator) != level) {
            return null;
        } else {
            expect(operator);
            return operator;
        }
    }

    protected CommonExpression parseMaxLevel() throws ParserException {
        skipWhitespace();

        for (Map.Entry<Character, Character> bracket: brackets.entrySet()) {
            if (test(bracket.getKey())) {
                return parseInBrackets(bracket.getValue());
            }
        }

        if (forwardChar(0) == '-' && isDigit(forwardChar(1))) {
            expect('-');
            return parseConst("-");
        }

        if (isDigit()) {
            return parseConst("");
        }

        return parseValue();
    }

    protected CommonExpression parseInBrackets(char closeBracket) throws ParserException {
        CommonExpression parsed = parseLevel(0);
        skipWhitespace();
        expect(closeBracket);
        return parsed;
    }

    protected CommonExpression parseConst(final String prefix) throws ParserException {
        skipWhitespace();
        String parsed = prefix + parseToken(BaseParser::isDigit);
        try {
            return new Const(Integer.parseInt(parsed));
        } catch (NumberFormatException e) {
            throw error("Invalid const value: " + e.getMessage());
        }
    }

    protected CommonExpression parseValue() throws ParserException {
        String operator = parseIdentifier(Identifier.UNARY);
        if (operator != null) {
            expect(operator);
            return buildUnOperator(operator, parseMaxLevel());
        }

        return parseVariable();
    }

    protected CommonExpression parseVariable() throws ParserException {
        String variable = parseIdentifier(Identifier.VAR);

        if (variable == null) {
            throw error("Mismatch exception: Expected variable, found " + ch);
        } else {
            expect(variable);
            return new Variable(variable);
        }
    }

    // toDo very unfriendly to exceptions
    private String parseIdentifier(Identifier type) throws ParserException {
        skipWhitespace();

        int pos = 0;
        boolean isWordType = isLetter();
        while (identifiers.test(forwardChar(pos++)) && !(identifiers.checkIdentifier(type) && isWordType && isSign(forwardChar(pos)))) {
            // emptyBody
        }

        // toDo optimize
        String operator = identifiers.getValue(type);
        if (operator != null && isWordType && !isSign(forwardChar(pos))) {
            throw error("Invalid operator found: " + operator + forwardChar(pos));
        }

        return operator;
    }

    protected CommonExpression buildBinOperator(String operator, CommonExpression left, CommonExpression right) {
        return getBinFactory(operator).apply(left, right);
    }

    protected CommonExpression buildUnOperator(String operator, CommonExpression expression) {
        return getUnFactory(operator).apply(expression);
    }

    protected BinaryOperator<CommonExpression> getBinFactory(String operator) {
        return binaryFactories.get(operator);
    }

    protected UnaryOperator<CommonExpression> getUnFactory(String operator) {
        return unaryFactories.get(operator);
    }

    protected int getRank(String operator) {
        return operatorToRank.get(operator);
    }

    protected int getMaxRank() {
        return maxRank;
    }

    protected void skipWhitespace() {
        while (test(BaseParser::isWhiteSpace)) {
            // Empty body
        }
    }

    protected class TrieExpression {
        public TrieExpression() {
            this.root = new Node();
            this.pos = this.root;
            this.sb = new StringBuilder();
        }

        private final Node root;
        private final StringBuilder sb;
        private Node pos;

        private void put(String str, Identifier type) {
            Node v = root;

            for (char ch: str.toCharArray()) {
                v.nodes.putIfAbsent(ch, new Node());
                v = v.nodes.get(ch);
            }

            switch (type) {
                case VAR:
                    v.isVariable = true;
                    break;
                case UNARY:
                    v.isUnaryOperator = true;
                    break;
                case BINARY:
                    v.isBinaryOperator = true;
                    break;
            }
        }

        private boolean test(char ch) {
            if (pos.nodes.containsKey(ch)) {
                pos = pos.nodes.get(ch);
                sb.append(ch);
                return true;
            } else {
                return false;
            }
        }

        private boolean checkIdentifier(Identifier type) {
            switch (type) {
                case VAR:
                    return pos.isVariable;
                case UNARY:
                    return pos.isUnaryOperator;
                case BINARY:
                    return pos.isBinaryOperator;
                default:
                    throw new IllegalStateException("Unreachable statement");
            }
        }

        private String getValue(Identifier type) {
            if (!checkIdentifier(type)) {
                toStart();
                return null;
            }

            String value = sb.toString();
            toStart();

            return value;
        }

        private void toStart() {
            pos = root;
            sb.setLength(0);
        }

        private class Node {
            private final Map<Character, Node> nodes = new HashMap<>();
            private boolean isVariable, isUnaryOperator, isBinaryOperator;
        }
    }
}

enum Identifier {
    VAR, UNARY, BINARY
}