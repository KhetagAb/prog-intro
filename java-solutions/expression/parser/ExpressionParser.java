package expression.parser;

import expression.TripleExpression;

public class ExpressionParser implements Parser {
    @Override
    public TripleExpression parse(String expression) {
        return this.parse(new StringSource(expression));
    }

    public TripleExpression parse(StringSource expression) {
        return new Parser(expression).parse();
    }

    private static class Parser extends BaseParser {
        protected Parser(CharSource source) {
            super(source);
        }

        private TripleExpression parse() {
            /* toDo


            4 + 4 * 4



             */
            return new TripleExpression() {
                @Override
                public int evaluate(int x, int y, int z) {
                    return 0;
                }
            };
        }
    }
}
