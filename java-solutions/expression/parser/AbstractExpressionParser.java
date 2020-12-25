package expression.parser;

import expression.CommonExpression;
import expression.Const;
import expression.Variable;
import expression.exceptions.ParserException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractExpressionParser extends BaseParser {
    // Binary/Unary operators & Variables
    protected final TrieExpression tokens = new TrieExpression();

    protected final Map<Character, Character> brackets;

    private int maxRank = 0;
    private final Map<String, Integer> operatorToRank = new HashMap<>();
    private final Map<String, BinaryOperator<CommonExpression>> binaryFactories = new HashMap<>();
    private final Map<String, UnaryOperator<CommonExpression>> unaryFactories = new HashMap<>();

    private String lastOperator = null;

    protected AbstractExpressionParser(final List<Map<String, BinaryOperator<CommonExpression>>> binaryOperators,
                                       final Map<String, UnaryOperator<CommonExpression>> unaryOperators,
                                       final List<String> variables,
                                       final Map<Character, Character> brackets) {
        this.brackets = brackets;

        for (Map<String, BinaryOperator<CommonExpression>> bins: binaryOperators) {
            for (Map.Entry<String, BinaryOperator<CommonExpression>> bin: bins.entrySet()) {
                this.tokens.putBinaryOperator(bin.getKey());
                this.binaryFactories.put(bin.getKey(), bin.getValue());
                this.operatorToRank.put(bin.getKey(), maxRank);
            }
            maxRank++;
        }

        for (Map.Entry<String, UnaryOperator<CommonExpression>> un: unaryOperators.entrySet()) {
            this.tokens.putValue(un.getKey(), Token.UNARY);
            this.unaryFactories.put(un.getKey(), un.getValue());
        }

        for (String var: variables) {
            this.tokens.putValue(var, Token.VARIABLE);
        }
    }

    protected CommonExpression parseExpression() throws ParserException {
        CommonExpression parsed = parseLevel(0);

        skipWhitespace();

        if (ch != EOF) {
            throw invalidTokenException(Token.BINARY, tokens.toRoot());
        }

        return parsed;
    }

    protected CommonExpression parseLevel(int level) throws ParserException {
        if (level == getMaxRank()) {
            return parseMaxLevel();
        }

        CommonExpression parsed = parseLevel(level + 1);
        parseBinaryOperator();

        while (lastOperator != null && getRank(lastOperator) == level) {
            String operator = lastOperator;
            lastOperator = null;
            parsed = buildBinOperator(operator, parsed, parseLevel(level + 1));
            parseBinaryOperator();
        }

        return parsed;
    }

    protected void parseBinaryOperator() throws ParserException {
        if (lastOperator != null) {
            return;
        }

        skipWhitespace();
        tokens.parseToken();
        lastOperator = tokens.getBinaryOperator();
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
            throw invalidTokenException(Token.CONST, parsed);
        }
    }

    protected CommonExpression parseValue() throws ParserException {
        skipWhitespace();
        tokens.parseToken();
        String value = tokens.getValue(Token.UNARY);

        if (value == null) {
            value = tokens.getValue(Token.VARIABLE);

            if (value == null) {
                throw invalidTokenException(Token.VARIABLE, tokens.toRoot());
            }

            return new Variable(value);
        } else {
            return buildUnOperator(value, parseMaxLevel());
        }
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

    protected CommonExpression buildBinOperator(String operator, CommonExpression left, CommonExpression right) {
        if (!binaryFactories.containsKey(operator)) {
            throw new IllegalStateException("Unsupported binary operator");
        }

        return binaryFactories.get(operator).apply(left, right);
    }

    protected CommonExpression buildUnOperator(String operator, CommonExpression expression) {
        if (!unaryFactories.containsKey(operator)) {
            throw new IllegalStateException("Unsupported unary operator");
        }

        return unaryFactories.get(operator).apply(expression);
    }

    protected ParserException invalidTokenException(Token type, String found) {
        return type.getFactory().apply(getPositionMessage(
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

    protected class TrieExpression {
        protected TrieExpression() {
            this.root = new Node();
            this.pos = this.root;
            this.sb = new StringBuilder();
        }

        private final Node root;
        private final StringBuilder sb;
        private Node pos;

        protected void putBinaryOperator(String str) {
            getNode(str).isBinaryOperator = true;
        }

        protected void putValue(String str, Token type) {
            getNode(str).type = type;
        }

        private Node getNode(String str) {
            Node v = root;

            for (char ch: str.toCharArray()) {
                v.nodes.putIfAbsent(ch, new Node());
                v = v.nodes.get(ch);
            }

            return v;
        }

        protected void parseToken() {
            while (pos.nodes.containsKey(ch)) {
                pos = pos.nodes.get(ch);
                sb.append(ch);
                nextChar();
            }
        }

        private String checkForWordType(String value, Token type) throws ParserException{
            if (value != null && (value.length() == 0 || isLetter(value.charAt(0)) && (isDigit() || isLetter()))) {
                throw invalidTokenException(type, value);
            }

            return value;
        }

        protected boolean isBinaryOperator() {
            return pos.isBinaryOperator;
        }

        protected boolean isMatch(Token type) {
            return type != null && pos.type == type;
        }

        protected String getBinaryOperator() throws ParserException {
            return isBinaryOperator() ? checkForWordType(toRoot(), Token.BINARY) : null;
        }

        protected String getValue(Token type) throws ParserException {
            return isMatch(type) ? checkForWordType(toRoot(), type) : null;
        }

        protected String toRoot() {
            String value = sb.length() == 0 ? null : sb.toString();
            pos = root;
            sb.setLength(0);
            return value;
        }

        private class Node {
            private final Map<Character, Node> nodes = new HashMap<>();
            private boolean isBinaryOperator = false;
            private Token type = null;
        }
    }
}