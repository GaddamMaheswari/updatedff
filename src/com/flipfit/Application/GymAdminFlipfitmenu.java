package com.flipfit.Application;

import com.flipfit.bean.GymBooking;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.GymOwnerRequest;
import com.flipfit.business.GymAdminBusiness;
import com.flipfit.business.GymAdminBusinessImpl;
import com.flipfit.business.GymUserBusiness;
import com.flipfit.business.GymUserBusinessImpl;

import java.util.*;

public class GymAdminFlipfitmenu {
    // Create instances of business services for admin and user functionalities
    GymAdminBusiness service = new GymAdminBusinessImpl();
    GymUserBusiness userService = new GymUserBusinessImpl();
    int currentAdminId = 0;

    // Method to show the Gym Admin menu and handle user interaction
    public void gymadminmenu(int adminId) {
        System.out.println("Welcome to FlipFit Admin Menu");
        currentAdminId = adminId;

        // Start a loop for continuous menu interaction
        while (true) {
            java.util.Scanner in = new java.util.Scanner(System.in);

            // Display the admin menu with options
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("1. View All Bookings");
            System.out.println("2. View All Customers");
            System.out.println("3. View All Owners");
            System.out.println("4. See Pending Requests");
            System.out.println("5. Approve Requests");
            System.out.println("6. Exit");

            // Get user choice for the menu
            int choice = in.nextInt();

            // Option 1: View all bookings
            if (choice == 1) {
                List<GymBooking> list = service.viewBookings();  // Call the service to get all bookings
                if (list.isEmpty()) {
                    continue; // If no bookings, continue to the next iteration
                } else {
                    // Print header for bookings list
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.printf("%-15s %-25s %-20s %-10s %-10s%n", "BookingId" ,  "CenterName ","StartTime","EndTime","Date");
                    // Iterate through the bookings and display details
                    for (GymBooking booking : list) {
                        System.out.printf("%-15s %-25s %-20s %-10s %-10s%n",
                                booking.getBookingId() , booking.getCenterName(),
                                booking.getStartTime().toString(), booking.getEndTime().toString(),
                                booking.getBookingDate());
                    }
                }

            }
            // Option 2: View all customers
            else if (choice == 2) {
                List<GymCustomer> list = userService.viewAllCustomers();  // Get all customers
                if (list.isEmpty()) {
                    continue; // If no customers, continue to the next iteration
                } else {
                    // Print header for customer list
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.printf("%-15s %-25s %-20s %-10s %-10s%n","CustomerId", "CustomerName", "CustomerEmail", "CustomerAddress", "CustomerPhone");
                    // Iterate through the customers and display details
                    for (GymCustomer customer : list) {
                        System.out.printf("%-15s %-25s %-20s %-10s %-10s%n",
                                customer.getCustomerId(), customer.getCustomerName(),
                                customer.getCustomerEmailAddress(), customer.getCustomerAddress(),
                                customer.getCustomerPhone());
                    }
                }
            }
            // Option 3: View all gym owners
            else if (choice == 3) {
                List<GymOwner> list = userService.viewAllGymOwners();  // Get all gym owners
                if (list.isEmpty()) {
                    continue; // If no owners, continue to the next iteration
                } else {
                    // Print header for owners list
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.printf("%-15s %-25s %-20s %-10s %-10s%n","OwnerId", "OwnerName", "OwnerEmail", "OwnerAddress", "OwnerPhone");
                    // Iterate through the owners and display details
                    for (GymOwner owner : list) {
                        System.out.printf("%-15s %-25s %-20s %-10s %-10s%n",
                                owner.getOwnerId(), owner.getOwnerName(), owner.getOwnerEmailAddress(),
                                owner.getOwnerAddress(), owner.getOwnerPhone());
                    }
                }
            }
            // Option 4: See pending requests for gym owner registration
            else if (choice == 4) {
                List<GymOwnerRequest> requests = service.pendingRequests();  // Get all pending requests
                if (requests.isEmpty()) {
                    continue; // If no pending requests, continue to the next iteration
                } else {
                    // Print header for pending requests list
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.printf("%-15s %-15s %-20s %-25s %-30s %-15s%n",
                            "RequestId", "OwnerId", "Status", "CenterName", "CenterLocation", "NumOfSlots");
                    // Iterate through pending requests and display details
                    for (GymOwnerRequest request : requests) {
                        System.out.printf("%-15s %-15s %-20s %-25s %-30s %-15s%n",
                                request.getRequestId(), request.getOwnerId(), request.getStatus(),
                                request.getCenterName(), request.getCenterLocation(), request.getNumOfSlots());
                    }
                }
            }
            // Option 5: Approve or reject requests
            else if (choice == 5) {
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Provide Your Request ID for approving/rejecting");
                int requestId = scanner.nextInt();  // Get request ID from user input
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Select Choice : approve/reject");
                String statuss = scanner.next();  // Get approval or rejection status
                // Call service method to approve or reject the request
                service.approveOwnerRegistration(requestId, statuss);
            }
            // Option 6: Exit the menu
            else if (choice == 6)
                break;  // Exit the loop and terminate the menu interaction
            else {
                System.out.println("Invalid choice, Try Again");  // Handle invalid choice
            }
        }
    }
}
