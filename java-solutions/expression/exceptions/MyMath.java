package expression.exceptions;

public class MyMath {
    public static int checkedAdd(int left, int right) {
        return checkedAdd(left, right, "+");
    }
    public static int checkedAdd(int left, int right, String symbol) {
        if (left > 0 && Integer.MAX_VALUE - left < right ||
                left < 0 && Integer.MIN_VALUE - left > right)
        {
            throw new OperationOverflowException(left + symbol + right);
        }

        return left + right;
    }

    public static int checkedSubtract(int left, int right) {
        return checkedSubtract(left, right, "-");
    }
    public static int checkedSubtract(int left, int right, String symbol) {
        if (right < 0 && Integer.MAX_VALUE + right < left ||
                right > 0 && Integer.MIN_VALUE + right > left)
        {
            throw new OperationOverflowException(left + symbol + right);
        }

        return left - right;
    }

    public static int checkedMultiply(int left, int right) {
        return checkedMultiply(left, right, "*");
    }
    public static int checkedMultiply(int left, int right,  String symbol) {
        if (left > 0 && right > 0 && Integer.MAX_VALUE / left < right ||
                left < 0 && right < 0 && Integer.MAX_VALUE / right > left ||
                left > 0 && right < 0 && Integer.MIN_VALUE / left > right ||
                left < 0 && right > 0 && Integer.MIN_VALUE / right > left)
        {
            throw new OperationOverflowException(left + symbol + right);
        }

        return left * right;
    }

    public static int checkedDivide(int left, int right) {
        return checkedDivide(left, right, "/");
    }
    public static int checkedDivide(int left, int right, String symbol) {
        if (right == 0) {
            throw new DivideByZeroException();
        } else if (left == Integer.MIN_VALUE && right == -1) {
            throw new OperationOverflowException(left + symbol + right + " = overflow");
        }

        return left / right;
    }

    public static int checkedNegate(int value) {
        return checkedNegate(value, "-");
    }
    public static int checkedNegate(int value, String symbol) {
        if (value == Integer.MIN_VALUE) {
            throw new OperationOverflowException(symbol + value);
        }

        return -value;
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
        return checkedMultiply(a / gcd, b);
    }

    public static int abs(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OperationOverflowException("Abs of " + value);
        }

        return value < 0 ? -value : value;
    }
}
