package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();

        System.out.println(parser.parse("10").toMiniString());
    }
}

/*

flip – число с переставленными двоичными цифрами, flip 12345 равно 9987, flip -12345 равно -470548481;
low – минимальный установленный бит (как в lowestOneBit), low 123456 равно 64.

 */
