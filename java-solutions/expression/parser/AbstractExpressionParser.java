package expression.parser;

import expression.*;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractExpressionParser extends BaseParser {
    protected final OperatorsRank ranks;
    protected final PrefixTreeOperator unaries;
    protected final PrefixTreeOperator binaries;
    private final Map<String, BinaryOperator<CommonExpression>> binaryFactories = new HashMap<>();
    private final Map<String, UnaryOperator<CommonExpression>> unaryFactories = new HashMap<>();

    protected AbstractExpressionParser(final List<List<Pair>> binOperations, final UnaryOperation[] unOperations) {
        this.ranks = new OperatorsRank();
        this.unaries = new PrefixTreeOperator();
        this.binaries = new PrefixTreeOperator();

        for (List<Pair> bins: binOperations) {
            ranks.addLevel(bins);
            for (Pair bin : bins) {
                binaries.put(bin.getKey());
                binaryFactories.put(bin.getKey(), bin.getValue());
            }
        }

        for (UnaryOperation un: unOperations) {
            unaries.put(un.getSymbol());
            unaryFactories.put(un.getSymbol(), un.getFactory());
        }
    }

    protected CommonExpression parseExpression() {
        return parseLevel(0);
    }

    protected CommonExpression parseLevel(int level) {
        if (level == ranks.getMaxRank()) {
            return parseValue();
        }

        skipWhitespace();
        int nextLevel = ranks.getNextRank(level);

        CommonExpression parsed = parseLevel(nextLevel);
        String operator = parseBinaryOperator(level);
        while (operator != null) {
            parsed = buildBinOperator(operator, parsed, parseLevel(nextLevel));
            operator = parseBinaryOperator(level);
        }

        return parsed;
    }

    protected CommonExpression buildBinOperator(String operator, CommonExpression left, CommonExpression right) {
        return getBinOperator(operator).apply(left, right);
    }

    protected CommonExpression buildUnOperator(String operator, CommonExpression expression) {
        return getUnOperator(operator).apply(expression);
    }

    protected CommonExpression parseValue() {
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

        String operator = parseUnaryOperator();
        return operator == null ? parseVariable() : buildUnOperator(operator, parseValue());
    }

    protected String parseBinaryOperator(int level) {
        skipWhitespace();

        int pos = 0;
        char current;
        do {
            current = forwardChar(pos++);
        } while (binaries.check(current) && !binaries.isOperator());

        String operator = binaries.getOperator();
        if (operator == null || ranks.getRank(operator) != level) {
            return null;
        } else {
            expect(operator);
            return operator;
        }
    }

    protected String parseUnaryOperator() {
        skipWhitespace();

        boolean isWordType = true;

        int pos = 0;
        char current;
        do {
            current = forwardChar(pos++);

            if (!isDigit(current) && !isLetter(current)) {
                isWordType = false;
            }

        } while (unaries.check(current) && !unaries.isOperator());

        String operator = null;
        if (unaries.isOperator()) {
            if (isWordType) {
                char next = forwardChar(pos);

                if (!isDigit(next) && !isLetter(next)) {
                    operator = unaries.getOperator();
                }
            } else {
                operator = unaries.getOperator();
            }
        } else {
            return null;
        }

        if (operator != null) {
            expect(operator);
        }

        return operator;
    }

    protected CommonExpression parseConst(final String prefix) {
        skipWhitespace();
        return new Const(Integer.parseInt(prefix + parseToken(BaseParser::isDigit)));
    }

    protected CommonExpression parseVariable() {
        skipWhitespace();
        return new Variable(parseToken(BaseParser::isLetter));
    }

    protected class OperatorsRank {
        private int level = 0;
        private final Map<String, Integer> operatorToRank = new HashMap<>();

        private void addLevel(List<Pair> bins) {
            for (Pair bin : bins) {
                ranks.operatorToRank.put(bin.getKey(), level);
            }
            level++;
        }

        protected int getRank(String operator) {
            return operatorToRank.get(operator);
        }

        protected int getNextRank(int rank) {
            return rank + 1;
        }

        protected int getMaxRank() {
            return level;
        }
    }

    protected boolean isBinOperator(String operator) {
        return binaryFactories.containsKey(operator);
    }

    protected BinaryOperator<CommonExpression> getBinOperator(String operator) {
        return binaryFactories.get(operator);
    }

    protected UnaryOperator<CommonExpression> getUnOperator(String operator) {
        return unaryFactories.get(operator);
    }

    // Prefix tree: Operator name -> Operator factory
    protected static class PrefixTreeOperator {
        public PrefixTreeOperator() {
            this.root = new Node();
            this.pos = this.root;
            this.sb = new StringBuilder();
        }

        private final Node root;
        private Node pos;
        private StringBuilder sb;

        public void put(String str) {
            Node v = root;

            for (char ch: str.toCharArray()) {
                v.nodes.putIfAbsent(ch, new Node());
                v = v.nodes.get(ch);
            }

            v.isOperator = true;
        }

        public boolean check(char ch) {
            if (pos.nodes.containsKey(ch)) {
                pos = pos.nodes.get(ch);
                sb.append(ch);
                return true;
            } else {
                return false;
            }
        }

        public boolean isOperator() {
            return pos.isOperator;
        }

        public String getOperator() {
            if (!isOperator()) {
                return null;
            }

            String operator = sb.toString();
            toStart();

            return operator;
        }

        public void toStart() {
            pos = root;
            sb.setLength(0);
        }

        private class Node {
            private final Map<Character, Node> nodes = new HashMap<>();
            private boolean isOperator = false;
        }
    }
}

/*
// :NOTE: Не enum
@Deprecated
public enum Operations {
    OR("|", 0, Or::new),
    XOR("^", 1),
    AND("&", 2),
    ADD("+", 3), SUB("-", 3),
    MUL("*", 4), DIV("/", 4),
    MINUS("-", 5), FLIP("flip", 5), LOW("low", 5), CONST("", 5), VAR("", 5)
    ;

    private final String symbol;
    private final int rank;
    private final BinaryOperator<CommonExpression> factory;

    Operations(final String symbol, final int rank, final BinaryOperator<CommonExpression> factory) {
        this.symbol = symbol;
        this.rank = rank;
        this.factory = factory;
    }

    Operations(String symbol, int rank) {
        this(symbol, rank, null);
    }

    public int getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public CommonExpression create(CommonExpression left, CommonExpression right) {
        return factory.apply(left, right);
    }


    // :NOTE: Копипаста
    public static final Map<String, Operations> BINARY_OPERAND = Map.of(
            OR.symbol, OR,
            XOR.symbol, XOR,
            AND.symbol, AND,
            ADD.symbol, ADD,
            SUB.symbol, SUB,
            MUL.symbol, MUL,
            DIV.symbol, DIV
    );
} */