import java.util.Scanner;

public class HotelBookingSystem {

    static boolean[][] rooms = new boolean[5][5]; 

    public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the Hotel Booking System");

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. View Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Hotel Booking System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1-3.");
            }

        } while (choice != 3);

        scanner.close();
    }

  
    public static void viewRooms() {
        System.out.println("\nRoom Status (F = Floor, R = Room):");
        for (int floor = 0; floor < rooms.length; floor++) {
            for (int room = 0; room < rooms[floor].length; room++) {
                String status = rooms[floor][room] ? "Booked" : "Available";
                System.out.printf("F%d-R%d: %s\t", floor + 1, room + 1, status);
            }
            System.out.println();
        }
    }


    public static void bookRoom(Scanner scanner) {
        System.out.print("Enter floor number (1-5): ");
        int floor = scanner.nextInt();
        System.out.print("Enter room number (1-5): ");
        int room = scanner.nextInt();

        if (floor < 1 || floor > 5 || room < 1 || room > 5) {
            System.out.println("Invalid floor or room number.");
            return;
        }

        if (rooms[floor - 1][room - 1]) {
            System.out.println("Sorry, this room is already booked.");
        } else {
            rooms[floor - 1][room - 1] = true;
            System.out.println("Room F" + floor + "-R" + room + " successfully booked!");
        }
    }
}
