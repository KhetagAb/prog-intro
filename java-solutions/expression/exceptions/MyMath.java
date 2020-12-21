package expression.exceptions;

public class MyMath {
    public static boolean checkAddOverflow(int left, int right) {
        return left > 0 && Integer.MAX_VALUE - left < right ||
                left < 0 && Integer.MIN_VALUE - left > right;
    }

    public static boolean checkSubtractOverflow(int left, int right) {
        return right < 0 && Integer.MAX_VALUE + right < left ||
                right > 0 && Integer.MIN_VALUE + right > left;
    }

    public static boolean checkMultiplyOverflow(int left, int right) {
        return left > 0 && right > 0 && Integer.MAX_VALUE / left < right ||
                left < 0 && right < 0 && Integer.MAX_VALUE / right > left ||
                left > 0 && right < 0 && Integer.MIN_VALUE / left > right ||
                left < 0 && right > 0 && Integer.MIN_VALUE / right > left;
    }

    public static boolean checkDivideOverflow(int left, int right) {
        return left == Integer.MIN_VALUE && right == -1;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            a %= b;

            a ^= b;
            b ^= a;
            a ^= b;
        }

        return abs(a);
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        int gcd = MyMath.gcd(a, b);
        if (checkMultiplyOverflow(a / gcd, b)) {
            throw new OperationOverflowException("Lcm of " + a + ", " +  b);
        }

        return a / gcd * b;
    }

    public static int abs(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OperationOverflowException("Abs of " + value);
        }

        return value < 0 ? -value : value;
    }
}
