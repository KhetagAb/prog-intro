package expression.parser;

import expression.*;

import java.util.List;
import java.util.function.BinaryOperator;

public class ExpressionParser extends AbstractExpressionParser implements Parser {
    private static final List<List<Pair>> BINARY_OPERATORS = List.of(
            List.of(new Pair("|", Or::new)),
            List.of(new Pair("^", XOR::new)),
            List.of(new Pair("&", And::new)),
            List.of(new Pair("+", Add::new), new Pair("-", Subtract::new)),
            List.of(new Pair("*", Multiply::new), new Pair("/", Divide::new))
    );

    private static final UnaryOperation[] UNARY_OPERATORS = new UnaryOperation[] {
            new Negate(null), new Flip(null), new Low(null)
    };


    public ExpressionParser() {
        super(BINARY_OPERATORS, UNARY_OPERATORS);
    }

    @Override
    public TripleExpression parse(String expression) {
        setSource(new StringSource(expression));
        return parseExpression();
    }
}

class Pair {
    private String key;
    private BinaryOperator<CommonExpression> value;

    public Pair(String key, BinaryOperator<CommonExpression> value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public BinaryOperator<CommonExpression> getValue() {
        return value;
    }
}