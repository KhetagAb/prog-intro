package expression;

import java.util.Map;

public enum Operations {
    OR("|", 0),
    XOR("^", 1),
    AND("&", 2),
    ADD("+", 3), SUB("-", 3),
    MUL("*", 4), DIV("/", 4),
    MINUS("-", 5), FLIP("flip", 5), LOW("low", 5), CONST("", 5), VAR("", 5);

    Operations(String symbol, int rank) {
        this.SYMBOL = symbol;
        this.RANK = rank;
    }

    private final String SYMBOL;
    private final int RANK;

    public int getRank() {
        return RANK;
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public static final Map<String, Operations> BINARY_OPERAND = Map.of(
            OR.SYMBOL, OR,
            XOR.SYMBOL, XOR,
            AND.SYMBOL, AND,
            ADD.SYMBOL, ADD, SUB.SYMBOL, SUB,
            MUL.SYMBOL, MUL, DIV.SYMBOL, DIV
    );
}
