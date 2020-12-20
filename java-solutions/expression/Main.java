package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.ExpressionParser;
import expression.exceptions.OperationOverflowException;
import expression.exceptions.ParserException;

public class Main {
    public static void main(String[] args) throws ParserException {
        /*
            op("Start symbol", "@x * y"),
            op("Middle symbol", "x @ * y"),
            op("End symbol", "x * y@"),
            op("Constant overflow 1", Integer.MIN_VALUE - 1L + ""),
            op("Constant overflow 2", Integer.MAX_VALUE + 1L + ""),
            op("Bare +", "+"),
            op("Bare -", "-"),
            op("Bare a", "a"),
            op("(())", "(())"),
            op("Spaces in numbers", "10 20")
         */
        new ExpressionParser().parse("x*y+(z-1)/10");

        try {
            TripleExpression expression = new ExpressionParser().parse("1000000*x*x*x*x*x/(x-1)");

            for (int i = 0; i <= 10; i++) {
                try {
                    int result = expression.evaluate(i, 0, 0);

                    System.out.println(result);
                } catch (OperationOverflowException e) {
                    System.out.println("overflow");
                } catch (DivideByZeroException e) {
                    System.out.println("division by zero");
                }
            }
        } catch (ParserException e) {
            System.out.println("Parsing error: " + e.getMessage());
        }
    }
}