import com.ecommerce.system.processors.PaymentProcessor;
import com.inventory.system.entities.enums.OrderStatus;
import com.inventory.system.entities.enums.ProductCategory;
import com.inventory.system.entities.enums.Roles;
import com.inventory.system.entities.items.ElectronicsItem;
import com.inventory.system.entities.items.FragileItem;
import com.inventory.system.entities.items.GroceryItem;
import com.inventory.system.entities.items.InventoryItem;
import com.inventory.system.entities.order.Order;
import com.inventory.system.entities.user.User;
import com.inventory.system.entities.user.UserCredentials;
import com.inventory.system.services.InventoryManager;
import com.inventory.system.services.OrdersManager;
import com.inventory.system.services.UsersManager;
import com.inventory.system.services.enums.SortCriteria;
import com.inventory.system.utils.DirectoryManager;

import java.time.LocalDate;
import java.util.*;

public class App {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        OrdersManager ordersManager = new OrdersManager();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
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
                        makeOrder(scanner, inventoryManager, ordersManager, paymentProcessor, loggedUserId);
                        break;
                    case 2:
                        displayAllItems(inventoryManager);
                        break;
                    case 3:
                        displayItemsSorted(scanner, inventoryManager);
                        break;
                    case 4:
                        getItemsByCategory(scanner, inventoryManager);
                        break;
                    case 5:
                        createInventory(scanner, inventoryManager);
                        break;
                    case 6:
                        createItem(scanner, inventoryManager);
                        break;
                    case 7:
                        updateInventoryItem(scanner, inventoryManager);
                        break;
                    case 8:
                        deleteInventoryItem(scanner, inventoryManager);
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
        System.out.println("4. Get items by category from " + inventoryManager.getCurrentInventoryName());
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
            System.out.println();
        }
    }

    private static <T> void printList(List<T> list){
        for (T item : list) {
            System.out.println(item.toString());
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

    private static void makeOrder(Scanner scanner, InventoryManager inventoryManager, OrdersManager ordersManager, PaymentProcessor paymentProcessor, long loggedUserId){
        Map<InventoryItem, Integer> orderItems = new HashMap<>();
        System.out.println("Available Items:");
        displayAllItems(inventoryManager);

        while (true) {
            System.out.print("Enter the ID of the item you want to order (or 'end' to finish): ");
            String input = scanner.nextLine().toLowerCase();
            if ("end".equalsIgnoreCase(input)) {
                break;
            }
            try {
                int itemId = Integer.parseInt(input);
                InventoryItem selectedItem = inventoryManager.getById(itemId);
                if (selectedItem != null) {
                    System.out.print("Enter the quantity you want to order: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    if (checkQuantity(selectedItem, quantity)) {
                        orderItems.put(selectedItem, quantity);
                    }else{
                        System.out.println("Invalid quantity");
                    }
                } else {
                    System.out.println("Invalid item ID. Please choose a valid item.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid item ID or 'end' to finish.");
            }
        }
        if (!orderItems.isEmpty()) {
            checkOut(scanner, orderItems, ordersManager, paymentProcessor, inventoryManager, loggedUserId);
        }
    }

    private static void checkOut(Scanner scanner,
                                 Map<InventoryItem, Integer> orderItems,
                                 OrdersManager ordersManager,
                                 PaymentProcessor paymentProcessor,
                                 InventoryManager inventoryManager,
                                 long loggedUserId) {
        double orderTotalPrice = ordersManager.calculateOrderTotalPrice(orderItems);
        while (true) {
            System.out.println("Do you want to proceed to checkout?");
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "yes" -> {
                    if (paymentProcess(scanner, paymentProcessor, orderTotalPrice)) {
                        ordersManager.create(loggedUserId, orderItems, OrderStatus.Done.getDisplayName(), inventoryManager);
                    }
                    return;
                }
                case "no" -> {
                    ordersManager.create(loggedUserId, orderItems, OrderStatus.Pending.getDisplayName(), inventoryManager);
                    System.out.println("You can pay later");
                    return;
                }
                default -> System.out.println("Yes or no?");
            }
        }
    }

    private static boolean paymentProcess(Scanner scanner, PaymentProcessor paymentProcessor, double orderTotalPrice) {
        System.out.println("Payment Options:");
        System.out.println("1. Credit Card Payment");
        System.out.println("2. PayPal Payment");
        System.out.print("Select a payment option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            // Credit Card Payment
            String cardHolderName;
            String cardNumber;
            int cvv;
            double totalCost;

            // Gather credit card information
            System.out.println("Enter Card Holder Name: ");
            cardHolderName = scanner.nextLine();
            System.out.println("Enter Card Number: ");
            cardNumber = scanner.nextLine();
            System.out.println("Enter CVV: ");
            cvv = Integer.parseInt(scanner.nextLine());

            // Process the credit card payment
            boolean paymentResult = paymentProcessor.processCreditCardPayment(cardNumber, cvv, cardHolderName, orderTotalPrice);

            if (paymentResult) {
                System.out.println("Credit Card Payment Successful.");
                return true;
            } else {
                System.out.println("Credit Card Payment Failed.");
                return false;
            }
        } else if (choice == 2) {
            // PayPal Payment
            System.out.println("PayPal payment processing is not implemented in this example.");
        }
        return false;
    }

    private static boolean checkQuantity(InventoryItem item, int quantity) {
        if (quantity <= item.getQuantity()) {
            System.out.println("Added " + item.getName() + " x" + quantity + " to the order.");
            return true;
        } else {
            System.out.println("Insufficient quantity for " + item.getName());
            return false;
        }
    }

    private static void displayItemsSorted(Scanner scanner, InventoryManager inventoryManager){
        System.out.println("Sort by: 1.Name / 2.Price");
        Integer option = Integer.parseInt(scanner.nextLine());
        switch (option){
            case 1:
                printList(inventoryManager.getItemsSortedBy(SortCriteria.NAME));
                break;
            case 2:
                printList(inventoryManager.getItemsSortedBy(SortCriteria.PRICE));
                break;
            default:
                System.out.println("Wrong input!");
        }
    }

    private static void getItemsByCategory(Scanner scanner, InventoryManager inventoryManager){
        for (ProductCategory category : ProductCategory.values()) {
            System.out.println(category.getCode() + ". " + category.getDisplayName());
        }
        System.out.println("Choose category by its code: ");
        int chosenCategoryCode = Integer.parseInt(scanner.nextLine());
        printList(inventoryManager.getItemsByCategory(ProductCategory.getCategoryByCode(chosenCategoryCode)));
    }

    private static void deleteInventoryItem(Scanner scanner, InventoryManager inventoryManager){
        System.out.println("Choose item by its ID: ");
        displayAllItems(inventoryManager);
        long chosenItem = Integer.parseInt(scanner.nextLine());
        if (inventoryManager.deleteItem(chosenItem)) {
            System.out.println("Successfully deleted item with ID " + chosenItem );
        }else{
            System.out.println("Unsuccessfully");
        }
    }

    private static void updateInventoryItem(Scanner scanner, InventoryManager inventoryManager) {
        System.out.println("Choose item by its ID: ");
        displayAllItems(inventoryManager);
        long chosenItem = Integer.parseInt(scanner.nextLine());
        InventoryItem item = inventoryManager.getById(chosenItem);
        System.out.println("1. Change name / 2. Change price");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                System.out.println("Enter new name:");
                String name = scanner.nextLine();
                item.setName(name);
                break;
            case "2":
                System.out.println("Enter new price:");
                double price = Double.parseDouble(scanner.nextLine());
                item.setPrice(price);
                break;
            default:
                System.out.println("Wrong");
        }
        if (inventoryManager.updateItem(item)) {
            System.out.println("Successfully deleted item with ID " + chosenItem);
        } else {
            System.out.println("Unsuccessfully");
        }
    }
}
