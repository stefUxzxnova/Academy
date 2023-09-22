import java.util.Scanner;

public class Marks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double mark = Double.parseDouble(sc.nextLine());
        if (mark < 6 && mark > 2){
            if (mark >= 5.50){
                System.out.println("Excellent!");
            }
        }else{
            System.out.println("Invalid data!");
        }

    }
}
