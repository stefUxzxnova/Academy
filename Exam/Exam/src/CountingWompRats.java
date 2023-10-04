import java.util.Scanner;

//        Luke Skywalker is practicing his blaster skills by targeting Womp Rats on Tatooine. Given
//        that he targets n Womp Rats in the first hour and increases his count by m every
//        subsequent hour, determine the total number of Womp Rats he will have targeted after h
//        hours.
public class CountingWompRats {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //count of womp rats
        int count = Integer.parseInt(scanner.nextLine());
        //increase of the number every hour
        int increaseOfCount = Integer.parseInt(scanner.nextLine());
        int numberOfHours = Integer.parseInt(scanner.nextLine());

        int totalCount = count;
        //foreach hour the number of womp rats is increasing
        //we store the number
        for (int i = 2; i <= numberOfHours; i++) {
            count += increaseOfCount;
            totalCount += count;
        }
        System.out.println(totalCount);
    }
}
