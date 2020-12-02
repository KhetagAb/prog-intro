package expression;

import java.util.Objects;

public class Variable implements CommonExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            return name.equals(((Variable) obj).name);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
