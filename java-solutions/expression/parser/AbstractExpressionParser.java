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

    protected AbstractExpressionParser(final BinaryOperation[] binOperations, final UnaryOperation[] unOperations) {
        this.ranks = new OperatorsRank();
        this.binFactories = new BinaryOperators();
        this.unFactories = new UnaryOperators();

        for (BinaryOperation bin: binOperations) {
            addBinOperator(bin.getSymbol(), bin.getRank(), bin.getFactory());
        }

        for (UnaryOperation un: unOperations) {
            addUnOperator(un.getSymbol(), un.getFactory());
        }
    }

    protected void addBinOperator(String symbol, int rank, BinaryOperator<CommonExpression> factory) {
        ranks.ranks.add(rank);
        ranks.operatorToRank.put(symbol, rank);
        binFactories.factories.put(symbol, factory);
    }

    protected void addUnOperator(String symbol, UnaryOperator<CommonExpression> factory) {
        unFactories.put(symbol, factory);
    }

    protected class OperatorsRank {
        private final NavigableSet<Integer> ranks = new TreeSet<>() {};
        private final Map<String, Integer> operatorToRank = new HashMap<>();

        protected int getRank(String operator) {
            Integer rank = operatorToRank.get(operator);

            if (rank != null) {
                return rank;
            } else {
                throw new IllegalStateException("Unknown operator");
            }
        }

        protected int getNextRank(int rank) {
            return Objects.requireNonNullElse(ranks.higher(rank), getMaxRank());
        }

        protected int getMaxRank() {
            return Integer.MAX_VALUE;
        }
    }

    // Map: Operator name -> Operator factory
    protected class BinaryOperators {
        private final Map<String, BinaryOperator<CommonExpression>> factories = new HashMap<>();

        protected CommonExpression buildExpression(String operator, CommonExpression left, CommonExpression right) {
            BinaryOperator<CommonExpression> factory = factories.get(operator);

            if (factory != null) {
                return factory.apply(left, right);
            } else {
                throw new IllegalStateException("Unknown operator");
            }
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
                v.nodes.putIfAbsent(ch, new Node());
                v = v.nodes.get(ch);
            }

            v.factory = factory;
        }

        public boolean check(char ch) {
            if (pos.nodes.containsKey(ch)) {
                pos = pos.nodes.get(ch);
                return true;
            } else {
                return false;
            }
        }

        public boolean isOperator() {
            return pos.factory != null;
        }

        public UnaryOperator<CommonExpression> getOperator() {
            UnaryOperator<CommonExpression> operator = pos.factory;
            toStart();

            return operator;
        }

        public void toStart() {
            pos = root;
        }

        private class Node {
            private final Map<Character, Node> nodes = new HashMap<>();
            private UnaryOperator<CommonExpression> factory = null;
        }
    }
}
