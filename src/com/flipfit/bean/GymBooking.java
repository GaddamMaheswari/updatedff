package com.flipfit.bean;

import java.time.LocalTime;
import java.util.Date;

public class GymBooking {
    // BookingId: Unique identifier for the booking
    // CenterName: Name of the gym center where the booking is made
    // CenterLocation: Location of the gym center
    // StartTime: The start time of the booking
    // EndTime: The end time of the booking
    // BookingDate: The date the booking was made
    private int bookingId;  // Unique booking identifier
    private String centerName;  // Name of the gym center
    private String centerLocation;  // Location of the gym center
    private LocalTime startTime;  // Start time of the booking
    private LocalTime endTime;  // End time of the booking
    private Date bookingDate;  // Date the booking was made

    // Constructor to initialize all fields of the GymBooking object
    public GymBooking(int bookingId, String centerName, String centerLocation, LocalTime startTime, LocalTime endTime, Date bookingDate) {
        this.bookingId = bookingId;  // Set bookingId
        this.centerName = centerName;  // Set centerName
        this.centerLocation = centerLocation;  // Set centerLocation
        this.startTime = startTime;  // Set startTime
        this.endTime = endTime;  // Set endTime
        this.bookingDate = bookingDate;  // Set bookingDate
    }

    // Getter method for bookingId
    public int getBookingId() {
        return bookingId;  // Return the bookingId
    }

    // Setter method for bookingId
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;  // Set the bookingId
    }

    // Getter method for centerName
    public String getCenterName() {
        return centerName;  // Return the centerName
    }

    // Setter method for centerName
    public void setCenterName(String centerName) {
        this.centerName = centerName;  // Set the centerName
    }

    // Getter method for centerLocation
    public String getCenterLocation() {
        return centerLocation;  // Return the centerLocation
    }

    // Setter method for centerLocation
    public void setCenterLocation(String centerLocation) {
        this.centerLocation = centerLocation;  // Set the centerLocation
    }

    // Getter method for startTime
    public LocalTime getStartTime() {
        return startTime;  // Return the startTime
    }

    // Setter method for startTime
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;  // Set the startTime
    }

    // Getter method for endTime
    public LocalTime getEndTime() {
        return endTime;  // Return the endTime
    }

    // Setter method for endTime
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;  // Set the endTime
    }

    // Getter method for bookingDate
    public Date getBookingDate() {
        return bookingDate;  // Return the bookingDate
    }

    // Setter method for bookingDate
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;  // Set the bookingDate
    }
}
