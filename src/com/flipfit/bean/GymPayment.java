package com.flipfit.bean;

public class GymPayment {
    // Properties of the GymPayment class with their respective getters and setters

    // Unique payment ID
    private int paymentID;
    // Booking ID associated with the payment
    private int bookingID;
    // Payment mode (e.g., credit card, cash, etc.)
    private String mode;
    // Amount paid for the booking
    private int amount;

    // Getter method for bookingID
    public int getBookingID() {
        return bookingID;  // Return the bookingID
    }

    // Setter method for bookingID
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;  // Set the bookingID
    }

    // Getter method for mode
    public String getMode() {
        return mode;  // Return the payment mode
    }

    // Setter method for mode
    public void setMode(String mode) {
        this.mode = mode;  // Set the payment mode
    }

    // Getter method for amount
    public int getAmount() {
        return amount;  // Return the amount paid
    }

    // Setter method for amount
    public void setAmount(int amount) {
        this.amount = amount;  // Set the amount paid
    }

    // Constructor to initialize GymPayment with bookingID and mode
    public GymPayment(int bookingID, String mode) {
        this.bookingID = bookingID;  // Set the bookingID
        this.mode = mode;  // Set the payment mode
    }
}
