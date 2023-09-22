import java.util.Date;
import java.util.Scanner;

public class DifferentTasks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println(LargerNumber(scanner));
        //NumberFromZeroToNine(scanner);
        //System.out.printf("%.2f", FaceOfFigures(scanner));
        //DayOfTheWeek(scanner);
        //EvenOrOdd(scanner);
        //Speed(scanner);
        //AlarmAfter15Minutes(scanner);
        //AddressByAgeAndGender(scanner);
        //System.out.printf("%.2f", Grocery(scanner));
        //NumberInTheRange(scanner);
        //System.out.println(SimpleCalculator(scanner));
        //VegetableMarket(scanner);
        //Holiday(scanner);
        //MakeupShop(scanner);
        //System.out.printf("%.2f", AtSea(scanner));
        //LeapYearChecker(scanner);
        //UniversityAdmissions(scanner);
        //LuggageCharges(scanner);
        //AdventureGame(scanner);
        //Survival(scanner);
        //ClimateZoneIdentifier(scanner);
        EraIdentifier(scanner);
    }

    //2. Larger number
    public static int LargerNumber(Scanner scanner){
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        if (num1 >= num2){
            return num1;
        }else{
            return num2;
        }
    }

    //3. Number from 0 to 9 with words
    public static void NumberFromZeroToNine(Scanner scanner){
        int number = scanner.nextInt();

        if (number > 9){
            System.out.println("too big");

        }else{
            switch (number){
                case 0:
                    System.out.println("Zero");
                    break;
                case 1:
                    System.out.println("One");
                    break;
                case 2:
                    System.out.println("Two");
                    break;
                case 3:
                    System.out.println("Three");
                    break;
                case 4:
                    System.out.println("Four");
                    break;
                case 5:
                    System.out.println("Five");
                    break;
                case 6:
                    System.out.println("Six");
                    break;
                case 7:
                    System.out.println("Seven");
                    break;
                case 8:
                    System.out.println("Eight");
                    break;
                case 9:
                    System.out.println("Nine");
                    break;
                default:
                    System.out.println("Invalid!");

            }
        }
    }

    //4. Face of figures
    public static double FaceOfFigures(Scanner scanner){
        String figure = scanner.nextLine();
        switch (figure){
            case "square":
                double sideSquare = scanner.nextDouble();
                return sideSquare * sideSquare;
            case "rectangle":
                double sideRectangle1 = scanner.nextDouble();
                double sideRectangle2 = scanner.nextDouble();
                return sideRectangle1 * sideRectangle2;
            case "circle":
                double radius = scanner.nextDouble();
                return Math.PI * radius * radius;
            case "triangle":
                double sideTriangle = scanner.nextDouble();
                double height = scanner.nextDouble();
                return sideTriangle * height / 2;
            default:
                return -1;
        }
    }

    //5. Day of the week
    public static void DayOfTheWeek(Scanner scanner){
        int number = scanner.nextInt();
        switch (number){
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Error");
        }
    }

    //6. Even or odd
    public static void EvenOrOdd(Scanner scanner){
        int num = scanner.nextInt();
        if (num%2 == 0){
            System.out.println("Even");
        }else{
            System.out.println("Odd");
        }
    }

    //7. Speed
    public static void Speed(Scanner scanner){
        double speed = Double.parseDouble(scanner.nextLine());
        if(speed <= 10 && speed >= 0){
            System.out.println("slow");
        } else if (speed > 10 && speed <= 60) {
            System.out.println("average");
        } else if (speed > 60 && speed <= 120) {
            System.out.println("fast");
        } else if (speed > 120 && speed <= 160) {
            System.out.println("super-fast");
        }else{
            System.out.println("turbo-fast");
        }

    }

    //8. Alarm after 15 minutes
    public static void AlarmAfter15Minutes(Scanner scanner){
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt() + 15;
        if (minutes >= 60){
            minutes -= 60;
            if(hours == 23){
                hours = 0;
            }else{
                hours++;
            }
        }

        if (minutes <= 9 && minutes >=0){
            System.out.printf(hours + ":" + "%02d", minutes);
        }else{
            System.out.println(hours + ":" + minutes);
        }
    }

    //9. AddressByAgeAndGender
    public static void AddressByAgeAndGender(Scanner scanner){
        double age = Double.parseDouble(scanner.nextLine());
        String gender = scanner.nextLine();
        switch (gender){
            case "m":
                if (age >= 16){
                    System.out.println("Mr.");
                }else{
                    System.out.println("Master");
                }
                break;
            case "f":
                if (age >= 16){
                    System.out.println("Ms.");
                }else{
                    System.out.println("Miss");
                }
                break;
        }

    }

    //10. Grocery
    public static double Grocery(Scanner scanner){
        String product = scanner.nextLine();
        String city = scanner.nextLine();
        int quantity = Integer.parseInt(scanner.nextLine());
        double totalSum = 0;
        switch (city){
            case "Varna":
                switch (product){
                    case "coffee":
                        totalSum = quantity * 0.45;
                        break;
                    case "water":
                        totalSum = quantity * 0.70;
                        break;
                    case "juice":
                        totalSum = quantity * 1.10;
                        break;
                    case "sweets":
                        totalSum = quantity * 1.35;
                        break;
                    case "chips":
                        totalSum = quantity * 1.55;
                        break;
                }
                break;
            case "Sofia":
                switch (product){
                    case "coffee":
                        totalSum = quantity * 0.50;
                        break;
                    case "water":
                        totalSum = quantity * 0.80;
                        break;
                    case "juice":
                        totalSum = quantity * 1.20;
                        break;
                    case "sweets":
                        totalSum = quantity * 1.45;
                        break;
                    case "chips":
                        totalSum = quantity * 1.60;
                        break;
                    default:
                        return -1;
                }
                break;
            case "Plovdiv":
                switch (product){
                    case "coffee":
                        totalSum = quantity * 0.40;
                        break;
                    case "water":
                        totalSum = quantity * 0.70;
                        break;
                    case "juice":
                        totalSum = quantity * 1.15;
                        break;
                    case "sweets":
                        totalSum = quantity * 1.30;
                        break;
                    case "chips":
                        totalSum = quantity * 1.50;
                        break;
                }
                break;
            default:
                return -1;
        }

        return totalSum;
    }

    //11. Number in the range
    public static void NumberInTheRange(Scanner scanner){
        int num = scanner.nextInt();
        if (num >= -100 && num <= 100 && num != 0){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }

    //12. Simple Calculator
    public static double SimpleCalculator(Scanner scanner){
        double num1 = Double.parseDouble(scanner.nextLine());
        double num2 = Double.parseDouble(scanner.nextLine());
        String operation = scanner.nextLine();
        switch (operation){
            case "add":
                return num1 + num2;
            case "subtract":
                return num1 - num2;
            case "divide":
                if (num2 == 0){
                    return -1;
                }
                return num1 / num2;
            case "multiply":
                return num1 * num2;
            default:
                return -1;
        }
    }

    //13. Vegetable Market
    public static void VegetableMarket(Scanner scanner){
        String vegetable = scanner.nextLine();
        String dayOfTheWeek = scanner.nextLine();
        int quantity = Integer.parseInt(scanner.nextLine());

        switch (dayOfTheWeek){
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                switch (vegetable){
                    case "tomato":
                        System.out.printf("%.2f", quantity * 2.50);
                        break;
                    case "onion":
                        System.out.printf("%.2f", quantity * 1.20);
                        break;
                    case "lettuce":
                        System.out.printf("%.2f", quantity * 0.85);
                        break;
                    case "cucumber":
                        System.out.printf("%.2f",quantity * 1.45);
                        break;
                    case "pepper":
                        System.out.printf("%.2f", quantity * 5.50);
                        break;
                    default:
                        System.out.println("error");
                }
                break;
            case "Saturday":
            case "Sunday":
                switch (vegetable){
                    case "tomato":
                        System.out.printf("%.2f", quantity * 2.80);
                        break;
                    case "onion":
                        System.out.printf("%.2f", quantity * 1.30);
                        break;
                    case "lettuce":
                        System.out.printf("%.2f", quantity * 0.85);
                        break;
                    case "cucumber":
                        System.out.printf("%.2f", quantity * 1.75);
                        break;
                    case "pepper":
                        System.out.printf("%.2f", quantity * 3.50);
                        break;
                    default:
                        System.out.println("error");
                }
                break;
            default:
                System.out.println("error");
        }
    }

    //14. Holiday
    public static void Holiday(Scanner scanner){
        double budget = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();
        double costs = 0;
        if (budget <= 100){
            switch (season){
                case "Summer":
                    costs = 0.3 * budget;
                    System.out.println("Somewhere in Bulgaria");
                    System.out.printf("Camp - %.2f", costs);
                    break;
                case "Winter":
                    costs = 0.7 * budget;
                    System.out.println("Somewhere in Bulgaria");
                    System.out.printf("Hotel - %.2f", costs);
                    break;
            }

        } else if (budget <= 1000) {
            switch (season){
                case "Summer":
                    costs = 0.4 * budget;
                    System.out.println("Somewhere in Europe");
                    System.out.printf("Camp - %.2f", costs);
                    break;
                case "Winter":
                    costs = 0.8 * budget;
                    System.out.println("Somewhere in Europe");
                    System.out.printf("Hotel - %.2f", costs);
                    break;
            }
        } else {
            costs = 0.9 * budget;
            System.out.println("Somewhere in Asia");
            System.out.printf("Hotel - %.2f", costs);
        }
    }

    //15. Makeup Shop
    public static void MakeupShop(Scanner scanner){
        double priceOfRenovation = Double.parseDouble(scanner.nextLine());
        int numberOfPowders = Integer.parseInt(scanner.nextLine());
        int numberOfLipSticks = Integer.parseInt(scanner.nextLine());
        int numberOfSpirals = Integer.parseInt(scanner.nextLine());
        int numberOfShadows = Integer.parseInt(scanner.nextLine());
        int numberOfConcealer = Integer.parseInt(scanner.nextLine());

        double totalPriceOrder = (numberOfPowders * 2.60) + (numberOfLipSticks * 3) +
                (numberOfSpirals * 4.10) + (numberOfShadows * 8.20) + (numberOfConcealer * 2);
        if (numberOfPowders + numberOfLipSticks + numberOfSpirals + numberOfShadows + numberOfConcealer >= 50){
            totalPriceOrder -= 0.25 * totalPriceOrder;
        }
        double rent = 0.1 * totalPriceOrder;
        if (totalPriceOrder - rent >= priceOfRenovation){
            System.out.printf("Yes! %.2f lv left.", totalPriceOrder - rent - priceOfRenovation);
        }else{
            System.out.printf("Not enough money! %.2f lv needed.", priceOfRenovation - (totalPriceOrder - rent));
        }
    }

    //16. At Sea
    public static double AtSea(Scanner scanner){
        int daysToStay = Integer.parseInt(scanner.nextLine());
        String typeOfRoom = scanner.nextLine();
        String assessment = scanner.nextLine();

        double totalPrice = 0;
        switch (typeOfRoom){
            case "single room":
                totalPrice = (daysToStay - 1) * 25;
                break;
            case "apartment":
                totalPrice = (daysToStay - 1) * 50;
                if (daysToStay < 10){
                    totalPrice -= totalPrice * 0.30;
                } else if (daysToStay >= 10 && daysToStay <= 15) {
                    totalPrice -= totalPrice * 0.35;
                }else{
                    totalPrice -= totalPrice * 0.50;
                }
                break;
            case "presidential":
                totalPrice = (daysToStay - 1) * 100;
                if (daysToStay < 10){
                    totalPrice -= totalPrice * 0.10;
                } else if (daysToStay >= 10 && daysToStay <= 15) {
                    totalPrice -= totalPrice * 0.15;
                }else{
                    totalPrice -= totalPrice * 0.20;
                }
                break;
            default:
                return -1;
        }
        if (assessment.equals("positive")){
            totalPrice += 0.25 * totalPrice;
        }else{
            totalPrice -= 0.1 * totalPrice;
        }
        return totalPrice;
    }

    //17.Grade Calculator
    public static void GradeCalculator(Scanner scanner){
        int percentage = scanner.nextInt();
        if (percentage >= 90 && percentage <= 100){
            System.out.println("A");
        } else if (percentage >= 80 && percentage <= 89) {
            System.out.println("B");
        } else if (percentage >= 70 && percentage <= 79) {
            System.out.println("C");
        } else if (percentage >= 60 && percentage <= 69) {
            System.out.println("D");
        } else if (percentage >= 0 && percentage <= 59) {
            System.out.println("F");
        }else{
            System.out.println("Invalid percentage!");
        }
    }

    //18. Leap Year Checker
    public static void LeapYearChecker(Scanner scanner){
        int yearToCheck = scanner.nextInt();
        //приемаме, че дадената година НЕ е високосна
        boolean isLeap = false;
        if (yearToCheck % 4 == 0 ){
            isLeap = true;
            if (yearToCheck % 100 == 0){
                isLeap = false;
                if (yearToCheck % 400 == 0){
                    isLeap = true;
                }
            }
        }

        if (isLeap){
            System.out.println("It's a leap year!");
        }else{
            System.out.println("It's not a leap year!");
        }
    }

    //19.Movie ticket Price
    public static void MovieTicketPrice(Scanner scanner){
        int age = scanner.nextInt();
        if (age >= 0 && age <= 12){
            System.out.println("$5");
        } else if (age >= 13 && age <= 19) {
            System.out.println("$8");
        } else {
            System.out.println("$10");
        }
    }

    //20.Days in a Month
    public static int DaysInAMonth(Scanner scanner){
        int monthNumber = scanner.nextInt();
        switch (monthNumber){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return -1;
        }
    }

    //21.University Admissions
    public static void UniversityAdmissions(Scanner scanner){
        int score = Integer.parseInt(scanner.nextLine());
        int extracurriculars = Integer.parseInt(scanner.nextLine());
        boolean isAdmitted = false;
        if (score >= 90){
            isAdmitted = true;
        } else if (score >= 80 && score <= 89) {
            if (extracurriculars >= 2){
                isAdmitted = true;
            }
        }
        if (isAdmitted){
            System.out.println("Admitted");
        }else{
            System.out.println("Not admitted");
        }
    }

    //22.Discount Calculator
    public static void DiscountCalculator(){
        Scanner scanner = new Scanner(System.in);
        int age = Integer.parseInt(scanner.nextLine());
        String withMembershipCard = scanner.nextLine();

        if(age < 18){
            System.out.println("10% discount");
        } else if (age >= 18 && age <= 64) {
            if (withMembershipCard.equals("Yes")){
                System.out.println("20% discount");
            } else if (withMembershipCard.equals("No")) {
                System.out.println("10% discount");
            } else{
                System.out.println("Error");
            }
        }else{
            System.out.println("30% discount.");
        }
    }

    //24. Airline Luggage Charges
    public static void LuggageCharges(Scanner scanner){
        int weight = Integer.parseInt(scanner.nextLine());
        int dimensions = Integer.parseInt(scanner.nextLine());
        int fees = 0;
        boolean isOverweight = false;
        boolean isOverSized = false;
        if (weight > 50){
            fees += 100;
            isOverweight = true;
        }

        if (dimensions > 158){
            isOverSized = true;
            if (dimensions - 158 >= 1 && dimensions - 158 <= 20 ){
                fees += 50;
            } else if (dimensions - 158 >= 21 && dimensions - 158 <= 50) {
                fees += 100;
            }else{
                fees += 200;
            }
        }

        if (isOverweight && isOverSized){
            fees += 50;
        }
        System.out.println("$" + fees);
    }

    //25.Adventure Game: Path Decision
    public static void AdventureGame(Scanner scanner){
        String tool1 = scanner.nextLine();
        String tool2 = scanner.nextLine();

        if (tool1.equals("sword")){
            if (tool2.equals("shield")){
                System.out.println("Path to the castle");
            }else{
                System.out.println("Path to the forest");
            }
        } else if (tool1.equals("map")) {
            if (tool2.equals("coins")){
                System.out.println("Go to the town");
            }else{
                System.out.println("Camp at the current spot and prepare for the next day");
            }
        }else{
            System.out.println("Wander aimlessly");
        }
    }

    //26.Potion Brewing Decision
    public static void PotionBrewing(Scanner scanner){
        String ingredient1 = scanner.nextLine();
        String ingredient2 = scanner.nextLine();

        if (ingredient1.equals("herbs")){
            if (ingredient2.equals("water")){
                System.out.println("Health potion");
            } else if (ingredient2.equals("oil")) {
                System.out.println("Stealth potion");
            }
            else{
                System.out.println("Minor stamina potion");
            }
        } else if (ingredient1.equals("berries")) {
            if(ingredient2.equals("sugar")){
                System.out.println("Speed potion");
            }else{
                System.out.println("Minor energy potion");
            }
        }else{
            System.out.println("Can't brew any potion");
        }
    }

    //27. Survival in the Wildness
    public static void Survival(Scanner scanner){
        String timeOfTheDay = scanner.nextLine();
        String enviroment = scanner.nextLine();
        String item = scanner.nextLine();

        switch (timeOfTheDay){
            case "day":
                switch (enviroment){
                    case "forest":
                        if (item.equals("knife")){
                            System.out.println("Hunt for food");
                        } else if (item.equals("container")) {
                            System.out.println("Collect berries");
                        }else{
                            System.out.println("Explore");
                        }
                        break;
                    case "dessert":
                        if (item.equals("hat")){
                            System.out.println("Search for water");
                        }else{
                            System.out.println("Find shade");
                        }
                        break;
                }
                break;
            case "night":
                switch (enviroment){
                    case "forest":
                        if (item.equals("firestarter")){
                            System.out.println("Make a campfire");
                        }else{
                            System.out.println("Climb a tree for safety");
                        }
                        break;
                    case "dessert":
                        if (item.equals("blanket")){
                            System.out.println("Sleep");
                        }else{
                            System.out.println("Keep moving to stay warm");
                        }
                        break;
                }
                break;
        }
    }

    //28. Climate Zone Identifier
    public static void ClimateZoneIdentifier(Scanner scanner){
        double latitudeValue = Double.parseDouble(scanner.nextLine());
        String hemisphere = scanner.nextLine();
        if (Math.abs(latitudeValue) >= 66.5){
            System.out.println("Arctic Zone");
        } else if (Math.abs(latitudeValue) >= 23.5 && Math.abs(latitudeValue) <= 66.5) {
            System.out.println("Temperate Zone");
        } else if (Math.abs(latitudeValue) > 0 && Math.abs(latitudeValue) <= 23.5){
            System.out.println("Tropic Zone");
        } else if (latitudeValue == 0){
            System.out.println("Equator");
        }
    }

    //29. Architectural Era Identifier
    public static void EraIdentifier(Scanner scanner){
        int year = Integer.parseInt(scanner.nextLine());
        String material = scanner.nextLine();
        if (year < 500 &&  material.equals("stone")){
            System.out.println("Ancient");
        } else if (year >= 500 && year <= 1500 && material.equals("stone")) {
            System.out.println("Medieval");
        } else if (year >= 1500 && year <= 1800 && material.equals("wood")) {
            System.out.println("Colonial");
        } else if (year >= 1800 && year <= 1900 && material.equals("steel")) {
            System.out.println("Industrial");
        } else if (year > 1900 && material.equals("steel")) {
            System.out.println("Modern");
        }else{
            System.out.println("Uncertain");
        }
    }
}
