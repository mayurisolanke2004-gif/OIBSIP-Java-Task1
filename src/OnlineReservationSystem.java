import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class OnlineReservationSystem {
    
    // Train data store karnyasathi
    static Map<String, String> trains = new HashMap<>();
    // Booked tickets: PNR -> details
    static Map<String, String> bookings = new HashMap<>();
    // Login details
    static final String USERNAME = "mayuri";
    static final String PASSWORD = "1234";
    
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    
    public static void main(String[] args) {
        // Sample trains add karu
        trains.put("12121", "Pune - Mumbai Express");
        trains.put("12122", "Nagpur - Pune SF");
        trains.put("11007", "Mumbai - Solapur Exp");
        
        System.out.println("=== Online Reservation System ===");
        
        if(login()) {
            int choice;
            do {
                showMenu();
                choice = sc.nextInt();
                sc.nextLine(); // buffer clear
                
                switch(choice) {
                    case 1:
                        bookTicket();
                        break;
                    case 2:
                        cancelTicket();
                        break;
                    case 3:
                        viewBookings();
                        break;
                    case 4:
                        System.out.println("Thank you! Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while(choice != 4);
        } else {
            System.out.println("Login Failed. Exiting...");
        }
        sc.close();
    }
    
    // Login function
    static boolean login() {
        System.out.print("Enter Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();
        
        if(user.equals(USERNAME) && pass.equals(PASSWORD)) {
            System.out.println("Login Successful! Welcome " + user);
            return true;
        }
        return false;
    }
    
    // Menu dakhavne
    static void showMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Book Ticket");
        System.out.println("2. Cancel Ticket");
        System.out.println("3. View All Bookings");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
    
    // Ticket book karne
    static void bookTicket() {
        System.out.println("\n--- Available Trains ---");
        for(Map.Entry<String, String> entry : trains.entrySet()) {
            System.out.println("Train No: " + entry.getKey() + " | Name: " + entry.getValue());
        }
        
        System.out.print("\nEnter Train Number: ");
        String trainNo = sc.nextLine();
        
        if(!trains.containsKey(trainNo)) {
            System.out.println("Invalid Train Number!");
            return;
        }
        
        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Enter From Station: ");
        String from = sc.nextLine();
        System.out.print("Enter To Station: ");
        String to = sc.nextLine();
        System.out.print("Enter Date of Journey DD-MM-YYYY: ");
        String date = sc.nextLine();
        
        // PNR generate karne
        int pnr = 100000 + rand.nextInt(900000);
        String pnrStr = String.valueOf(pnr);
        
        String details = "Name: " + name + ", Train: " + trainNo + " - " + trains.get(trainNo) 
                       + ", From: " + from + ", To: " + to + ", Date: " + date;
        
        bookings.put(pnrStr, details);
        
        System.out.println("\n✅ Ticket Booked Successfully!");
        System.out.println("Your PNR is: " + pnrStr);
        System.out.println("Please save this PNR for future reference.");
    }
    
    // Ticket cancel karne
    static void cancelTicket() {
        System.out.print("\nEnter PNR to Cancel: ");
        String pnr = sc.nextLine();
        
        if(bookings.containsKey(pnr)) {
            System.out.println("\nTicket Details: " + bookings.get(pnr));
            System.out.print("Are you sure you want to cancel? y/n: ");
            String confirm = sc.nextLine();
            
            if(confirm.equalsIgnoreCase("y")) {
                bookings.remove(pnr);
                System.out.println("✅ Ticket Cancelled Successfully!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("❌ Invalid PNR. No booking found.");
        }
    }
    
    // Saglya bookings baghne
    static void viewBookings() {
        if(bookings.isEmpty()) {
            System.out.println("\nNo bookings found.");
            return;
        }
        System.out.println("\n--- All Bookings ---");
        for(Map.Entry<String, String> entry : bookings.entrySet()) {
            System.out.println("PNR: " + entry.getKey() + " | " + entry.getValue());
        }
    }
}