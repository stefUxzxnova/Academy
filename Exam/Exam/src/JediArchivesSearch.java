import java.util.Arrays;
import java.util.Scanner;

public class JediArchivesSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String word = scanner.nextLine();
        int firstOccurance = -1;
        int lastOccurance = -1;

        if (!input.contains(word)) {
            System.out.println("Record not found");
            return;
        }

        String[] array = Arrays.stream(input
                        .trim()
                        .split(", "))
                .toArray(String[]::new);

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(word)) {
                if (firstOccurance == -1) {
                    firstOccurance = i;
                    if (i == array.length - 1) {
                        lastOccurance = firstOccurance;
                    }
                } else{
                    lastOccurance = i;
                }
            }
        }
        System.out.println("First Occurance: " + firstOccurance);
        System.out.println("Last Occurance: " + lastOccurance);
    }
}
