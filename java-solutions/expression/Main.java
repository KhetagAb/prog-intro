package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.ExpressionParser;
import expression.exceptions.OperationOverflowException;
import expression.exceptions.ParserException;

public class Main {
    public static void main(String[] args) {
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