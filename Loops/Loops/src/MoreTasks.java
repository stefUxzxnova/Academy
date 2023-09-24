import java.util.Scanner;

public class MoreTasks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //SumOfOddNumbers(scanner);
        //NumbersUpTo1000EndingWith7(scanner);
        //NumbersUpTo1000EndingWithN(scanner);
        //Encoding(scanner);
        //CoinsAndNotes(scanner);
        //EvenPairs(scanner);
        //PyramidOfNumbers(scanner);
        //UniqueCodes(scanner);
        //SquareOfAsterisks(scanner);
        //HalfRhombusFromAsterisks(scanner);
        //RhombusFromAsterisks(scanner);
        //TreePattern(scanner);
        //SquareFrame(scanner);
        //ChristmasTree(scanner);
        //Sunglasses(scanner);
        //PyramidWithIncreasingDigits(scanner);
        //ArrowPattern(scanner);
        //StaircasePattern(scanner);
        //HourglassPattern(scanner);
        //LeftArrowPattern(scanner);
        //Pyramid(scanner);
        //AlternativeConditions(scanner);
        //EqualSumOfOddAndEvenPositions(scanner);
        //PasswordGenerator(scanner);
        //SpecialNumbers(scanner);
        System.out.println(Change(scanner));
    }

    //21.
    public static void SumOfOddNumbers(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        int i = 1;
//        for (int i = 0; i <= n ; i++) {
//            System.out.println(2 * i + 1);
//            sum += 2 * i;
//        }
        while(n > 0){
            if (i % 2 != 0) {
                sum += i;
                n--;
                System.out.println(i);
            }
            i++;
        }
        System.out.printf("Sum: %d%n", sum);
    }

    //22.
    public static void NumbersUpTo1000EndingWith7(Scanner scanner){
        for (int i = 1; i <= 1000; i++) {
            if (i % 10 == 7){
                System.out.println(i);
            }
        }
    }

    //23.
    public static void NumbersUpTo1000EndingWithN(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= 1000; i++) {
            if (i % 10 == n){
                System.out.println(i);
            }
        }
    }

    //24.
    public static void Encoding(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        int countDigits = (int) (Math.log10(n) + 1);
        for (int i = 0; i < countDigits; i++) {
            int digit = n % 10;
            n /= 10;
            if (digit == 0){
                System.out.print("ZERO");
            }
            for (int j = 0; j < digit; j++) {

                System.out.print((char) (33 + digit));
            }
            System.out.println("");
        }
    }

    //25.
    public static void CoinsAndNotes(Scanner scanner){
        int numberOfCoinsOf1lv = Math.abs(Integer.parseInt(scanner.nextLine()));
        int numberOfCoinsOf2lv = Math.abs(Integer.parseInt(scanner.nextLine()));
        int numberOfBanknotesOf5lv = Math.abs(Integer.parseInt(scanner.nextLine()));
        int sum = Math.abs(Integer.parseInt(scanner.nextLine()));
        //започваме комбинациите с най-голямата банкнота
        int combinationNum = BiggestFrom3Numbers(1,2,5) / 2;
        //за да можем да ги използваме в принтирането на конзолата преди да сме ги декларирали във for-loop
        int j = 0;
        int q = 0;
        //започваме да изброяваме различни комбинации с дадените ни пари и след това проверяваме за наличност
        //първо използваме най-голямата банкнота и най-голямата комбинация с нея, след това допълваме сумата с по-малките монети
        for (int i = combinationNum; i >= 0; i--) {
            if (i * 5 == sum){
                //проверява дали банкнотите от комбинацията са налични
                if (numberOfBanknotesOf5lv >= combinationNum) {
                    System.out.printf("%d * 1 lv. + %d * 2 lv. + %d * 5 lv. = %d lv.%n", q, j, i, sum);
                }
            } else  {
                //изчислваме границата на for-loop, за да може комбинациите да не надвишат дадената сума
                int temp = (sum - (i * 5) )/ 2;
                //пробваме с монетите от 2 лв.
                for (j = temp; j >= 0; j--) {
                    //ако имаме готова комбинация
                    if (i * 5 + j * 2 == sum){
                        //проверява дали банкнотите от комбинацията са налични
                        if (numberOfBanknotesOf5lv >= i && numberOfCoinsOf2lv >= j) {
                            System.out.printf("%d * 1 lv. + %d * 2 lv. + %d * 5 lv. = %d lv.%n", q, j, i, sum);
                        }
                    } else  {
                        //изчислваме границата на for-loop, за да може комбинациите да не надвишат дадената сума
                        int temp1 = (sum - (i * 5 + j * 2) )/ 2;
                        //пробваме с монетите от 1 лв.
                        for (q = temp1; q >= temp1; q--) {
                            if (i * 5 + j * 2 + q * 1 == sum){
                                //проверява дали банкнотите от комбинацията са налични
                                if (numberOfBanknotesOf5lv >= i && numberOfCoinsOf2lv >= j && numberOfCoinsOf1lv >= q) {
                                    System.out.printf("%d * 1 lv. + %d * 2 lv. + %d * 5 lv. = %d lv.%n", q, j, i, sum);
                                }
                            }else  {
                                //изчисляваме колко пари са ни необходими да допълним сумата
                                int temp2 = sum - (i * 5 + j * 2) ;
                                //проверява дали банкнотите от комбинацията са налични
                                if (numberOfBanknotesOf5lv >= i && numberOfCoinsOf2lv >= j && numberOfCoinsOf1lv >= temp2) {
                                    System.out.printf("%d * 1 lv. + %d * 2 lv. + %d * 5 lv. = %d lv.%n", temp2, j, i, sum);
                                }
                            }
                        }
                    }
                }
            }



        }
    }
    //допълнителен метод, който ще можем да преизползваме евентуално,
    //ако са ни дадени други монети и банкноти
    public static int BiggestFrom3Numbers (int a, int b, int c){
        if (a >= b && a >= c ){
            return a;
        } else if (b >= a && b >= c) {
            return b;
        }else{
            return c;
        }
    }

    //26.
    public static void EvenPairs(Scanner scanner){
        int initialValueFirstPair = Math.abs(Integer.parseInt(scanner.nextLine()));
        int initialValueSecondPair = Math.abs(Integer.parseInt(scanner.nextLine()));
        int differenceBetweenInitialAndFinalFirstPair = Math.abs(Integer.parseInt(scanner.nextLine()));
        int differenceBetweenInitialAndFinalSecondPair = Math.abs(Integer.parseInt(scanner.nextLine()));
        //външния for определя цифрите на хилядните и стотиците, т.е. първото двуцифрено число
        for (int i = initialValueFirstPair; i <= (initialValueFirstPair + differenceBetweenInitialAndFinalFirstPair); i++) {
            //външния for определя цифрите на десетиците и единиците, т.е. второто двуцифрено число
            for (int j = initialValueSecondPair; j <= (initialValueSecondPair + differenceBetweenInitialAndFinalSecondPair); j++) {
                //проверяваме двете числа дали са прости
                if (PrimeNumberCheck(i) && PrimeNumberCheck(j)){
                    System.out.println(i * 100 + j);
                }
            }
        }
    }
    //Метод, който проверява дали дадено число е просто
    public static boolean PrimeNumberCheck(int num){
        if (num <= 0 ) {
            return false;
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }

    //27.
    public static int Change(Scanner scanner){
        double amount = Double.parseDouble(scanner.nextLine());
        int count = 0;
        int lvPart = (int)amount;
        int stotinkiPart = (int)(amount * 100) % 100;
        for (int i = 2; i >= 1 ; i--) {
            int temp = lvPart / i;
            count += temp;
            if (lvPart - temp * i == 0 ) {
                break;
            }
            lvPart -= temp * i;
        }
        for (int i = 50; i >= 1 ; i--) {
            if (i != 50 && i != 20 && i != 10 && i != 5 && i != 2 && i != 1) {
                continue;
            }
            int temp = stotinkiPart / i;
            count += temp;
            if (stotinkiPart - temp * i == 0 ) {
                break;
            }
            stotinkiPart -= temp * i;
        }
        return count;
    }


    //28.
    public static void PyramidOfNumbers(Scanner scanner){
        int num = Integer.parseInt(scanner.nextLine());
        //по колко цифри трябва да има на ред
        int count = 1;
        int temp = 0;
        for (int i = 1; i <= num ; i++) {
            //брои колко цифри има на реда
            temp++;
            System.out.print(i + " ");
            //
            if (temp == count){
                System.out.println("");
                count++;
                temp = 0;
            }
        }
    }

    //29.
    public static void UniqueCodes(Scanner scanner){
        int upperLimitFirstNum = Integer.parseInt(scanner.nextLine());
        int upperLimitSecondNum = Integer.parseInt(scanner.nextLine());
        int upperLimitThirdNum = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= upperLimitFirstNum; i++) {
            if (i % 2 != 0){
                continue;
            }
            for (int j = 2; j <= upperLimitSecondNum; j++) {
                if (!PrimeNumberCheck(j)){
                    continue;
                }
                for (int k = 1; k <= upperLimitThirdNum; k++) {
                    if (k % 2 != 0){
                        continue;
                    }
                    System.out.printf("%d %d %d %n", i, j, k);
                }
            }
        }
    }

    //30.
    public static void SquareOfAsterisks(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println("");
        }
    }

    //31.
    public static void HalfRhombusFromAsterisks(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        //отпечатва до средата включително
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        //отпечатва от средата надолу
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println("");
        }
    }

    //32.
    public static void RhombusFromAsterisks(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        //До средата включително - върви по редове
        for (int i = 1; i <= n; i++) {
            //слага интервали
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            //рисува звезди
            for (int k = 1; k <= 2 * i - 1; k++){
                //ако k e нечетно слага звезда, ако не е - " "
                if ( k % 2 != 0){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        //след средата
        for (int i = n - 1; i >= 1 ; i--) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i; j++) {
                if (j % 2 != 0){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    //33.
    public static void TreePattern(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        for (int i = 1; i <= 1 ; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
        }
    }

    //34.
    public static void SquareFrame(Scanner scanner){
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= num ; i++) {

            for (int j = 1; j <= num ; j++) {
                if ((i == 1 || i == num) && (j == 1 || j == num)){
                    System.out.print("+ ");
                } else if ((i != 1 || i != num) && (j == 1 || j == num) ) {
                    System.out.print("| ");
                } else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    //35.
    public static void ChristmasTree(Scanner scanner){
        int n = 0;
        while(n <= 1 || n >= 100){
            n = Integer.parseInt(scanner.nextLine());
        }
        for (int i = 1; i <= n + 1 ; i++) {
            for (int j = 1; j <= (n + 1) - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (2 * i - 1) ; j++) {
                if (j == i) {
                    System.out.print(" | ");
                }else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }

    }

    //36.
    public static void Sunglasses(Scanner scanner) {
        int n = 0;
        //проверяваме дали числото е в интервала
        while (n < 3 || n > 100) {
            n = Integer.parseInt(scanner.nextLine());
        }
        //минаваме по редове (равни на числото n)
        for (int i = 1; i <= n; i++) {
            //минаваме по колони (равни на числото n * 5)
            for (int j = 1; j <= 5 * n ; j++) {
                //проверяваме дали числото е част от рамката на очилата
                if (j <= n * 2 || j > 3 * n) {
                    //проверяваме дали е първа, последна или междинни колони (рамки)
                    if ((i == 1 || i == n) || (j == 1 || j == 5 * n || j == 2 * n || j == 3 * n + 1)) {
                        System.out.print("*");
                    }else{
                        System.out.print("/");
                    }
                }else{
                    //закръгляме към по-високото и там ще ни бъда рамката на очилата
                    if (i == Math.ceil(n / 2.0)) {
                        System.out.print("|");
                    }else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    //38.
    public static void PyramidWithIncreasingDigits(Scanner scanner) {
        int numberOfRows = Integer.parseInt(scanner.nextLine());
        int temp = 0;
        //минава по редове
        for (int i = 1; i <= numberOfRows; i++) {
            //слага интервали
            for (int j = 1; j <= numberOfRows - i; j++) {
                System.out.print(" ");
            }
            //променяме спрямо позицията на цифрата на съответния ред
            temp = i;
            //минава по колони
            for (int j = 1; j <= (i * 2 - 1); j++) {
                //първото число на реда е равно на поредността му (първи ред => 1)
                System.out.print(temp);
                //проверява дали сме стигнали средата на реда
                if ( j > Math.ceil((i * 2 - 1)/2)) {
                    temp--;
                }else {
                    temp ++;
                }
            }
            //добавя нов ред
            System.out.println();
        }
    }

    //39.
    public static void ArrowPattern(Scanner scanner){
        int numberOfRows = Math.abs(Integer.parseInt(scanner.nextLine()));
        for (int i = 1; i <= numberOfRows - 1  ; i++) {
            for (int j = 1; j <= numberOfRows - i - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (i * 2) - 1 ; j++) {
                if ( j % 2 != 0) {
                    System.out.print("*");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        for (int i = 1; i <= numberOfRows - 1 ; i++) {
            for (int j = 1; j <= numberOfRows / 3 ; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (numberOfRows * 2 - 1)/2; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //40.
    public static void StaircasePattern (Scanner scanner){
        int numberOfSteps = Integer.parseInt(scanner.nextLine());
        int temp = 0;
        for (int i = 1; i <= numberOfSteps; i++) {
            //имаме интервал едва след 2 ред
            if (i > 2){
                //с натрупване изчисляваме по колко интервала са ни нужни на ред
                temp += i - 2;
                for (int j = 1; j <= temp; j++) {
                    System.out.print(" ");
                }
            }

            for (int j = 1; j <= i; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }

    //41.
    public static void HourglassPattern (Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        int temp = 0;
        int boundary = 0;
        if (n % 2 == 0) {
            boundary = n + 1;
            temp = 2;
        }else {
            boundary = n + 2;
            temp = 1;
        }
        for (int i = 1; i <= boundary; i++) {
            //първи и последен ред
            if (i == 1 || i == boundary ) {
                for (int j = 1; j <= n + 2; j++) {
                    System.out.print("#");
                }
            //до средата вкл.
            }else if (i <= Math.ceil(boundary/2.0) ) {
                for (int j = 1; j <= i - 1 ; j++) {
                    System.out.print(" ");
                }
                System.out.print("#");

                for (int j = 1; j <= (n+2) - 2 * i ; j++) {
                    System.out.print(" ");
                }
                System.out.print("#");
            }else{
                for (int j = 1; j <= boundary - i ; j++) {
                    System.out.print(" ");
                }
                System.out.print("#");
                for (int j = 1; j <= temp ; j++) {
                    System.out.print(" ");
                }
                temp += 2;
                System.out.print("#");
            }
            System.out.println();
        }
    }

    //42.
    public static void LeftArrowPattern (Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= (n * 2) - 3; i++) {
            if (i <= n - 1) {
                for (int j = 1; j < n - i; j++) {
                    System.out.print(" ");
                }
                for (int j = 1; j <= i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            } else {
                for (int j = 0; j <= i - n; j++) {
                    System.out.print(" ");
                }
                for (int j = 1; j <= (2 * (n-1)) - i ; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }

    //43.
    public static void Pyramid(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
//        int i = 1;
//        //колко цифри са изписани на съответния ред
//        int count = 0;
//        //колко трябва да бъдат изписани
//        int temp = 1;
//        while(i <= n){
//            count++;
//            System.out.print(i);
//            if (temp == count) {
//                System.out.println();
//                count = 0;
//                temp++;
//            }
//            i++;
//        }

        int currentNum = 1;
        for (int j = 1; currentNum <= n; j++) {
            for (int k = 1; k <= j; k++) {
                if (currentNum > n) {
                    break;
                }
                System.out.print(currentNum);
                currentNum++;
            }
            System.out.println();
        }
    }

    //44.
    public static void AlternativeConditions(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        int currentNum = 1;

        //pyramid
        for (int i = 1; currentNum <= n; i++) {
            for (int j = 1; j <= Math.ceil(n/2) - i ; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i ; j++) {
                if (currentNum > n) {
                    break;
                }
                System.out.printf("%d ", currentNum);
                currentNum++;
            }
            System.out.println();
        }
    }

    //45.
    public static void EqualSumOfOddAndEvenPositions (Scanner scanner){
        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        while(num2 < 100000 || num2 > 300000){
            num2 = Integer.parseInt(scanner.nextLine());
        }
        while(num1 < 100000 || num1 > 300000 || num1 > num2){
            num1 = Integer.parseInt(scanner.nextLine());
        }
        int sumEvenNum = 0;
        int sumOddNum = 0;
        //колко числа са се отпечатали
        int count = 0;
        //обхожда всички числа в интервала
        for (int i = num1; i <= num2 ; i++) {
            int temp = i;
            //обхожда отделните цифри на съответното число
            for (int j = 1; j <= 6; j++) {
                if (j % 2 == 0) {
                    sumOddNum += temp % 10;
                }else {
                    sumEvenNum += temp % 10;
                }
                temp /= 10;
            }
            if (sumEvenNum == sumOddNum) {
                System.out.println(i);
                count ++;
            }
            sumOddNum = 0;
            sumEvenNum = 0;
        }
        if (count == 0) {
            System.out.println("None");
        }
    }

    //46.
    public static void PasswordGenerator (Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        int l = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                for (int k = 97; k < 97 + l ; k++) {
                    //ASCII table -> from 97 to 122
                    for (int m = 97; m < 97 + l; m++) {
                        for (int o = 1; o <= n; o++) {
                            if ((o <= i) || (o <= j)) {
                                continue;
                            }
                            System.out.printf("%d%d%c%c%d%n", i, j, (char)k, (char)m, o );
                        }
                    }
                }
            }
        }
    }

    //47.
    public static void SpecialNumbers (Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        boolean isSpecialNum = true;
        for (int i = 1111; i <= 9999 ; i++) {
            int temp = i;
            for (int j = 1; j <= 4; j++) {
                if (temp % 10 == 0 ) {
                    isSpecialNum = false;
                    continue;
                }
                if (n % (temp % 10) != 0) {
                    isSpecialNum = false;
                    break;
                }
                temp /= 10;
            }
            if (isSpecialNum) {
                System.out.println(i);
            }
            isSpecialNum = true;
        }
    }
}


