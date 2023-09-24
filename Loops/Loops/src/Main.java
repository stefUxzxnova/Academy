import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //FromZeroToOneHundred();
        //FromNToZeroReversed(scanner);
        //From1ToNTrough2(scanner);
        //From1ToNTroughM(scanner);
        //LettersInAWord(scanner);
        //System.out.println(SumOfVowels(scanner));
        //Clock();
        //MultiplicationTable();
        //System.out.println(SumOfEvenNumbers(scanner));
        //System.out.println(Factorial(scanner));
        //NumberReversal(scanner);
        //System.out.println(Fibonacci(scanner));
        //PalindromeCheck(scanner);
        //ArmstrongNumber(scanner);
        //CollatzConjecture(scanner);
        //HallowRectanglePattern(scanner);
        //NewBuilding(scanner);
        //MagicNumber(scanner);
        //PadawanEquipment(scanner);
        RageExpenses(scanner);
    }

    //1.
    public static void FromZeroToOneHundred(){
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
        }
    }

    //2.
    public static void FromNToZeroReversed(Scanner scanner){
        int num = scanner.nextInt();
        for (int i = num; i >= 0; i--) {
            System.out.println(i);
        }
    }

    //3.
    public static void From1ToNTrough2(Scanner scanner){
        int num = scanner.nextInt();
        for (int i = 1; i <= num; i += 2) {
            System.out.println(i);
        }
    }

    //4.
    public static void From1ToNTroughM(Scanner scanner){
        int num = Integer.parseInt(scanner.nextLine());
        int gap = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= num; i += gap) {
            System.out.println(i);
        }
    }

    //5.
    public static void LettersInAWord(Scanner scanner){
        String word = scanner.nextLine();
        for (int i = 0; i <= word.toCharArray().length - 1; i ++) {
            System.out.println(word.charAt(i));
        }
    }

    //6.
    public static int SumOfVowels(Scanner scanner){
        String word = scanner.nextLine();
        int sumVowels = 0;
        for (int i = 0; i <= word.toCharArray().length - 1; i ++) {
            char symbol = word.charAt(i);
            switch (symbol){
                case 'a':
                    sumVowels += 1;
                    break;
                case 'e':
                    sumVowels += 2;
                    break;
                case 'i':
                    sumVowels += 3;
                    break;
                case 'o':
                    sumVowels += 4;
                    break;
                case 'u':
                    sumVowels += 5;
                    break;
            }
        }
        return sumVowels;
    }

    //7.
    public static void Clock(){
        for (int i = 0; i <= 23; i ++) {
            for (int j = 0; j <= 59; j++) {
                System.out.println(i + ":" + j);
            }
        }
    }

    //8.
    public static void MultiplicationTable(){
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.println(i + " * " + j + " = " + i*j);
            }
        }
    }

    //9.
    public static int SumOfEvenNumbers(Scanner scanner){
        int num = scanner.nextInt();
        int i = 0;
        int sum = 0;
        while(true){
            i++;
            if (i % 2 == 0){
                sum += i;
                num --;
            }
            if (num == 0){
                break;
            }
        }
        return sum;
    }

    //10.
    public static int Factorial(Scanner scanner){
        int num = Integer.parseInt(scanner.nextLine());
        int factorial = 1;
        for (int i = 1; i <= num; i ++) {
            factorial *= i;
        }
        return factorial;
    }

    //11.
    public static void NumberReversal(Scanner scanner){
        int num = Integer.parseInt(scanner.nextLine());

        while (true){
            System.out.print(num % 10);
            num /= 10;
            if (num < 1){
                break;
            }
        }
    }
    //12.
    public static int Fibonacci(Scanner scanner){
        int num = Integer.parseInt(scanner.nextLine());
        int sum =  0;
        int firstNum = 0;
        int secondNum = 1;
//
        for (int i = 0; i < num; i++) {
            sum += firstNum;
            int temp = firstNum;
            firstNum = secondNum;
            secondNum += temp;
        }
        return sum;
    }

    //13.
    public static void PalindromeCheck(Scanner scanner){
        String word = scanner.nextLine();
        //1 вариант
        boolean isPalindrome = true;
        //проверяваме първия char с последния, втория с предпоследния
        //и така докато стигнем средата на думата
        for (int i = 0; i <= (word.length() - 1) / 2 ; i++) {
            if (word.charAt(i) != word.charAt(((word.length() - 1)) - i)){
                isPalindrome = false;
                break;
            }
        }

        //2 вариант
//        int left = 0;
//        int right = word.length() - 1;
//        while(right > left){
//            if (word.charAt(left) != word.charAt(right)) {
//                isPalindrome = false;
//                break;
//            }
//        }
        System.out.println(isPalindrome);
    }

    //14.
    public static void ArmstrongNumber(Scanner scanner){
        int num = Integer.parseInt(scanner.nextLine());
        int length = (int)(Math.log10(num) + 1);
        double sum = 0;
        int originalNum = num;
        while(num != 0){
            int temp = num % 10;
            sum += Math.pow(temp,length);
            num /= 10;
        }
        if (originalNum == sum){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }

    //15.
    public static void CollatzConjecture(Scanner scanner){
        int num = Math.abs(Integer.parseInt(scanner.nextLine()));
        while(true){
            System.out.print(num + " ");
            if (num % 2 == 0){
                num /= 2;
            }else{
                num = num * 3 + 1;
            }

            if (num == 1){
                System.out.println(num);
                break;
            }
        }
    }

    //16.
    public static void HallowRectanglePattern(Scanner scanner){
        int rows = Math.abs(Integer.parseInt(scanner.nextLine()));
        int columns = Math.abs(Integer.parseInt(scanner.nextLine()));
        //първо минаваме по редовете
        for (int i = 0; i < rows; i++) {
            //обхождаме колоните
            for (int j = 0; j < columns; j++) {
                //първия и последния ред да са изцяло със *
                if (i == 0 || i == (rows - 1)) {
                    System.out.print("*");
                }else{
                    if (j == 0 || j == (columns - 1)){
                        System.out.print("*");
                    }else{
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }

    //17.
    public static void NewBuilding(Scanner scanner){
        int numberOfFloors = Integer.parseInt(scanner.nextLine());
        int numberOfRooms = Integer.parseInt(scanner.nextLine());

        for (int i = numberOfFloors; i > 0 ; i--) {

            for (int j = 0; j < numberOfRooms; j++) {
                if (i == numberOfFloors){
                    System.out.print("L" + i + "" + j + " ");
                    continue;
                }
                if (i % 2 == 0){
                    System.out.print("O" + i + "" + j + " ");
                }else {
                    System.out.print("A" + i + "" + j + " ");
                }
            }
            System.out.println("");

        }

    }

    //18.
    public static void MagicNumber(Scanner scanner) {
        int beginningOfTheInterval = Integer.parseInt(scanner.nextLine());
        int endOfTheInterval = Integer.parseInt(scanner.nextLine());
        int magicNumber = Integer.parseInt(scanner.nextLine());

        String combination = "";
        int count = 0;
        for (int i = beginningOfTheInterval; i <= endOfTheInterval ; i++) {
            for (int j = beginningOfTheInterval; j <= endOfTheInterval; j++) {
                count++;
                int sum = i + j;
                if (sum == magicNumber){
                    combination = "Combination " + count + " - (" + i + " + " + j + " = " + sum + ")";
                    break;
                }
            }
            if (!combination.equals("")){
                break;
            }
        }
        if (!combination.equals("")) {
            System.out.println(combination);
        }else{
            System.out.printf("%d combinations - neither equals %d", count, magicNumber);
        }
    }

    //19.
    public static void PadawanEquipment(Scanner scanner){
        double amountOfMoney = Double.parseDouble(scanner.nextLine());
        int countOfStudents = Integer.parseInt(scanner.nextLine());
        double priceLightsabers = Double.parseDouble(scanner.nextLine());
        double priceRobe = Double.parseDouble(scanner.nextLine());
        double priceBelt = Double.parseDouble(scanner.nextLine());
        int freeBelts = countOfStudents / 6;
        double totalPrice = priceLightsabers * (countOfStudents + Math.ceil(0.1 * countOfStudents)) +
                priceBelt * (countOfStudents - freeBelts) +
                priceRobe * countOfStudents;
        if (totalPrice <= amountOfMoney){
            System.out.printf("Total money is enough - it would cost %.2f lv.", totalPrice);
        }else{
            System.out.printf("George Lukas will need %.2f lv more.", (totalPrice - amountOfMoney));
        }
    }

    //20.
    public static void RageExpenses(Scanner scanner) {
        int countOfLostGames = Integer.parseInt(scanner.nextLine());
        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());
        double totalExpenses = 0;
        int count = 0;
        for (int i = 1; i <= countOfLostGames ; i++) {
            if (i % 2 == 0 && i % 3 == 0) {
                totalExpenses = totalExpenses + mousePrice + headsetPrice + keyboardPrice;
                count++;
            }
            else if (i % 2 == 0){
                totalExpenses += headsetPrice;
            } else if (i % 3 == 0) {
                totalExpenses += mousePrice;
            }

            if (count != 0 && count % 2 == 0) {
                totalExpenses += displayPrice;
                count = 0;
            }
        }
        System.out.printf("Total expenses: %.2f lv.", totalExpenses);
    }
}