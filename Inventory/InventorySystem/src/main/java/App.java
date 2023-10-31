import com.inventory.system.entities.enums.ProductCategory;
import com.inventory.system.entities.enums.Roles;
import com.inventory.system.entities.items.ElectronicsItem;
import com.inventory.system.entities.items.FragileItem;
import com.inventory.system.entities.items.GroceryItem;
import com.inventory.system.entities.items.InventoryItem;
import com.inventory.system.entities.user.User;
import com.inventory.system.entities.user.UserCredentials;
import com.inventory.system.services.InventoryManager;
import com.inventory.system.services.UsersManager;
import com.inventory.system.utils.DirectoryManager;

import javax.management.relation.Role;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        //PaymentProcessor paymentProcessor = new PaymentProcessor();
        User user = new User(
                "Ivan",
                new UserCredentials("ivannn", "A1234456"),
                "jadsfhjfs@abv.bg",
                "slnlas",
                Roles.ADMIN.getDisplayName());
        UsersManager usersManager = new UsersManager();
        usersManager.add(user);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the E-commerce Console Application!");
        displayMenu();
        boolean isRunning = true;
        long loggedUserId = -1;
        while (isRunning) {
            loggedUserId = enterTheSystem(scanner, usersManager);
            //if we don't have a logged user, we will skip the logic below
            if (loggedUserId == -1) {
                continue;
            }
            while(true){
                roleDependingOptions(loggedUserId, usersManager, inventoryManager);
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        break;
                    case 2:
                        displayAllItems(inventoryManager);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        createInventory(scanner, inventoryManager);
                        break;
                    case 6:
                        createItem(scanner, inventoryManager);
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        changeInventory(scanner, inventoryManager);
                        break;
                    default:
                        break;

                }
            }

        }

}

    private static void displayMenu() {
        System.out.println("1. Log In");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    private static long enterTheSystem(Scanner scanner, UsersManager usersManager){
        System.out.print("Enter your choice (1/2/3): ");
        int choice = scanner.nextInt();
        User user = null;
        scanner.nextLine();
        switch (choice) {
            case 1:
                // Log In
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();
                // User authentication
                user = usersManager.getLoggedUser(username, password);
                if (user != null) {
                    System.out.println("Logged in as " + username);
                    return user.getId();
                }
                System.out.println("Wrong username or password!");
                break;

            case 2:
                // Register
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter username: ");
                String newUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String newPassword = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter phone: ");
                String address = scanner.nextLine();
                // Implement the logic for user registration
                user = usersManager.createUser(name, newUsername, newPassword, address, email, Roles.REGULARUSER.getDisplayName());
                if (user != null ) {
                    System.out.println("Registered a new user: " + newUsername);
                    return user.getId();
                }
                System.out.println("Problem occurred!");
                break;

            case 3:
                // Exit
                System.out.println("Goodbye!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                break;
        }
        return -1;
    }

    private static void roleDependingOptions(long loggedUserId, UsersManager usersManager, InventoryManager inventoryManager){
        User loggedUser = usersManager.getById(loggedUserId);
        System.out.println("1. Make Order");
        System.out.println("2. Display items in " + inventoryManager.getCurrentInventoryName());
        System.out.println("3. Sort items in " + inventoryManager.getCurrentInventoryName());
        System.out.println("4. Get items by category in " + inventoryManager.getCurrentInventoryName());
        if (loggedUser.getRole().equals(Roles.ADMIN.getDisplayName())) {
            // Display admin options
            System.out.println("5. Create Inventory");
            System.out.println("6. Add item to " + inventoryManager.getCurrentInventoryName());
            System.out.println("7. Update item in " + inventoryManager.getCurrentInventoryName());
            System.out.println("8. Delete item in " + inventoryManager.getCurrentInventoryName());
            System.out.println("9. Change inventory. You are now in " + inventoryManager.getCurrentInventoryName());
        }
        System.out.println("0. Exit");
    }


    private static void createInventory(Scanner scanner, InventoryManager inventoryManager){
        System.out.println("Enter inventory name:");
        String name = scanner.nextLine();
        inventoryManager = new InventoryManager(name);
        System.out.println("You are now in the new inventory");
    }

    private static void changeInventory(Scanner scanner, InventoryManager inventoryManager){
        List<String> inventories = DirectoryManager.listAllFileNamesInDirectory("inventories.directory");
        System.out.println("Enter inventory name:");
        printList(inventories);
        String name = scanner.nextLine();

        if (inventoryManager.getCurrentInventoryName().equals(name)) {
            System.out.println("You are already in " + inventoryManager.getCurrentInventoryName());
        }else {
            if (!inventories.contains(name)) {
                System.out.println("Wrong inventory name");
            }else {
                inventoryManager = new InventoryManager(name);
                System.out.println("You are now in " + inventoryManager.getCurrentInventoryName());
            }
        }
    }

    private static void displayAllItems(InventoryManager inventoryManager){
        List<InventoryItem> list = inventoryManager.getAllItems();
        for (InventoryItem item : list) {
            item.displayItemDetails();
        }
    }

    private static void printList(List<String> list){
        for (String item : list) {
            System.out.println(item);
        }
    }

    private static void createItem(Scanner scanner, InventoryManager inventoryManager){
        System.out.println("1. Grocery");
        System.out.println("2. Electronics");
        System.out.println("3. Fragile");
        System.out.print("Enter your choice (1/2/3): ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Choose categories: ");
        for (ProductCategory category : ProductCategory.values()) {
            System.out.println(category.getCode() + ". " + category.getDisplayName());
        }
        // Ask the user to choose categories by numbers
        System.out.print("Enter the numbers of your chosen categories separated by commas (e.g., 1,3,5): ");
        String input = scanner.nextLine();
        // Parse the user's input and collect selected categories
        String[] choiceTokens = input.split(",");
        List<String> selectedCategories = new ArrayList<>();

        for (String choiceToken : choiceTokens) {
            int userChoice = Integer.parseInt(choiceToken);
            String selectedCategory = ProductCategory.getCategoryByCode(userChoice).getDisplayName();
            selectedCategories.add(selectedCategory);
        }

        switch (choice) {
            case 1:
                // Create a GroceryItem
                System.out.print("Enter expiration date (YYYY-MM-DD): ");
                String expirationDateInput = scanner.nextLine();
                // Parse the date (assuming a specific date format)
                LocalDate expirationDate = LocalDate.parse(expirationDateInput);
                if (inventoryManager.addItem(new GroceryItem(name, description, quantity, price, selectedCategories, expirationDate))) {
                    System.out.println("Created Grocery Item");
                }
                break;
            case 2:
                // Create an ElectronicsItem instance
                if (inventoryManager.addItem(new ElectronicsItem(name, description, quantity, price, selectedCategories))) {
                    System.out.println("Created Electronics Item");
                }
                break;
            case 3:
                // Create a FragileItem
                System.out.print("Enter weight: ");
                double weight = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character

                // Create the FragileItem instance
                if (inventoryManager.addItem(new FragileItem(name, description, quantity, price, selectedCategories, weight))) {
                    System.out.println("Created Fragile Item");
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }



}
