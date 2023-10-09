import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// proba:
//        Integer n = 3;
//        int hashCode = System.identityHashCode(n);
//        System.out.println(Integer.toHexString(hashCode));
//        n = changeNum(n);
//        hashCode = System.identityHashCode(n);
//        System.out.println(Integer.toHexString(hashCode));
//        System.out.println(n);

        //reverseString(scanner);
        //repeatStrings(scanner);
        //substring(scanner);
        textFilter(scanner);

    }

//    private static Integer changeNum(Integer n) {
//        return ++n;
//    }

    //1.
    public static void reverseString(Scanner scanner){
        Map<String, String> words = new LinkedHashMap<>();
        while (true){
            String word = scanner.nextLine();
            if (word.equalsIgnoreCase("end")) {
                break;
            }
            StringBuilder reversedWord = new StringBuilder(word).reverse();
            words.put(word, reversedWord.toString());
        }
        printMap(words);
    }
    private static <K, V> void printMap (Map<K, V> mp){
        for (var entry : mp.entrySet()) {
            System.out.printf("%s = %s%n", entry.getKey(), entry.getValue());
        }
    }

    //2.
    public static void repeatStrings(Scanner scanner){
        String[] words = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" "))
                .toArray(String[]::new);
        StringBuilder strb = new StringBuilder();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                strb.append(word);
            }
        }
        System.out.println(strb);
    }
    public static void matcherExercise(Scanner scanner){
        String st = "hi abc";
        Pattern pattern = Pattern.compile("(?<Gosho>\\w*) (?<Ivan>\\w*)");
        Matcher matcher = pattern.matcher(st);

        if (matcher.find()) {
            String match = matcher.group(0);
            System.out.println(match);
        } else {
            System.out.println("No match found.");
        }

    }

    //3.
    public static void substring(Scanner scanner){
        Pattern pattern = Pattern.compile(scanner.nextLine());
        String text = scanner.nextLine();
        Matcher matcher = pattern.matcher(text);
        String res = null;

        while(matcher.find()){
            res = matcher.replaceAll("");
            matcher = pattern.matcher(res);
        }

        System.out.println(res);
    }

    //4.
    public static void textFilter(Scanner scanner){
        List<String> bannedWords = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(", "))
                .toList();
        String text = scanner.nextLine();
        for (String word : bannedWords) {
            Pattern pattern = Pattern.compile(word);
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                String replacement = "*".repeat(word.length());
                text = matcher.replaceAll(replacement);
            }
        }
        
        System.out.println(text);

    }
}