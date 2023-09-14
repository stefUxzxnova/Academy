import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //readDifferentData(scanner);
        //WeatherForecast(scanner);
        //System.out.println(ConvertKilometersToMiles(scanner));
        //Calculate(scanner);
        //System.out.println(ConvertMinutesToHours(scanner));
        //System.out.printf("%.6f", CalculateSpeedPerSecond(scanner));
        //CalculatePaint(scanner);
        //OfficeCalculation(scanner);
        //System.out.println(JourneyCalculation(scanner));
        //System.out.printf("%.2f", CalculateBodyMassIndex(scanner));
        //System.out.println(CalculateDailyWaterConsumption(scanner));
        //System.out.println(CalculateElectricityBill(scanner));
        //System.out.println(CalculateAverageSpeed(scanner));
        //System.out.println(CalculateFuelEfficiency(scanner));
        System.out.printf("%.2f", CalculateCircleCircumference(scanner));

    }

    //5. Read different data
    public static void readDifferentData(Scanner scanner){
        String name = scanner.nextLine();
        char symbol = scanner.nextLine().charAt(0);
        int num = scanner.nextInt();
        double realNum = scanner.nextDouble();

        System.out.printf(name + " " + symbol + " " + num + " " + realNum);
    }

    //6. Weather Forecast
    public static void WeatherForecast(Scanner scanner){
        String city = scanner.nextLine();
        int degrees = scanner.nextInt();

        System.out.printf("Today in %s it is %d degrees.", city, degrees);
    }

    //7. Kilometers to Miles
    public static double ConvertKilometersToMiles(Scanner scanner){
        double km = scanner.nextDouble();
        double miles = km * 0.621371192;
        return miles;
    }

    //9. Calculate
    public static void Calculate(Scanner scanner){
        int numOne = scanner.nextInt();
        int numTwo = scanner.nextInt();

        System.out.println("The sum is: " + (numOne + numTwo));
        System.out.println("The difference is: " + (numOne - numTwo));
        System.out.println("The product is: " + (numOne * numTwo));
        System.out.println("The average is: " + ((numOne + numTwo)/2.0));
    }

    //10. Time
    public static String ConvertMinutesToHours(Scanner scanner){
        int minutes = scanner.nextInt();
        int hours = minutes / 60;
        int totalMinutes = minutes % 60;
        if (hours <= 9){
            if (totalMinutes <= 9){
                return "0" + hours + ":" + "0"+ totalMinutes;
            }
            return "0" + hours + ":" + totalMinutes;
        }
        return hours + ":" + totalMinutes;
    }

    //11. Speed
    public static double CalculateSpeedPerSecond(Scanner scanner){
        int distanceInMeters = scanner.nextInt();
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        int seconds = scanner.nextInt();

        double totalTime = hours * 3600 + minutes * 60 + seconds;
        return distanceInMeters / totalTime;
    }

    //12. Painting
    public static void CalculatePaint(Scanner scanner){
        double totalPaintConsumed = scanner.nextDouble();
        double yellowPaint = 4 * totalPaintConsumed / 13;
        double redPaint = yellowPaint / 4;
        double whitePaint = 2 * yellowPaint;

        System.out.printf("Yellow paint used: %.4f kg%n", yellowPaint);
        System.out.printf("Red paint used: %.4f kg%n", redPaint);
        System.out.printf("White paint used: %.4f kg%n", whitePaint);
    }

    //13. Office
    public static void OfficeCalculation(Scanner scanner){
        double firstCabinetCost = scanner.nextDouble();
        double secondCabinetCost = firstCabinetCost - (firstCabinetCost * 0.2);
        double thirdCabinetCost = 0.15 * (firstCabinetCost + secondCabinetCost) + firstCabinetCost + secondCabinetCost;
        double totalCost = firstCabinetCost + secondCabinetCost + thirdCabinetCost;
        System.out.printf("%.3f", totalCost);
    }

    //14. Journey
    public static int JourneyCalculation(Scanner scanner){
        int firstCarSpeed = scanner.nextInt();
        int secondCarSpeed = scanner.nextInt();
        int firstCarDistance = firstCarSpeed * 5;
        int secondCarDistance = secondCarSpeed * 3;
        return (firstCarDistance - secondCarDistance);
    }

    //15. Body Mass Index
    public static double CalculateBodyMassIndex(Scanner scanner){
        int weight = scanner.nextInt();
        double height = scanner.nextDouble();

        double bodyMassIndex =  weight/ Math.pow(height,2) ;
        return bodyMassIndex;
    }

    //16. Water Consumption
    public static double CalculateDailyWaterConsumption(Scanner scanner){
        int totalWeeklyWaterConsumption = scanner.nextInt();
        int numberOfPeople = scanner.nextInt();

        double dailyConsumptionPerPerson =  totalWeeklyWaterConsumption/ 7 / numberOfPeople ;
        return dailyConsumptionPerPerson;
    }

    //17. Electricity Bill
    public static double CalculateElectricityBill(Scanner scanner){
        int unitsConsumed = scanner.nextInt();
        double ratePerUnit = scanner.nextDouble();

        double totalBill =  (unitsConsumed * ratePerUnit) + 10;
        return totalBill;
    }

    //18. Average Speed
    public static double CalculateAverageSpeed(Scanner scanner){
        int distanceInKm = scanner.nextInt();
        int timeInHours = scanner.nextInt();

        double speed = distanceInKm / timeInHours;
        return speed;
    }

    //19. Fuel Efficiency
    public static double CalculateFuelEfficiency(Scanner scanner){
        int distanceInKm = scanner.nextInt();
        double fuelInLitres = scanner.nextInt();

        double fuelEfficiency = distanceInKm / fuelInLitres;
        return fuelEfficiency;
    }

    //20. Circle Circumference
    public static double CalculateCircleCircumference(Scanner scanner){
        int radius = scanner.nextInt();
        double circumference = 2 * Math.PI * radius;

        return circumference;
    }
}