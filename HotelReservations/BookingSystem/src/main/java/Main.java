import com.booking.system.entities.*;
import com.booking.system.enums.UserRoles;
import com.booking.system.services.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static int loggedUserId = -1;
    public static Hotel hotel;
    public static RoomService roomService;
    public static UserService userService;
    public static BookingService bookingService;
    public static RoomTypeService roomTypeService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hotel = new Hotel("Arte", "Plovdiv", 4, new ArrayList<>());
        roomService = new RoomService(hotel);
        userService = new UserService();
        bookingService = new BookingService(hotel);
        roomTypeService = new RoomTypeService();

        while (true) {
            String command;
            System.out.println("""
                    Choose command by writing its value:
                        1.Log in // 2.Register""");
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("End")) {
                break;
            }
            String username;
            String password;
            switch (command) {
                case "1":
                    /*
                     * LOG IN -> if it is successful we set loggedUserId
                     * if it is not successful we go back to the beginning */
                    System.out.println("Enter username;");
                    username = scanner.nextLine();
                    System.out.println("Enter password;");
                    password = scanner.nextLine();
                    if (!logIn(username, password)) {
                        System.out.println("Invalid data");
                        continue;
                    } else {
                        loggedUserId = userService.getLoggedUser(username, password).getId();
                    }
                    break;
                case "2":
                    /*
                     * REGISTER -> if it is successful we set loggedUserId
                     * if it is not successful we go back to the beginning */
                    System.out.println("Enter username:");
                    username = scanner.nextLine();
                    System.out.println("Enter password:");
                    password = scanner.nextLine();
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    if (!registerUser(username, password, name, UserRoles.REGISTERED.getDisplayName(), 0)) {
                        System.out.println("Invalid data!");
                        continue;
                    } else {
                        loggedUserId = userService.getLoggedUser(username, password).getId();
                    }
                    break;
                default:
                    continue;
            }
            /*We are logged into the system*/
            while (true) {
                if (loggedUserId == -1) {
                    break;
                }
                System.out.println("""
                        Choose command by writing its value:
                        1.Get my profile info // 2.Delete my profile""");
                if (userService.isAdmin(loggedUserId)) {
                    System.out.println("""
                            3.Create room  // 4.Delete Room
                            5.View all bookings // 6.View total income
                            7.View total cancellation fees // 8.AddPromotionalCode """);
                    command = scanner.nextLine();
                    if (command.equalsIgnoreCase("End")) {
                        break;
                    }
                    if (!Validator.isValidValue(command)) {
                        System.out.println("Invalid command");
                        continue;
                    }
                    switch (command) {
                        case "1":
                            System.out.println(userService.getById(loggedUserId).toString());
                            break;
                        case "2":
                            userService.deleteUser(loggedUserId);
                            loggedUserId = -1;
                            break;
                        case "3":
                            createRoom(scanner);
                            break;
                        case "4":
                            deleteRoom(scanner);
                            break;
                        case "5":
                            viewAllBookings();
                            break;
                        case "6":
                            viewTotalIncome();
                            break;
                        case "7":
                            viewTotalCancellationFees();
                            break;
                        case "8":
                            // TODO: 24.10.2023 г.
                            break;
                    }
                } else {
                    System.out.println("""
                            3.View rooms  // 4.Book room
                            5.Cancel booking """);
                    command = scanner.nextLine();
                    if (command.equalsIgnoreCase("End")) {
                        break;
                    }
                    switch (command) {
                        case "1":
                            System.out.println(userService.getById(loggedUserId).toString());
                            break;
                        case "2":
                            userService.deleteUser(loggedUserId);
                            loggedUserId = -1;
                            break;
                        case "3":
                            viewAllRooms();
                            break;
                        case "4":
                            bookRoom(scanner);
                            break;
                        case "5":
                            cancelBooking(scanner);
                            break;
                    }
                }

            }
        }

    }

    public static boolean logIn(String username, String password) {
        User loggedUser = userService.getLoggedUser(username, password);
        if (loggedUser == null) {
            return false;
        }
        loggedUserId = loggedUser.getId();
        return true;
    }

    public static boolean registerUser(String username, String password, String name, String role, double cancellationFees) {
        return userService.createUser(username, password, name, role, cancellationFees);
    }

    public static void viewAllRooms() {
        List<Room> list = roomService.getAllRooms();
        for (Room room : list) {
            System.out.println(room.toString());
        }
    }

    public static void bookRoom(Scanner scanner) {
        System.out.println("Enter checkInDate in format 'dd-mm-yyyy':");
        String date = scanner.nextLine();
        LocalDate checkInDate = Validator.validateDate(date);
        while (checkInDate == null) {
            date = scanner.nextLine();
            checkInDate = Validator.validateDate(date);
        }
        System.out.println("Enter checkOutDate in format 'dd-mm-yyyy':");
        date = scanner.nextLine();
        LocalDate checkOutDate = Validator.validateDate(date);
        while (checkOutDate == null) {
            date = scanner.nextLine();
            checkOutDate = Validator.validateDate(date);
        }
        if (!Validator.validateDates(checkInDate, checkOutDate)) {
            System.out.println("Invalid dates");
            return;
        }
        while (true) {
            List<Room> availableRooms = bookingService.getAvailableRoomsForGivenDates(checkInDate, checkOutDate);
            if (availableRooms.isEmpty()) {
                System.out.println("There are not available rooms");
                break;
            }
            System.out.println("Choose room by its number: ");
            for (Room room : availableRooms) {
                System.out.println(room.toString());
            }
            String input = scanner.nextLine();
            while (!Validator.isValidValue(input)) {
                input = scanner.nextLine();
            }
            int roomNum = Integer.parseInt(input);
            if (bookingService.create(roomNum, loggedUserId, roomService.getRoomByRoomNum(roomNum).getType().getPricePerNight(), checkInDate, checkOutDate)){
                System.out.println("Successful");
                break;
            }
        }
    }
    public static void cancelBooking(Scanner scanner){
        List<Booking> bookingList = userService.getAllPresentOrFutureBookings(loggedUserId);
        if (bookingList.isEmpty()) {
            System.out.println("You don't have any bookings");
            return;
        }
        System.out.println("Choose booking you want to cancel (enter id):");
        for (Booking booking : bookingList) {
            System.out.println(booking.toString());
        }
        int bookingId = Integer.parseInt(scanner.nextLine());
        bookingService.cancelBooking(bookingId);
    }
    public static void createRoom(Scanner scanner){
        System.out.println("Enter room details in format 'roomNumber roomTypeId': ");
        for (RoomType roomType : roomTypeService.getAll()) {
            System.out.println(roomType.toString());
        }
        String input = scanner.nextLine();
        if (roomService.createRoom(input)) {
            System.out.println("Successful");
        }

    }
    public static void deleteRoom(Scanner scanner){
        for (Room room : roomService.getAllRooms()) {
            System.out.println(room.toString());
        }
        if (roomService.deleteRoom(Integer.parseInt(scanner.nextLine()))) {
            System.out.println("Successful");
        }
    }
    public static void viewTotalIncome(){
        double totalIncome = 0;
        for (Booking booking :bookingService.getAll()) {
            totalIncome += booking.getTotalPrice();
        }
        System.out.println(totalIncome);
    }
    public static void viewAllBookings(){
        for (Booking booking : bookingService.getAll()) {
            System.out.println(booking.toString());
        }
    }

    public static void viewTotalCancellationFees(){
        double totalCancellationFees = 0;
        for (User user : userService.getAllUsers()) {
            totalCancellationFees += user.getCancellationFees();
        }
        System.out.println(totalCancellationFees);
    }

}
