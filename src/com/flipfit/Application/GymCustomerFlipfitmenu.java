package com.flipfit.Application;

import com.flipfit.bean.GymBooking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymSlots;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.*;
import java.util.List;
import java.util.Scanner;

import com.flipfit.business.*;

public class GymCustomerFlipfitmenu {
    // Instance variables for current customer and business services
    int currentcustId;
    GymCustomerBusiness service = new GymCustomerBusinessImpl();
    GymCenterBusiness centerBusiness = new GymCenterBusinessImpl();
    GymAdminBusiness ser = new GymAdminBusinessImpl();

    // Method for registering a new Gym Customer
    public void register() {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Registering Gym Customer Menu");
        // Collecting customer details for registration
        System.out.println("Enter your name");
        String name = in.nextLine();
        System.out.println("Enter your email");
        String email = in.nextLine();
        System.out.println("Enter your Address");
        String address = in.nextLine();
        System.out.println("Enter your password");
        String password = in.nextLine();
        System.out.println("Enter your Contact Number");
        String contactNumber = in.nextLine();

        // Creating a new GymCustomer object and calling service to register
        GymCustomer customer = new GymCustomer(name, address, email, contactNumber, password);
        if(service.createProfile(customer)){
            System.out.println("Registered Successfully!!");
        } else {
            System.out.println("User already exists with this emailID!!");
        }
    }

    // Method for displaying the customer menu and handling the customer actions
    public void gymcustomermenu(int custId) {
        currentcustId = custId;  // Set current customer ID
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current Date and Time: " + dtf.format(now));  // Display current date and time

        while (true) {
            System.out.println("Customer ID:" + currentcustId);
            System.out.println("Welcome to FlipFit Customer Menu");
            System.out.println("------------------------------------------------------------------------------------------------");
            java.util.Scanner in = new java.util.Scanner(System.in);
            // Display menu options
            System.out.println("1. View My Bookings");
            System.out.println("2. View GymCenter/Book a Slot");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Edit Profile");
            System.out.println("5. Exit");

            // Get the user's choice
            int choice = in.nextInt();
            if (choice == 1) {
                // Option 1: View current customer's bookings
                List<GymBooking> list = service.viewBookings(currentcustId);  // Get bookings from the service
                if(!list.isEmpty()){
                    System.out.println("------------------------------------------------------------------------------------------------");
                    // Print the bookings in a tabular format
                    System.out.printf("%-15s %-15s %-20s %-25s %-20s %-15s%n",
                            "BookingId", "CenterName","CenterLocation","StartTime","EndTime","Date");
                    for (GymBooking booking : list) {
                        System.out.printf("%-15s %-15s %-20s %-25s %-20s %-15s%n",
                                booking.getBookingId(), booking.getCenterName(), booking.getCenterLocation() ,
                                booking.getStartTime().toString(), booking.getEndTime().toString(), booking.getBookingDate());
                    }
                }
            }
            else if (choice == 2) {
                // Option 2: View gym centers and book a slot
                List<GymCenter> list = ser.viewCenter();  // Get list of available gym centers
                if(list.size() == 0){
                    continue; // No centers available, continue to the next iteration
                }
                // Display available centers in a tabular format
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.printf("%-15s %-15s %-20s %-25s %-30s%n","CenterId"," OwnerId","CenterName"," CenterLocation"," NumOfSlots");
                for (GymCenter center : list) {
                    System.out.printf("%-15s %-15s %-20s %-25s %-30s%n",center.getCenterId(),center.getOwnerId(),
                            center.getGymName(), center.getGymLocation(), center.getNumOfSlots());
                }
                // Prompt the user to enter a date to book a slot
                System.out.println("Enter a date (dd/mm/yyyy): ");
                in = new java.util.Scanner(System.in);
                String dateStr = in.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date = sdf.parse(dateStr);  // Parse the date entered by the user
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.println("Date you entered is : " + date.toString());
                    // Prompt for center ID to book a slot
                    System.out.println("Enter the centre ID to select a slot for booking");
                    int centerId = in.nextInt();
                    List<GymSlots> slots = centerBusiness.viewSlots(centerId, date);  // Get available slots for the selected date
                    if(slots.size() == 0){
                        System.out.println("No slot found");
                        continue;  // If no slots are found, continue the loop
                    }
                    // Display available slots in tabular format
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.printf("%-15s %-15s %-20s %-25s %-30s %-15s%n","SlotId", "CenterId","StartTime","EndTime","AvailableNumSeats","Cost");
                    for (GymSlots slot : slots) {
                        System.out.printf("%-15s %-15s %-20s %-25s %-30s %-15s%n",slot.getSlotId(),slot.getCenterId(),
                                slot.getStartTime(), slot.getEndTime(), slot.getCost(), slot.getTotalSeats());
                    }
                    // Prompt for slot selection and booking
                    System.out.println("select a slot Id for booking");
                    int slotId = in.nextInt();
                    int bookingId = service.createBooking(currentcustId, slotId, centerId, date);  // Create the booking
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.println("Type Payment Option as one of below without spaces:");
                    System.out.println("1. CreditCard");
                    System.out.println("2. DebitCard");
                    System.out.println("3. UPI");
                    System.out.println("4. Netbanking");
                    in = new java.util.Scanner(System.in);
                    String mode = in.nextLine().toLowerCase();  // Get the payment mode
                    int paymentId = service.makepayment(bookingId, mode);  // Process the payment
                    System.out.println("PaymentId = " + paymentId + "|| BookingId = " + bookingId);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if (choice == 3) {
                // Option 3: Cancel an existing booking
                in = new Scanner(System.in);
                System.out.println("Enter the booking Id to be cancelled");
                int bookingId = in.nextInt();
                if(service.cancelBooking(currentcustId, bookingId)){
                    System.out.println("Booking Canceled Successfully");
                }
            }
            else if (choice == 4) {
                // Option 4: Edit customer profile
                in = new java.util.Scanner(System.in);
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Editing profile ...");
                // Collect updated profile information
                System.out.println("Enter your new name");
                String name = in.nextLine();
                System.out.println("Enter your new email");
                String email = in.nextLine();
                System.out.println("Enter your new Address");
                String address = in.nextLine();
                System.out.println("Enter your new password");
                String password = in.nextLine();
                System.out.println("Enter your new Contact Number");
                String contactNumber = in.nextLine();
                GymCustomer customer = new GymCustomer(name, address, email, contactNumber, password);
                customer.setCustomerId(currentcustId);
                // Call service to update the profile
                if(service.editProfile(customer)){
                    System.out.println("Profile Edited Successfully");
                }
            }
            else if (choice == 5)
                break;  // Exit the menu loop
            else
                System.out.println("Invalid choice Try Again");  // Handle invalid menu choice
        }
    }
}
