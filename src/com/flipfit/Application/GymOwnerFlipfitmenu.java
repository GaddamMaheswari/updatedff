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
    int currentownerId = 0;
    GymOwnerBusiness service = new GymOwnerBusinessImpl();

    public void gymownermenu(int ownerId) {
        currentownerId = ownerId;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current Date and Time: " + dtf.format(now));
        while (true) {
            System.out.println("GymOwnerID: " + currentownerId);
            System.out.println("Welcome to FlipFit Owner Menu");
            System.out.println("------------------------------------------------------------------------------------------------");
            java.util.Scanner in = new java.util.Scanner(System.in);
            System.out.println("1. Register Center");
            System.out.println("2. Add New Slot");
            System.out.println("3. Delete Slot");
            System.out.println("4. Delete Center");
            System.out.println("5. Edit Profile");
            System.out.println("6. Exit");
            int choice = in.nextInt();
            if (choice == 1) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Registering Gym Center...");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Enter your CenterName");
                String centerName = scanner.next();
                System.out.println("Enter your Center Location");
                String location = scanner.next();
                System.out.println("Enter No Of Slots In Center");
                int slots = scanner.nextInt();
                if(service.registerCenter(currentownerId, centerName, location, slots)){
                    System.out.println("Gym Center Registered Successfully");
                }
            }
            else if (choice == 2) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Adding new slot...");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Enter your CenterId:");
                int centerId = scanner.nextInt();
                System.out.println("Enter your StartTime as HH:MM:SS in 24-Hour Format");
                LocalTime startTime= LocalTime.parse(scanner.next());
                System.out.println("Enter your EndTime as HH:MM:SS in 24-Hour Format");
                LocalTime endTime = LocalTime.parse(scanner.next());
                System.out.println("Enter Number of Seats:");
                int seats = scanner.nextInt();
                System.out.println("Enter Cost:");
                int cost = scanner.nextInt();
                GymSlots slot = new GymSlots(centerId, startTime, endTime, seats, cost);
                if(service.addnewSlot(centerId, slot)){
                    System.out.println("New Slot Added Successfully");
                }
            }
            else if (choice == 3) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Deleting slot...");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Enter your CenterId:");
                int centerId = scanner.nextInt();
                System.out.println("Enter your StartTime as HH:MM:SS in 24-Hour Format");
                LocalTime startTime= LocalTime.parse(scanner.next());
                if(service.deleteSlot(centerId, startTime)){
                    System.out.println("Slot Deleted Successfully");
                }
            }
            else if (choice == 4) {
                System.out.println("Deleting center...");
                System.out.println("------------------------------------------------------------------------------------------------");
                in = new java.util.Scanner(System.in);
                System.out.println("Enter the center ID to be deleted: ");
                int centerId = in.nextInt();
                if(service.deleteCenter(centerId)){
                    System.out.println("Center Deleted Successfully");
                }
            }
            else if (choice == 5) {
                in = new java.util.Scanner(System.in);
                System.out.println("Editing profile...");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Enter your new name");
                String name = in.nextLine();
                System.out.println("Enter your new email");
                String email = in.nextLine();
                System.out.println("Enter your new password");
                String password = in.nextLine();
                System.out.println("Enter your new Address");
                String address = in.nextLine();
                System.out.println("Enter your new Contact Number");
                String contactNumber = in.nextLine();
                GymOwner owner = new GymOwner(name, email, contactNumber, address, password);
                owner.setOwnerId(currentownerId);
                if(service.editProfile(owner)){
                    System.out.println("Profile Edited Successfully");
                }
            }
            else if (choice == 6)
                break;
            else
                System.out.println("Invalid choice");
        }

    }

    public void register() {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("Registering Gym Owner Menu");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Enter your name");
        String name = in.nextLine();
        System.out.println("Enter your email");
        String email = in.nextLine();
        System.out.println("Enter your password");
        String password = in.nextLine();
        System.out.println("Enter your Address");
        String address = in.nextLine();
        System.out.println("Enter your Contact Number");
        String contactNumber = in.nextLine();
        GymOwner gymOwner = new GymOwner(name, email, contactNumber, address, password);
        if(service.createProfile(gymOwner)){
            System.out.println("Registered Successfully!!");
        } else {
            System.out.println("User already exists with this email");
        }
    }
}
