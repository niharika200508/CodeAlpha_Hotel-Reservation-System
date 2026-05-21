import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isBooked;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        isBooked = true;
    }

    public void cancelRoom() {
        isBooked = false;
    }

    public void displayRoom() {
        System.out.println("Room Number : " + roomNumber);
        System.out.println("Category    : " + category);
        System.out.println("Price       : \u20B9" + price);
        System.out.println("Status      : " + (isBooked ? "Booked" : "Available"));
        System.out.println("-----------------------------------");
    }
}

class Booking {
    private String customerName;
    private String phoneNumber;
    private Room room;

    public Booking(String customerName, String phoneNumber, Room room) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.room = room;
    }

    public int getRoomNumber() {
        return room.getRoomNumber();
    }

    public void displayBooking() {
        System.out.println("Customer Name : " + customerName);
        System.out.println("Phone Number  : " + phoneNumber);
        System.out.println("Room Number   : " + room.getRoomNumber());
        System.out.println("Category      : " + room.getCategory());
        System.out.println("Price         : \u20B9" + room.getPrice());
        System.out.println("-----------------------------------");
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Deluxe", 2500));
        rooms.add(new Room(103, "Suite", 4000));

        int choice;

        do {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelReservation();
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);
    }

    public static void viewRooms() {

        System.out.println("\n===== ROOM DETAILS =====");

        for (Room room : rooms) {
            room.displayRoom();
        }
    }

    public static void bookRoom() {

        System.out.print("Enter Room Number to Book : ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNo) {

                if (!room.isBooked()) {

                    System.out.print("Enter Customer Name : ");
                    String name = sc.nextLine();

                    System.out.print("Enter Phone Number : ");
                    String phone = sc.nextLine();

                    room.bookRoom();

                    bookings.add(new Booking(name, phone, room));

                    System.out.println("\nPayment Successful!");
                    System.out.println("Room Booked Successfully!");

                } else {
                    System.out.println("Room Already Booked!");
                }

                return;
            }
        }

        System.out.println("Room Not Found!");
    }

    public static void cancelReservation() {

        System.out.print("Enter Room Number to Cancel : ");
        int roomNo = sc.nextInt();

        for (Booking booking : bookings) {

            if (booking.getRoomNumber() == roomNo) {

                for (Room room : rooms) {

                    if (room.getRoomNumber() == roomNo) {
                        room.cancelRoom();
                        break;
                    }
                }

                bookings.remove(booking);

                System.out.println("Reservation Cancelled Successfully!");
                return;
            }
        }

        System.out.println("Booking Not Found!");
    }

    public static void viewBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No Bookings Found!");
            return;
        }

        System.out.println("\n===== BOOKING DETAILS =====");

        for (Booking booking : bookings) {
            booking.displayBooking();
        }
    }
}