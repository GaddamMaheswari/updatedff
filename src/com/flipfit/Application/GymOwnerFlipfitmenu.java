package com.flipfit.Application;

import com.flipfit.bean.GymOwner;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.flipfit.bean.GymSlots;
import com.flipfit.business.GymOwnerBusiness;
import com.flipfit.business.GymOwnerBusinessImpl;

public class GymOwnerFlipfitmenu {
    int currentownerId = 0;  // Holds the current owner's ID
    GymOwnerBusiness service = new GymOwnerBusinessImpl();  // Service instance to handle gym owner operations

    // Method to display and handle the gym owner menu
    public void gymownermenu(int ownerId) {
        currentownerId = ownerId;  // Assign the provided owner ID to the current owner
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  // Date format for displaying current date and time
        LocalDateTime now = LocalDateTime.now();  // Get current date and time
        System.out.println("Current Date and Time: " + dtf.format(now));  // Print current date and time

        while (true) {
            System.out.println("GymOwnerID: " + currentownerId);  // Display the current gym owner ID
            System.out.println("Welcome to FlipFit Owner Menu");  // Display a welcome message
            System.out.println("------------------------------------------------------------------------------------------------");
            java.util.Scanner in = new java.util.Scanner(System.in);  // Create scanner object for input
            // Display the menu options for the gym owner
            System.out.println("1. Register Center");
            System.out.println("2. Add New Slot");
            System.out.println("3. Delete Slot");
            System.out.println("4. Delete Center");
            System.out.println("5. Edit Profile");
            System.out.println("6. Exit");

            int choice = in.nextInt();  // Get the owner's menu choice
            if (choice == 1) {  // Register Center option
                Scanner scanner = new Scanner(System.in);  // Create scanner for input
                System.out.println("Registering Gym Center...");  // Prompt for gym center registration
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Enter your CenterName");  // Prompt for the center name
                String centerName = scanner.next();
                System.out.println("Enter your Center Location");  // Prompt for the center location
                String location = scanner.next();
                System.out.println("Enter No Of Slots In Center");  // Prompt for the number of slots
                int slots = scanner.nextInt();
                // Register the gym center using the provided details
                if(service.registerCenter(currentownerId, centerName, location, slots)){
                    System.out.println("Gym Center Registered Successfully");  // Success message
                }
            }
            else if (choice == 2) {  // Add New Slot option
                Scanner scanner = new Scanner(System.in);  // Create scanner for input
                System.out.println("Adding new slot...");  // Prompt for adding a new slot
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Enter your CenterId:");  // Prompt for center ID
                int centerId = scanner.nextInt();
                System.out.println("Enter your StartTime as HH:MM:SS in 24-Hour Format");  // Prompt for start time
                LocalTime startTime= LocalTime.parse(scanner.next());  // Parse start time
                System.out.println("Enter your EndTime as HH:MM:SS in 24-Hour Format");  // Prompt for end time
                LocalTime endTime = LocalTime.parse(scanner.next());  // Parse end time
                System.out.println("Enter Number of Seats:");  // Prompt for the number of seats
                int seats = scanner.nextInt();
                System.out.println("Enter Cost:");  // Prompt for the cost
                int cost = scanner.nextInt();
                GymSlots slot = new GymSlots(centerId, startTime, endTime, seats, cost);  // Create a new GymSlots object
                // Add the new slot to the gym center
                if(service.addnewSlot(centerId, slot)){
                    System.out.println("New Slot Added Successfully");  // Success message
                }
            }
            else if (choice == 3) {  // Delete Slot option
                Scanner scanner = new Scanner(System.in);  // Create scanner for input
                System.out.println("Deleting slot...");  // Prompt for deleting a slot
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("
