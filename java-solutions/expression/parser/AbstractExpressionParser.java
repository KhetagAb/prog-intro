package expression.parser;

import expression.*;
import expression.exceptions.ParserException;

import java.util.*;
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
        expect('\0');

        return parsed;
    }

    protected CommonExpression parseLevel(int level) throws ParserException {
        if (level == getMaxRank()) {
            return parseMaxLevel();
        }

        CommonExpression parsed = parseLevel(level + 1);

        while (true) {
            parseBinaryOperator();

            if (lastOperator == null || getRank(lastOperator) != level)
                break;

            // toDo simplify
            String operator = lastOperator;
            lastOperator = null;

            parsed = buildBinOperator(operator, parsed, parseLevel(level + 1));
        }

        return parsed;
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
        } else if (isDigit()) {
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

    protected void parseBinaryOperator() throws ParserException {
        if (lastOperator != null) {
            return;
        }

        skipWhitespace();
        tokens.parseToken();

        String operator = tokens.getBinaryOperator();
        // toDo Check WTF WITH ')'?
        if (operator == null && ch != '\0' && ch != ')') {
            throw error("Expected binary operator, found " + formatString(tokens.getValue(Token.NotAValue)));
        }

        lastOperator = operator;
    }

    protected CommonExpression parseValue() throws ParserException {
        skipWhitespace();
        tokens.parseToken();

        String value = tokens.getValue(Token.UNARY);
        
        if (value != null) {
            return buildUnOperator(value, parseMaxLevel());
        } else {
            value = tokens.getValue(Token.VARIABLE);

            if (value != null) {
                return new Variable(value);
            } else {
                throw error("Expected variable, found: " + formatString(tokens.getValue(Token.NotAValue)));
            }
        }
    }

    protected CommonExpression buildBinOperator(String operator, CommonExpression left, CommonExpression right) {
        return getBinaryFactory(operator).apply(left, right);
    }

    protected CommonExpression buildUnOperator(String operator, CommonExpression expression) {
        return getUnaryFactory(operator).apply(expression);
    }

    protected BinaryOperator<CommonExpression> getBinaryFactory(String operator) {
        return binaryFactories.get(operator);
    }

    protected UnaryOperator<CommonExpression> getUnaryFactory(String operator) {
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

        private void putBinaryOperator(String str) {
            getNode(str).isBinaryOperator = true;
        }

        private void putValue(String str, Token type) {
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

        private void parseToken() {
            while (pos.nodes.containsKey(ch)) {
                pos = pos.nodes.get(ch);
                sb.append(ch);
                nextChar();
            }
        }

        private boolean isBinaryOperator() {
            return pos.isBinaryOperator;
        }

        private boolean isMatch(Token type) {
            return pos.type == type;
        }

        // toDo check
        private String checkForWordType(String value, String type) throws ParserException {
            if (value != null && isLetter(value.charAt(0)) && (isDigit() || isLetter())) {
                throw error("Invalid " + type + " found " + formatString(value));
            } else {
                return value;
            }
        }

        private String getBinaryOperator() throws ParserException {
            return isBinaryOperator() ? checkForWordType(toRoot(), "binary operator") : null;
        }

        private String getValue(Token type) throws ParserException {
            if (type == Token.NotAValue && pos.type == Token.NotAValue) {
                return toRoot();
            } else {
                return isMatch(type) ? checkForWordType(toRoot(), type.getName()) : null;
            }
        }

        private String toRoot() {
            String value = sb.length() == 0 ? null : sb.toString();
            pos = root;
            sb.setLength(0);
            return value;
        }

        private class Node {
            private final Map<Character, Node> nodes = new HashMap<>();
            private boolean isBinaryOperator = false;
            private Token type = Token.NotAValue;
        }
    }
}

// toDo Optimal?
enum Token {
    VARIABLE("variable"), UNARY("unary operator"), NotAValue("");

    private final String name;

    Token(String value) {
        this.name = value;
    }

    protected String getName() {
        return name;
    }
}