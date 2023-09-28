import java.util.*;
import java.util.stream.IntStream;

public class Stream {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] splittedInput = input.trim().split("\\s*,\\s*");

        int[] numbers = Arrays
                .stream(splittedInput)
                //.mapToInt(e -> Integer.parseInt(e))
                .mapToInt(Integer::parseInt)
                .toArray();
    }


}
