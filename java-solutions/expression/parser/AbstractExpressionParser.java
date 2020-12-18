package expression.parser;

import expression.BinaryOperation;
import expression.CommonExpression;
import expression.UnaryOperation;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public abstract class AbstractExpressionParser extends BaseParser {
    protected final OperatorsRank ranks;
    protected final UnaryOperators unFactories;
    protected final BinaryOperators binFactories;

    protected AbstractExpressionParser(final List<List<BinaryOperation>> binOperations, final UnaryOperation[] unOperations) {
        this.ranks = new OperatorsRank();
        this.binFactories = new BinaryOperators();
        this.unFactories = new UnaryOperators();

        for (List<BinaryOperation> bins: binOperations) {
            ranks.addLevel(bins);
            for (BinaryOperation bin : bins) {
                binFactories.factories.put(bin.getSymbol(), bin.getFactory());
            }
        }

        for (UnaryOperation un: unOperations) {
            unFactories.put(un.getSymbol(), un.getFactory());
        }
    }

    protected class OperatorsRank {
        private int level = 0;
        private final Map<String, Integer> operatorToRank = new HashMap<>();

        private void addLevel(List<BinaryOperation> bins) {
            for (BinaryOperation bin : bins) {
                ranks.operatorToRank.put(bin.getSymbol(), level);
            }
            level++;
        }

        protected int getRank(String operator) {
            Integer rank = operatorToRank.get(operator);

            if (rank != null) {
                return rank;
            } else {
                throw new IllegalStateException("Unknown operator");
            }
        }

        protected int getNextRank(int rank) {
            return rank + 1;
        }

        protected int getMaxRank() {
            return level;
        }
    }

    // Map: Operator name -> Operator factory
    protected class BinaryOperators {
        private final Map<String, BinaryOperator<CommonExpression>> factories = new HashMap<>();

        protected BinaryOperator<CommonExpression> getOperator(String operator) {
            return factories.get(operator);
        }
    }

    // Prefix tree: Operator name -> Operator factory
    protected class UnaryOperators {
        public UnaryOperators() {
            this.root = new Node();
            this.pos = this.root;
        }

        private final Node root;
        private Node pos;

        public void put(String str, UnaryOperator<CommonExpression> factory) {
            Node v = root;

            for (char ch: str.toCharArray()) {
                if (!v.isNodeTo(ch)) {
                    v.setNode(ch, new Node());
                }

                v = v.getNode(ch);
            }

            v.factory = factory;
        }

        public boolean check(char ch) {
            if (pos.isNodeTo(ch)) {
                pos = pos.getNode(ch);
                return true;
            } else {
                return false;
            }
        }

        public boolean isOperator() {
            return pos.factory != null;
        }

        public UnaryOperator<CommonExpression> getOperator() {
            if (isOperator()) {
                UnaryOperator<CommonExpression> operator = pos.factory;
                toStart();

                return operator;
            }

            throw new IllegalStateException("Unknown operator");
        }

        public void toStart() {
            pos = root;
        }

        private class Node {
            // from U+0021 to U+007E
            private final char from = '!';
            private final char to = '~';

            private final Node[] nodes = new Node[to - from + 1];
            private UnaryOperator<CommonExpression> factory = null;

            private Node getNode(char ch) {
                checkInRange(ch);
                return nodes[ch - from];
            }

            private void setNode(char ch, Node node) {
                checkInRange(ch);
                nodes[ch - from] = node;
            }

            private boolean isNodeTo(char ch) {
                return isInRange(ch) && nodes[ch - from] != null;
            }

            private boolean isInRange(char ch) {
                return (from <= ch && ch <= to);
            }

            private void checkInRange(char ch) {
                if (!isInRange(ch)) {
                    throw new IllegalStateException("Invalid operators symbols");
                }
            }
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