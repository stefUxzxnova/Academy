import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class BalancedLightsaberDuels {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(""))
                .toArray(String[]::new);

        ArrayDeque<String> stack = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("(") || array[i].equals("{")) {
                if (count == 1) {
                    System.out.println("Not Legendary");
                    return;
                }else {
                    stack.push(array[i]);
                }

            }
            else if (array[i].equals("!")) {
                if (count == 0) {
                    stack.push(array[i]);
                    count = 1;
                } else{
                    stack.pop();
                    count = 0;
                }
            }
            else{
                switch (array[i]){
                    case ")":
                        if (stack.peek().equals("(")) {
                            stack.pop();
                        }else {
                            System.out.println("Not Legendary");
                            return;
                        }
                        break;
                    case "}":
                        if (stack.peek().equals("{")) {
                            stack.pop();
                        }else {
                            System.out.println("Not Legendary");
                            return;
                        }
                        break;
                }
            }

        }
        if (stack.size() == 0) {
            System.out.println("Legendary");
        }else{
            System.out.println("Not Legendary");
        }

    }
}
