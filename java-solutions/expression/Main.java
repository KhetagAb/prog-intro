package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parse = new ExpressionParser();


        TripleExpression ex = parse.parse("-2147483648");

        System.out.println(ex.toMiniString());

        System.out.println(new Multiply(new Const(5), new Divide(new Const(3), new Const(1))).toMiniString());
        System.out.println(new Add(new Const(5), new Subtract(new Const(3), new Const(1))).toMiniString());
        System.out.println(new Subtract(new Const(5), new Subtract(new Const(3), new Const(1))).toMiniString());

        System.out.println(new XOR(new Const(5), new Subtract(new Minus(new And(new Const(4), new Variable("x"))), new Const(1))).toString());

        Subtract a = new Subtract(
                new Const(5),
                new Subtract(new Minus(
                        new And(
                                new Const(4),
                                new Variable("x"))),
                        new Minus(new Minus(new Flip(new Low(new Const(1))))))
        );

        Subtract b = new Subtract(
                new Const(5),
                new Subtract(new Minus(
                        new And(
                                new Const(4),
                                new Variable("x"))),
                        new Minus(new Minus(new Flip(new Low(new Const(1))))))
        );

        System.out.println(a.toMiniString());
        System.out.println(a.toString());
        System.out.println(a.equals(b));

        System.out.println(new Flip(new Const(12345)).evaluate(0));
        System.out.println(new Flip(new Const(-12345)).evaluate(0));

        System.out.println(new Low(new Const(123456)).evaluate(0));
    }
}

/*

flip – число с переставленными двоичными цифрами, flip 12345 равно 9987, flip -12345 равно -470548481;
low – минимальный установленный бит (как в lowestOneBit), low 123456 равно 64.

 */
// 5 ^ ((-(4 & x)) - 1)
