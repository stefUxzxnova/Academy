import javax.swing.plaf.PanelUI;
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
        //textFilter(scanner);
        //digitLettersOther(scanner);
        //validUsernames(scanner);
        //extractFile(scanner);
        //caesarCipher(scanner);
        //multiplyBigNumber(scanner);
        //replaceRepeatingChars(scanner);
        //extractPersonInfo(scanner);
        //asciiSimulator(scanner);
        //morseCodeTranslator(scanner);
        //htmlIdentifier(scanner);
        //letter(scanner);
        //matchFullName(scanner);
        //matchPhoneNumber(scanner);
        //pascalCaseSplitter(scanner);
        //starBattlesEnigma(scanner);
        matchDate(scanner);
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

    //5.
    public static void digitLettersOther(Scanner scanner){
        String input = scanner.nextLine();

        //"([\\w_\\-]+)@([\\w_\\-])+\\.([\\w](2,4))";
        String regex = "(?<digit>\\d)+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<Integer> digits = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        StringBuilder symbols = new StringBuilder();
        while (matcher.find()) {
            digits.add(Integer.parseInt(matcher.group()));
        }

        regex = "(?<letters>[a-z]|[A-Z])+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);
        while (matcher.find()){
            str.append(matcher.group());
        }

        regex ="(?<symbols>\\W)+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);
        while (matcher.find()){
            symbols.append(matcher.group());
        }

        //print
        for (int digit : digits) {
            System.out.print(digit);
        }
        System.out.println();
        System.out.println(str);
        System.out.println(symbols);
    }

    //6.
    public static void validUsernames(Scanner scanner){
        String[] usernames = Arrays.stream(scanner.nextLine().trim().split(", "))
                .toArray(String[]::new);

        String regex = "[[a-z][A-Z][1-9]-_]{3,16}";
        Pattern pattern = Pattern.compile(regex);
        for ( String username : usernames) {
            Matcher matcher = pattern.matcher(username);
            if (matcher.find()) {
                if (matcher.group().equals(username)) {
                    System.out.println(username);
                }
            }
        }
    }

    //7.
    public static void extractFile(Scanner scanner){
        String path = scanner.nextLine();
        String regex = "((?<fileName>[a-zA-Z._]+)[.]+" +
                "(?<extension>[a-z]+))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            System.out.printf("File name: %s%n", matcher.group("fileName"));
            System.out.printf("File extension: %s%n", matcher.group("extension"));
        }
    }

    //8.
    public static void caesarCipher(Scanner scanner){
        String text = scanner.nextLine();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int newChar = text.charAt(i) + 3;
            res.append((char) newChar);
        }

        System.out.println(res);
    }

    //9.
    public static void multiplyBigNumber(Scanner scanner) {
        int[] number = Arrays.stream(scanner.nextLine().trim().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int multiplicative = Integer.parseInt(scanner.nextLine());
        StringBuilder str = new StringBuilder();
        //да пазим с колко наум сме увеличили следващото число, което ще умножим
        int temp = 0;
        for (int i = number.length - 1; i >= 0; i--) {
            int multiplication = multiplyNumbers(number[i], multiplicative) + temp;
            int ed = multiplication % 10 ;
            temp = 0;
            int dec = multiplication / 10;

            if (dec == 0) {
                str.insert(0, ed);
            } else if (i == 0) {
                str.insert(0, multiplication);
            } else{
               temp = dec;
               str.insert(0, ed);
            }
        }

        System.out.println(str);
    }

    private static int multiplyNumbers(int num1, int num2){
        return num1 * num2;
    }

    //10.
    public static void replaceRepeatingChars(Scanner scanner){
        String input = scanner.nextLine();
        StringBuilder res = new StringBuilder();
        char previousChar = ' ';
        for (int i = 0; i < input.length(); i++) {
            if (previousChar == ' ') {
                previousChar = input.charAt(i);
                res.append(previousChar);
                continue;
            }
            if (input.charAt(i) != previousChar) {
                res.append(input.charAt(i));
                previousChar = input.charAt(i);
            }
        }
        System.out.println(res);
    }

    //11.
    public static void extractPersonInfo(Scanner scanner){
        int numberOfLines = Integer.parseInt(scanner.nextLine());
        //String regex = "(@(?<name>[A-Z]{1}[a-z]+)\\|)|(#(?<age>[1-9]+)\\*)";
        String regex = "(@(?<name>[A-Za-z]+)\\|)|(#(?<age>[1-9]+)\\*)";
        Pattern pattern = Pattern.compile(regex);
        String name = null;
        int age = 0;
        Matcher matcher = null;
        for (int i = 1; i <= numberOfLines; i++) {
            name = null;
            age = 0;
            String input = scanner.nextLine();
            matcher = pattern.matcher(input);
            while(matcher.find()){
                if (name == null) {
                    name = matcher.group("name");
                }
                if (age == 0) {
                    try {
                        age = Integer.parseInt(matcher.group("age"));
                    }finally {
                        continue;
                    }
                }
            }

            System.out.printf("%s is %d years old.%n",name, age);
        }

    }

    //12.
    public static void asciiSimulator(Scanner scanner) {
        char symbol1 = scanner.nextLine().charAt(0);
        char symbol2 = scanner.nextLine().charAt(0);
        char[] chars = scanner.nextLine().toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (symbol1 < symbol2) {
                if (symbol1 < chars[i] && chars[i] < symbol2) {
                    sum += chars[i];
                }
            }else{
                if (symbol1 > chars[i] && chars[i] > symbol2) {
                    sum += chars[i];
                }
            }
        }
        System.out.println(sum);
    }

    //13.
    public static void morseCodeTranslator(Scanner scanner){
        Map<String, String> morseToEnglish = new HashMap<>();
        morseToEnglish.put(".-", "A");
        morseToEnglish.put("-...", "B");
        morseToEnglish.put("-.-.", "C");
        morseToEnglish.put("-..", "D");
        morseToEnglish.put(".", "E");
        morseToEnglish.put("..-.", "F");
        morseToEnglish.put("--.", "G");
        morseToEnglish.put("....", "H");
        morseToEnglish.put("..", "I");
        morseToEnglish.put(".---", "J");
        morseToEnglish.put("-.-", "K");
        morseToEnglish.put(".-..", "L");
        morseToEnglish.put("--", "M");
        morseToEnglish.put("-.", "N");
        morseToEnglish.put("---", "O");
        morseToEnglish.put(".--.", "P");
        morseToEnglish.put("--.-", "Q");
        morseToEnglish.put(".-.", "R");
        morseToEnglish.put("...", "S");
        morseToEnglish.put("-", "T");
        morseToEnglish.put("..-", "U");
        morseToEnglish.put("...-", "V");
        morseToEnglish.put(".--", "W");
        morseToEnglish.put("-..-", "X");
        morseToEnglish.put("-.--", "Y");
        morseToEnglish.put("--..", "Z");
        String[] input = Arrays.stream(scanner.nextLine().split("\\|"))
                .toArray(String[]::new);
        StringBuilder result = new StringBuilder();
        for (String word : input ) {
            String[] components = word.trim().split(" ");
            for (String component : components) {
                if (morseToEnglish.containsKey(component)) {
                    result.append(morseToEnglish.get(component));
                }
            }
            result.append(" ");
        }
        System.out.println(result);
    }

    //14.
    public static void htmlIdentifier(Scanner scanner){
        String title = scanner.nextLine();
        String content = scanner.nextLine();
        List<String> comments = new ArrayList<>();
        while(true){
            String comment = scanner.nextLine();
            if (comment.equalsIgnoreCase("end of comments")) {
                break;
            }
            comments.add(comment);
        }
        printH1(title);
        printArticle(content);
        for (String comment : comments) {
            printDiv(comment);
        }

    }
    private static void printH1(String title){
        System.out.println("<h1>");
        System.out.printf("    %s%n", title);
        System.out.println("</h1>");
    }
    private static void printArticle(String content){
        System.out.println("<article>");
        System.out.printf("    %s%n", content);
        System.out.println("</article>");
    }
    private static void printDiv(String comment){
        System.out.println("<div>");
        System.out.printf("    %s%n", comment);
        System.out.println("</div>");
    }

    //15.
    public static void letter(Scanner scanner){
        String[] input = Arrays.stream(scanner.nextLine().trim().split("\\.',"))
                .toArray(String[]::new);
        //we use it for our new string
        StringBuilder res = new StringBuilder(input[0]);
        //we will store the words here
        List<String> words = new ArrayList<>();
        //finds the words in the array
        String regex = "('(?<word>[a-zA-Z]+)')";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input[1]);
        //add them to the list
        while (matcher.find()){
            words.add(matcher.group("word"));
        }
        //our new regex is to match the gaps in the text
        regex = "(_+)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input[0]);
        //traverse all the matches and search for a word in the array with the same length
        while(matcher.find()){
            for (String word : words) {
                if (word.length() == matcher.group().length()) {
                   res.replace(matcher.start(), matcher.end(), word);
                   break;
                }
            }
        }
        System.out.println(res + ".");
    }

    //16.
    public static void matchFullName(Scanner scanner) {
        List<String> names = Arrays.stream(scanner.nextLine().trim().split(", "))
                .toList();
        String regex = "([A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+)";
        Pattern pattern = Pattern.compile(regex);

        for (String name : names) {
            Matcher matcher = pattern.matcher(name);
            if (matcher.find() && matcher.group().equals(name)) {
                System.out.println(name);
            }
        }
    }

    //17.
    public static void matchPhoneNumber(Scanner scanner) {
        List<String> phones = Arrays.stream(scanner.nextLine().trim().split(","))
                .toList();

        String regex = "(\\+359\\s2\\s[1-9]{3}\\s[1-9]{4})|(\\+359-2-[1-9]{3}-[1-9]{4})";
        Pattern pattern = Pattern.compile(regex);

        for (String phone : phones) {
            Matcher matcher = pattern.matcher(phone);
            if (matcher.find() && matcher.group().equals(phone)) {
                System.out.println(phone);
            }
        }
    }

    //18.
    public static void pascalCaseSplitter(Scanner scanner){
        String input = scanner.nextLine();
        String regex = "([A-Z]{1}[a-z]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> words = new ArrayList<>();
        while(matcher.find()){
            words.add(matcher.group());
        }
        for (String word : words) {
            System.out.printf("%s,", word);
        }
    }

    //19.
    public static void matchDate(Scanner scanner){
        String dates = scanner.nextLine();
        String regex = "([0-9]{2}(?<separator>[/\\.-]){1}[A-Z]{1}[a-z]{2}(\\k<separator>{1})[0-9]{4})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dates);
        //finds valid dates
        while (matcher.find()){
            //check if the separator between the day, month and year are the same
            String separator = matcher.group("separator");
            boolean sameSeparator = matcher.group().replace(separator, "").matches("^(\\d{2}" + separator + "\\d{2}" + separator + "\\d{4})$");

            if (sameSeparator) {
                System.out.println("mqu");
            } else {
                printDate(matcher.group(), separator);
            }
        }
    }
    //print
    private static void printDate(String date, String separator){
        if (separator.equals(".")) {
            separator = "\\.";
        }
        String[] dateComponents = date.split(separator);
        System.out.printf("Day: %s, Month: %s, Year: %s%n", dateComponents[0], dateComponents[1], dateComponents[2]);

    }


    //20.
    //TEST2
    //tt("DGsvywgerx>5444444444%H%1B9444
    //GQhrr|A977777(H(TTTT
    //EHfsytsnhf?8555&I&2C9555SR
    public static void starBattlesEnigma(Scanner scanner) {
        int numberOfMassages = Integer.parseInt(scanner.nextLine());
        int key;
        List<String> attackedPlanets = new ArrayList<>();
        List<String> destroyedPlanets = new ArrayList<>();
        String matchSTAR = "[starSTAR]";
        Pattern pattern = Pattern.compile(matchSTAR);
        Matcher matcher = null;
        for (int i = 1; i <= numberOfMassages; i++) {
            //check how many times the letters s,t,a,r,A,T,A,R
            //are met in the message
            String message = scanner.nextLine();
            matcher = pattern.matcher(message);
            key = 0;
            while (matcher.find()){
                key++;
            }
            //subtract with the key value every symbol in the message
            char[] messageChars = message.toCharArray();
            for (int j = 0; j < messageChars.length; j++) {
                messageChars[j] -= key;
            }
            String resultMessage = new String(messageChars);
            String regex = "[@]{1}(?<planet>[a-zA-Z]+)|[:]{1}(?<population>[0-9]+)" +
                    "|[!]{1}(?<attackType>[AD])[!]{1}|(->){1}(?<soldierCount>[0-9]+)";
            Pattern pat = Pattern.compile(regex);
            Matcher match = pat.matcher(resultMessage);
            String planet = null;
            String population = null;
            String attackType = null;
            String soldierCount = null;
            while (match.find()) {
                if (planet == null) {
                    planet = match.group("planet");
                }
                if (population == null) {
                    population = match.group("population");
                }
                if (attackType == null) {
                    attackType = match.group("attackType");
                }
                soldierCount = match.group("soldierCount");
            }
            if (soldierCount == null || attackType == null || planet == null || population == null) {
                continue;
            }
            switch (attackType){
                case "A":
                    attackedPlanets.add(planet);
                    break;
                case "D":
                    destroyedPlanets.add(planet);
                    break;
            }
        }
        System.out.println("Attacked planets: " + attackedPlanets.size());
        for (String planet : attackedPlanets) {
            System.out.println("-> " + planet);
        }
        System.out.println("Destroyed planets: " + destroyedPlanets.size());
        for (String planet : destroyedPlanets) {
            System.out.println("-> " + planet);
        }
    }
}