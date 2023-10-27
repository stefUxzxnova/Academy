import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayCreator {

    @SuppressWarnings("unchecked")
    public static <T> T[] create(int length, T item){
        if (length < 0) {
            throw new IllegalArgumentException("Array length can not be " + length);
        }
        T[] array = (T[])new Object[length];
        for (int i = 0; i < array.length ; i++) {
            array[i] = item;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] create(Class<T> classItem ,int length, T item){
        if (length < 0) {
            throw new IllegalArgumentException("Array length can not be " + length);
        }
        T[] array = (T[])Array.newInstance(classItem, length);
        for (int i = 0; i < array.length; i++) {
            array[i] = item;
        }
        return array;
    }

    public static <T> T[] getAll(T[] array){
        return (T[]) Arrays.stream(array).toArray();
    }
}
