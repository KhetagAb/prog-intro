package expression;

import expression.exceptions.*;

public class Main {
    public static void main(String[] args) {
        int l = 0, r = 10;

        try {
            TripleExpression expression = new ExpressionParser().parse("1000000*x*x*x*x*x/(x-1)");

            for (int i = l; i <= r; i++) {
                try {
                    int result = expression.evaluate(i, 0, 0);

                    System.out.println(result);
                } catch (ExpressionOverflowException e) {
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