package expression.exceptions;

public class MyMath {
    public static int abs(int value) {
        return value < 0 ? -value : value;
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
}
