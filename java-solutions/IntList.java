import java.util.*;
import java.util.PrimitiveIterator;

public class IntList {
    public int size = 0;
    private int[] array = new int[1];

    public int get(int i) {
        return array[i];
    }

    public void set(int i, int value) {
        array[i] = value;
    }

    public void pushBack(int value) {
        if (array.length == size) {
            array = Arrays.copyOf(array, array.length * 2);
        }

        array[size++] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size);
        if (size != 0) {
            sb.append(array[0]);
            for (int i = 1; i < size; i++) {
                sb.append(' ').append(array[i]);
            }
        }
        return sb.toString();
    }
}
