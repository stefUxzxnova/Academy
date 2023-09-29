import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Car test
//        int n = Integer.parseInt(scanner.nextLine());
//        List<Car> list = new ArrayList<>();
//        for (int i = 1; i <= n; i++) {
//            String[] array = Arrays.stream(scanner.nextLine()
//                    .trim()
//                    .split(" "))
//                        .toArray(String[]::new);
//            if (array.length == 0) {
//                list.add(new Car());
//            } else if (array.length == 1 ) {
//                list.add(new Car(array[0]));
//            }else{
//                list.add(new Car(array[0], array[1], Integer.parseInt( array[2])));
//            }
//
//        }
//
//        Arrays.stream(list.toArray()).forEach(e -> System.out.println(e.toString()));
//////////////////////////////////////////////////////////////////////////////////////
        //Bank Account test
//        String command = scanner.nextLine();
//        while (!command.equals("End")){
//            String[] array = Arrays.stream(command
//                        .trim()
//                        .split(" "))
//                    .toArray(String[]::new);
//            BankAccount bankAccount;
//            String operation = (String)array[0];
//            switch(operation){
//                case "Create":
//                    bankAccount = new BankAccount();
//                    System.out.printf("Account ID%d created%n", bankAccount.getId());
//                    break;
//                case "Deposit":
//                    bankAccount = BankAccount.getBankAccount(Integer.parseInt(array[1]));
//                    if (bankAccount == null) {
//                        System.out.println("Account does not exist");
//                    }else{
//                        bankAccount.deposit(Integer.parseInt(array[2]));
//                        System.out.println("Deposited " + array[2] + " to ID" + bankAccount.getId());
//                    }
//                    break;
//                case "GetInterest":
//                    bankAccount = BankAccount.getBankAccount(Integer.parseInt(array[1]));
//                    if (bankAccount == null) {
//                        System.out.println("Account does not exist");
//                    }else{
//                        System.out.printf("%.2f%n",bankAccount.getInterest(Integer.parseInt(array[2])));
//                    }
//                    break;
//                case "SetInterest":
//                    BankAccount.setInterestRate(1.5);
//                    break;
//                default:
//                    System.out.println("Invalid command!");
//            }
//            command = scanner.nextLine();
//        }
//////////////////////////////////////////////////////////////////////////////////////
        //Class Vehicle
//        Engine engine = new Engine(100);
//        Vehicle vehicle = new Vehicle("motorbike", "Kawasaki", engine, 200);
//        vehicle.drive(100);
//        System.out.println(vehicle.getFuel());

//////////////////////////////////////////////////////////////////////////////////////
        //Class Storage
//        Product productOne = new Product("cucumber", 1.50, 15);
//        Product productTwo = new Product("tomato", 0.90, 25);
//        Product productThree = new Product("bread", 1.10, 8);
//        Storage storage = new Storage(50);
//        storage.addProduct(productOne);
//        storage.addProduct(productTwo);
//        storage.addProduct(productThree);
//        System.out.println(storage.getProducts());
//        System.out.println(storage.getCapacity());
//        System.out.println(storage.getTotalCost());

        //RandomizeWords
//        String[] words = Arrays.stream(scanner.nextLine().trim().split(" "))
//                .toArray(String[]::new);
//        Random rnd = new Random();
//        for (int i = 0; i < words.length; i++) {
//            int randomIndex = rnd.nextInt(words.length);
//            String temp = words[i];
//            words[i] = words[randomIndex];
//            words[randomIndex] = temp;
//        }
//
//        Arrays.stream(words).forEach(e -> System.out.println(e));

//////////////////////////////////////////////////////////////////////////////////////
    //Class Students
//        List<Student> students = new ArrayList<>();
//        String input = scanner.nextLine();
//        while (!input.equals("end")){
//            String[] array = Arrays.stream(input.trim().split(" "))
//                    .toArray(String[]::new);
//            students.add(new Student(array[0], array[1], Integer.parseInt(array[2]), array[3]));
//            input = scanner.nextLine();
//        }
//        String town = scanner.nextLine();
//        for (var student : students) {
//            if (student.getHometown().equals(town)) {
//                System.out.println(student.toString());
//            }
//        }
//////////////////////////////////////////////////////////////////////////////////////
        //Class Articles
        String[] input = Arrays.stream(scanner.nextLine()
                    .trim()
                    .split("\\s*,\\s*"))
                .toArray(String[]::new);
        Article article = new Article(input[0], input[1], input[2]);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= n; i++) {
            String[] command = Arrays.stream(scanner.nextLine()
                                            .trim()
                                            .split("\\s*:\\s*"))
                                    .toArray(String[]::new);
            switch (command[0]){
                case "Edit":
                    article.editContent(command[1]);
                    break;
                case "ChangeAuthor":
                    article.changeAuthor(command[1]);
                    break;
                case "Rename":
                    article.renameTitle(command[1]);
                    break;
            }
        }
        System.out.println(article.toString());
    }
}