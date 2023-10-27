import com.booking.system.enums.RoomStatus;
import com.booking.system.enums.RoomType;
import com.booking.system.enums.UserRoles;
import com.booking.system.models.Hotel;
import com.booking.system.models.Room;
import com.booking.system.models.User;
import com.booking.system.services.BookingManagement;
import com.booking.system.services.RoomManagement;
import com.booking.system.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int loggedUser = 0;

        List<Room> rooms = new ArrayList<>(){
            Room r1 = new Room(1, RoomType.SINGLE.getDisplayName(), 350,
                    7.7, RoomStatus.FREE.getDisplayName(), 2, new ArrayList<>(Arrays.asList("TV", "wifi")));
            Room r2 = new Room(20, RoomType.DELUXE.getDisplayName(), 100,
                    10.5, RoomStatus.FREE.getDisplayName(), 3, new ArrayList<>(Arrays.asList("TV", "wifi")));
            Room r3 = new Room(2, RoomType.SINGLE.getDisplayName(), 50,
                    5.9, RoomStatus.FREE.getDisplayName(),2, new ArrayList<>(Arrays.asList("TV")));
            Room r4 = new Room(5, RoomType.DOUBLE.getDisplayName(), 230,
                    2.5, RoomStatus.FREE.getDisplayName(), 4, new ArrayList<>(Arrays.asList("wifi")));

        };
        Hotel hotel = new Hotel("Arte", "Plovdiv", 4, rooms );

        UserService userService = new UserService();
        RoomManagement roomManagement = new RoomManagement(hotel);
        BookingManagement bookingManagement = new BookingManagement();

        for (Room room : hotel.getRooms()) {
            roomManagement.createRoom(room);
        }

        User admin = new User("admin", "adminpass", UserRoles.ADMIN.getDisplayName());
        userService.addAdmin(admin);

        while (true){
            System.out.println("""
                        Choose command by writing its value:
                        1.Log in // 2.Register""");
            String username;
            String pass;
            switch (scanner.nextLine()){
                case "1":
                    System.out.println("Username: ");
                    username = scanner.nextLine();
                    System.out.println("Password: ");
                    pass = scanner.nextLine();
                    User user = userService.getLoggedUser(username, pass);
                    if ( user != null) {
                        loggedUser = user.getId();
                    }else{
                        System.out.println("Wrong");
                        continue;
                    }
                    break;
                case "2":
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Age: ");
                    String age = scanner.nextLine();
                    System.out.println("Username: ");
                    username = scanner.nextLine();
                    System.out.println("Password: ");
                    pass = scanner.nextLine();
                    if (!userService.createUser(new String[]{username, pass, name, age})) {
                        System.out.println("error");
                        continue;
                    }
                    break;
                default:
                    continue;
            }
            System.out.println("1.View Rooms");
            System.out.println("2.Book a Room");
            System.out.println("3.Cancel Booking");

            switch (scanner.nextLine()){
                case "1":
                    roomManagement.getAllRooms()
                            .stream()
                            .filter(e -> e.getStatus()
                                    .equals(RoomStatus.FREE));
                    break;
                case "2":
                    //ENTER:
                    //roomNum checkInDate checkOutDate
                    System.out.println("Enter checkInDate in format \"year, month, day\"" );
                    int[] checkInDate = Arrays.stream(scanner.nextLine().split(", "))
                            .mapToInt(e -> Integer.parseInt(e))
                            .toArray();
                    System.out.println("Enter checkOutDate in format \"year, month, day\"" );
                    int[] checkOutDate = Arrays.stream(scanner.nextLine().split(", "))
                            .mapToInt(e -> Integer.parseInt(e))
                            .toArray();
                    for (Room room : hotel.getRooms()) {
                        System.out.println(room);
                    }
                    System.out.println("Enter room num");
                    int roomNum = Integer.parseInt(scanner.nextLine());

                    bookingManagement.createBooking(roomNum, loggedUser, checkInDate, checkOutDate);

                    break;
                default:
                    continue;
            }
            String[] input = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .toArray(String[]::new);
            if (userService.createUser(input)) {
                System.out.println("Success");
            }
        }
    }

}