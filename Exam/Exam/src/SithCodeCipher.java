import java.util.Arrays;
import java.util.Scanner;

public class SithCodeCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] message = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" "))
                .toArray(String[]::new);

        int n = Integer.parseInt(scanner.nextLine());
        while (n < 1 || n > 25){
            n = Integer.parseInt(scanner.nextLine());
        }

        for (int i = 0; i < message.length; i++) {
            char[] word = message[i].toCharArray();
            for (int j = 0; j < word.length; j++) {
                word[j] -= n;
            }
            message[i] = new String(word);
        }

        for (var word: message) {
            System.out.print(word + " ");
        }
    }
}
