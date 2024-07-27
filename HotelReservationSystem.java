import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room
{
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber,String category,boolean isAvailable,double price)
    {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.price =  price;
    }
    public int getRoomNumber()
    {
        return roomNumber;
    }
    public String getCategory()
    {
        return category;
    }
    public boolean isAvailable()
    {
        return isAvailable;
    }
    public void setAvailable(boolean available)
    {
        isAvailable = available;
    }
    public double getPrice()
    {
        return price;
    }
    @Override
    public String toString() 
    {
        return "roomNumber=" + roomNumber + ", category=" + category + ", isAvailable=" + isAvailable + ", price="
                + price;
    }
}
class Reservation
{
    private Room room;
    private String guestName;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(Room room, String guestName, String checkInDate , String checkOutDate)
    {
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public Room getRoom()
    {
        return room;
    }
    public String getGuestName()
    {
        return guestName;
    }
    public String getCheckInDate()
    {
        return checkInDate;
    }
    public String getCheckOutDate()
    {
        return checkOutDate;
    }
    public double getTotalCost()
    {
        return room.getPrice();
    }
    @Override
    public String toString() 
    {
        return "Reservation {Room=[" + room + ", guestName=" + guestName + ", checkInDate=" + checkInDate
                + ", checkOutDate=" + checkOutDate + "]}";
    }

}

class Hotel
{
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel()
    {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        rooms.add(new Room(101,"Single",true,60.0));
        rooms.add(new Room(102,"Double",true,75.0));
        rooms.add(new Room(103,"Suite",true,150.0));
        rooms.add(new Room(104,"Single",true,50.0));
        rooms.add(new Room(105,"Deluxe",true,130.0));
    }
    public List<Room> searchAvailableRooms(String category)
    {
        List<Room> availableRooms = new ArrayList<>();
        for(Room room : rooms)
        {
            if(room.isAvailable() && room.getCategory().equalsIgnoreCase(category))
            {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public void makeReservation(int roomNumber,String guestName,String checkInDate,String checkOutDate)
    {
        for(Room room : rooms)
        {
            if(room.getRoomNumber()==roomNumber && room.isAvailable())
            {
                room.setAvailable(false);
                Reservation reservation = new Reservation(room, guestName, checkInDate, checkOutDate);
                reservations.add(reservation);
                System.out.println("Reservation successful !");
                return;
            }
        }
        System.out.println("Room is not available !");
    }
    public void viewReservations()
    {
        for(Reservation reservation : reservations)
        {
            System.out.println(reservation);
        }
    }
    public double calculateTotalCost()
    {
        double totalCost = 0;
        for(Reservation reservation : reservations)
        {
            totalCost += reservation.getTotalCost();
        }
        return totalCost;
    }
    public void processPayment(double amount)
    {
       double totalCost = calculateTotalCost();
       if(amount >= totalCost)
       {
            System.out.println("Processing payment of $"+ amount);
            System.out.println("Payment successfull! Thank you");
       }
       else
       {
        System.out.println("Payment of $" + amount +" is not sufficient. Total cost is $" +totalCost);
       }
    }
}
public class HotelReservationSystem 
{
    public static void main(String[] args) 
    {
        Hotel hotel = new Hotel();
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("1. Search available rooms");
            System.out.println("2. Make reservation");
            System.out.println("3. View reservations");
            System.out.println("4. Process payment");
            System.out.println("5. Exit");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1 -> {
                    System.out.println("Enter room category (single , Double , Suite , Deluxe): ");
                    String category = sc.nextLine();
                    List<Room> availableRooms = hotel.searchAvailableRooms(category);
                    if(availableRooms.isEmpty())
                    {
                        System.out.println("No available rooms in this category");
                    }
                    else
                    {
                        for(Room room : availableRooms)
                        {
                            System.out.println(room);
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Enter room Number");
                    int roomNumber = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter guest name:");
                    String guestName = sc.nextLine();
                    System.out.println("Enter check-in-date (DD-MM-YYYY): ");
                    String checkInDate = sc.nextLine();
                    System.out.println("Enter check-out-date (DD-MM-YYYY): ");
                    String checkOutDate = sc.nextLine();
                    hotel.makeReservation(roomNumber, guestName, checkInDate, checkOutDate);
                }
                case 3 -> hotel.viewReservations();
                case 4 -> {
                    double totalCost = hotel.calculateTotalCost();
                    System.out.println("Total amount to pay: $"+ totalCost);
                    System.out.println("Enter amount to pay: ");
                    double amount = sc.nextDouble();
                    hotel.processPayment(amount);
                }
                case 5 -> {
                    System.out.println("Exiting......");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. please try again..");
            }
        }  
    }
}
