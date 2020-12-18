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
            unFactories.factories.put(un.getSymbol(), un.getFactory());
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
            if (operatorToRank.containsKey(operator)) {
                return operatorToRank.get(operator);
            }

            throw new IllegalStateException("Unknown operator");
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

    // Map: Operator name -> Operator factory
    protected class UnaryOperators {
        private final Map<String, UnaryOperator<CommonExpression>> factories = new HashMap<>();

        protected UnaryOperator<CommonExpression> getOperator(String operator) {
            return factories.get(operator);
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