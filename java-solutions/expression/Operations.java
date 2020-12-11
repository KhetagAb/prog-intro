package expression;

import java.util.Map;
import java.util.function.BinaryOperator;

// :NOTE: Не enum
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
}
