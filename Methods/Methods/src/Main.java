import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //signOfInteger(scanner);
        //grades(scanner);
        //printTriangle(scanner);
        //shop(scanner);
        //calculator(scanner);
        //repeatString(scanner);
        //mathPower(scanner);
        //greaterOfTwoValues(scanner);
        //multiplyEvensByOdds(scanner);
        //System.out.printf("%.0f", mathOperations(scanner));
        passwordValidator(scanner);
    }

    //1.
    public static void signOfInteger(Scanner scanner) {
        int num = Integer.parseInt(scanner.nextLine());
        if (num < 0) {
            System.out.printf("The number %d is negative", num);
        } else if (num == 0) {
            System.out.printf("The number %d is zero", num);
        } else {
            System.out.printf("The number %d is positive", num);
        }
    }

    //2.
    public static void grades(Scanner scanner) {
        double grade = Double.parseDouble(scanner.nextLine());
        if (grade < 2.00 || grade > 6.00) {
            System.out.println("Invalid grade");
            return;
        }
        if (2.00 <= grade && grade <= 2.99) {
            System.out.println("Fail");
        } else if (3.00 <= grade && grade <= 3.49) {
            System.out.println("Poor");
        } else if (3.50 <= grade && grade <= 4.49) {
            System.out.println("Good");
        } else if (4.50 <= grade && grade <= 5.49) {
            System.out.println("Very good");
        } else {
            System.out.println("Excellent");
        }
    }

    //3.
    public static void printTriangle(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        //for upper part
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        //for bottom part
        for (int i = n - 1; i >= 1 ; i--) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    //4.
    public static void calculator(Scanner scanner){
        String input = scanner.nextLine();
        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        switch (input){
            case "add":
                System.out.println(num1 + num2);
                break;
            case "multiply":
                System.out.println(num1 * num2);
                break;
            case "devide":
                if (num2 == 0) {
                    return;
                }
                System.out.println(num1 / num2);
                break;
            case "subtract":
                System.out.println(num1 - num2);
                break;
        }
    }

    //5.
    public static void shop(Scanner scanner){
        String product = scanner.nextLine();
        int quantity = Integer.parseInt(scanner.nextLine());

        switch (product){
            case "water":
                System.out.println(quantity * 1.00);
                break;
            case "coffee":
                System.out.println(quantity * 1.50);
                break;
            case "coke":
                System.out.println(quantity * 1.40);
                break;
            case "snacks":
                System.out.println(quantity * 2.00);
                break;
        }
    }

    //7.
    public static void repeatString(Scanner scanner){
        String str = scanner.nextLine();
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= n; i++) {
            stringBuilder.append(str);
        }
        System.out.println(stringBuilder);
    }

    //8.
    public static void mathPower(Scanner scanner){
        double n = Double.parseDouble(scanner.nextLine());
        int power = Integer.parseInt(scanner.nextLine());
        System.out.println(Math.pow(n, power));
    }

    //9.
    public static void greaterOfTwoValues(Scanner scanner){
        String input = scanner.nextLine();
        String val1 = scanner.nextLine();
        String val2 = scanner.nextLine();

        if (getMax(input, val1, val2) == null) {
            System.out.println("Invalid");
        }else{
            System.out.println(getMax(input, val1, val2).toString());
        }

    }
    private static Object getMax(String input, String val1, String val2){
        switch (input){
            case "int":
                return Integer.parseInt(val1) > Integer.parseInt(val2)
                        ? Integer.parseInt(val1) : Integer.parseInt(val2);
            case "char":
                return val1.charAt(0) > val2.charAt(0) ? val1.charAt(0) : val2.charAt(0);
            case "string":
                return val1.charAt(0) > val2.charAt(0) ? val1 : val2;
            default:
                return null;
        }

    }

    //10.
    public static void multiplyEvensByOdds(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        int digitsCount = (int) Math.log10(n) + 1;
        int sumEvenNums = 0;
        int sumOddNums = 0;
        for (int i = 1; i <= digitsCount; i++) {
            int digit = n % 10;
            if (digit % 2 == 0) {
                sumEvenNums += digit;
            }else{
                sumOddNums += digit;
            }
            n /= 10;
        }
        System.out.println(sumEvenNums * sumOddNums);
    }

    //11.
    public static double mathOperations(Scanner scanner) {
        int num1 = Integer.parseInt(scanner.nextLine());
        String operator = scanner.nextLine();
        int num2 = Integer.parseInt(scanner.nextLine());
        switch (operator){
            case "/":
                if (num2 == 0) {
                    return -1;
                }
                return num1 / num2;
            case "*":
                return num1 * num2;
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
        }
        return -1;
    }

    //12.
    public static void passwordValidator(Scanner scanner){
        String password = scanner.nextLine().trim();
        int count = 0;
        boolean containsSymbol = false;
        boolean numberOfDigitsProblem = false;
        boolean containLessDigits = false;
        if (password.toCharArray().length < 6 || password.toCharArray().length > 10) {
            numberOfDigitsProblem = true;
            System.out.println("Password must be between 6 and 10 characters");
        }
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            } else if (!Character.isLetter(c) && !Character.isDigit(c)) {
                containsSymbol = true;
            }
        }
        if (containsSymbol == true) {
            System.out.println("Password must contain only letters and digits");
        }
        if (count < 2) {
            containLessDigits = true;
            System.out.println("Password must have at least 2 digits");
        }
        if (!containLessDigits && !containsSymbol && !numberOfDigitsProblem){
            System.out.println("Password is valid");
        }
    }
}