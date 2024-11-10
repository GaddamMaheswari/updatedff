package com.flipfit.bean;

import java.time.LocalTime;

public class GymSlots {
    // Fields representing the details of a gym slot
    private int centerId;      // ID of the gym center
    private int slotId;        // Unique ID for the slot
    private LocalTime startTime;  // Start time of the gym slot
    private LocalTime endTime;    // End time of the gym slot
    private int totalSeats;    // Total number of seats available for the slot
    private int cost;          // Cost of the slot for the customer

    // Getter for the cost of the slot
    public int getCost() {
        return cost;  // Returns the cost of the slot
    }

    // Setter for the cost of the slot
    public void setCost(int cost) {
        this.cost = cost;  // Sets the cost of the slot
    }

    // Getter for the total number of seats in the slot
    public int getTotalSeats() {
        return totalSeats;  // Returns the number of seats in the slot
    }

    // Setter for the total number of seats in the slot
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;  // Sets the number of seats for the slot
    }

    // Getter for the center ID where the slot is located
    public int getCenterId() {
        return centerId;  // Returns the center ID for this slot
    }

    // Setter for the center ID
    public void setCenterId(int centerId) {
        this.centerId = centerId;  // Sets the center ID for this slot
    }

    // Getter for the slot ID
    public int getSlotId() {
        return slotId;  // Returns the unique ID of this slot
    }

    // Setter for the slot ID
    public void setSlotId(int slotId) {
        this.slotId = slotId;  // Sets the unique slot ID
    }

    // Getter for the start time of the slot
    public LocalTime getStartTime() {
        return startTime;  // Returns the start time of the slot
    }

    // Setter for the start time of the slot
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;  // Sets the start time for the slot
    }

    // Getter for the end time of the slot
    public LocalTime getEndTime() {
        return endTime;  // Returns the end time of the slot
    }

    // Setter for the end time of the slot
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;  // Sets the end time for the slot
    }

    // Constructor for creating a GymSlot with center ID, start time, end time, seats, and cost
    public GymSlots(int centerId, LocalTime StartTime, LocalTime EndTime, int totalSeats, int cost) {
        this.centerId = centerId;   // Initialize the center ID
        this.startTime = StartTime; // Initialize the start time of the slot
        this.endTime = EndTime;     // Initialize the end time of the slot
        this.totalSeats = totalSeats; // Initialize the total seats for the slot
        this.cost = cost;           // Initialize the cost of the slot
    }

    // Constructor for creating a GymSlot with center ID, slot ID, start time, end time, seats, and cost
    public GymSlots(int centerId, int slotId, LocalTime startTime, LocalTime endTime, int totalSeats, int cost) {
        this.centerId = centerId;   // Initialize the center ID
        this.slotId = slotId;       // Initialize the slot ID
        this.startTime = startTime; // Initialize the start time
        this.endTime = endTime;     // Initialize the end time
        this.totalSeats = totalSeats; // Initialize the total seats
        this.cost = cost;           // Initialize the cost
    }
}
