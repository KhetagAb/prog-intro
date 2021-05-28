package expression.parser;

import expression.CommonExpression;
import expression.Const;
import expression.Variable;
import expression.exceptions.ParserException;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractExpressionParser extends BaseParser {
    protected final TrieExpression tokens = new TrieExpression();
    protected final Map<Character, Character> brackets;

    private final int maxRank;
    private final Map<String, Integer> operatorToRank = new HashMap<>();
    private final Map<String, BinaryOperator<CommonExpression>> binaryFactories = new HashMap<>();
    private final Map<String, UnaryOperator<CommonExpression>> unaryFactories = new HashMap<>();

    protected AbstractExpressionParser(final List<Map<String, BinaryOperator<CommonExpression>>> binaryOperators,
                                       final Map<String, UnaryOperator<CommonExpression>> unaryOperators,
                                       final List<String> variables,
                                       final Map<Character, Character> brackets) {
        this.brackets = brackets;

        int maxRank = 0;
        for (Map<String, BinaryOperator<CommonExpression>> bins: binaryOperators) {
            for (Map.Entry<String, BinaryOperator<CommonExpression>> bin: bins.entrySet()) {
                this.tokens.putValue(bin.getKey(), Types.BINARY);
                this.binaryFactories.put(bin.getKey(), bin.getValue());
                this.operatorToRank.put(bin.getKey(), maxRank);
            }
            maxRank++;
        }
        this.maxRank = maxRank;

        for (Map.Entry<String, UnaryOperator<CommonExpression>> un: unaryOperators.entrySet()) {
            this.tokens.putValue(un.getKey(), Types.UNARY);
            this.unaryFactories.put(un.getKey(), un.getValue());
        }

        for (String var: variables) {
            this.tokens.putValue(var, Types.VARIABLE);
        }
    }

    protected CommonExpression parseExpression() throws ParserException {
        CommonExpression parsed = parseLevel(0);

        skipWhitespace();
        expect(EOF);

        return parsed;
    }

    private Token lastToken = null;
    protected CommonExpression parseLevel(int level) throws ParserException {
        lastToken = null;

        if (level == maxRank) {
            return parseMaxLevel();
        } else {
            CommonExpression parsed = parseLevel(level + 1);
            lastToken = parseBinaryOperator();

            while (lastToken != null && getRank(lastToken.operator) == level) {
                parsed = buildBinaryOperator(lastToken.operator, parsed, parseLevel(level + 1));
                lastToken = parseBinaryOperator();
            }

            return parsed;
        }
    }

    protected Token parseBinaryOperator() throws ParserException {
        if (lastToken != null) {
            return lastToken;
        }

        Token token = tokens.parseToken();
        if (token.operator.length() == 0) {
            return null;
        } else if (!token.checkOperator(Types.BINARY)) {
            throw invalidTokenException(Types.BINARY, token.operator);
        } else {
            return token;
        }
    }

    protected CommonExpression parseMaxLevel() throws ParserException {
        skipWhitespace();

        for (Map.Entry<Character, Character> bracket: brackets.entrySet()) {
            if (test(bracket.getKey())) {
                CommonExpression parsed = parseLevel(0);
                expect(bracket.getValue());
                return parsed;
            }
        }

        if (forwardChar(0) == '-' && isDigit(forwardChar(1))) {
            expect('-');
            return parseConst("-");
        } else if (isDigit()) {
            return parseConst("");
        }

        return parseValue();
    }

    protected CommonExpression parseConst(final String prefix) throws ParserException {
        skipWhitespace();
        String parsed = prefix + parseToken(BaseParser::isDigit);
        try {
            return new Const(Integer.parseInt(parsed));
        } catch (NumberFormatException e) {
            throw invalidTokenException(Types.CONST, parsed);
        }
    }

    protected CommonExpression parseValue() throws ParserException {
        Token token = tokens.parseToken();

        if (token.checkOperator(Types.UNARY)) {
            return buildUnaryOperator(token.operator, parseMaxLevel());
        } else if (token.checkOperator(Types.VARIABLE)) {
            return new Variable(token.operator);
        } else {
            throw invalidTokenException(Types.VARIABLE, token.operator);
        }
    }

    protected int getRank(String operator) {
        return operatorToRank.get(operator);
    }

    protected void skipWhitespace() {
        while (test(BaseParser::isWhiteSpace)) {
            // Empty body
        }
    }

    protected CommonExpression buildBinaryOperator(String operator, CommonExpression left, CommonExpression right) {
        assert binaryFactories.containsKey(operator);
        return binaryFactories.get(operator).apply(left, right);
    }

    protected CommonExpression buildUnaryOperator(String operator, CommonExpression expression) {
        assert unaryFactories.containsKey(operator);
        return unaryFactories.get(operator).apply(expression);
    }

    protected ParserException invalidTokenException(Types type, String found) {
        return type.instanceException().apply(getPositionMessage(
                "Invalid " + type.getExpected() +
                        " found: " + formatString(found),
                (found == null ? 0 : -found.length()) + (ch == EOF ? 0 : -1)));
    }

    protected String formatString(final String str) {
        if (str == null || str.length() == 0) {
            return formatChar(ch);
        } else {
            return "\"" + str + (ch == EOF || isWhiteSpace(ch) ? "" : ch) + "\"";
        }
    }

    private class Token {
        private final Set<Types> types;
        private final String operator;

        private Token(Set<Types> type, String operator) {
            this.types = type;
            this.operator = operator;
        }

        private boolean checkOperator(Types type) {
            return types.contains(type) && !(operator.length() == 0 || isLetter(operator.charAt(0)) && (isDigit() || isLetter()));
        }
    }

    private class TrieExpression {
        private final Node root = new Node();

        private void putValue(String str, Types type) {
            getNode(str).types.add(type);
        }

        private Node getNode(String str) {
            Node v = root;

            for (char ch: str.toCharArray()) {
                v.nodes.putIfAbsent(ch, new Node());
                v = v.nodes.get(ch);
            }

            return v;
        }

        private Token parseToken() {
            Node node = root;
            StringBuilder sb = new StringBuilder();

            skipWhitespace();
            while (node.nodes.containsKey(ch)) {
                node = node.nodes.get(ch);
                sb.append(ch);
                nextChar();
            }

            return new Token(node.types, sb.toString());
        }

        private class Node {
            private final Map<Character, Node> nodes = new HashMap<>();
            private final Set<Types> types = new HashSet<>();
        }
    }
}