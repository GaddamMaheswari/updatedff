package com.flipfit.bean;

public class GymOwner {
    // Properties of the GymOwner class with their respective getters and setters

    // Unique ID for the gym owner
    private int ownerId;
    // Name of the gym owner
    private String ownerName;
    // Email address of the gym owner
    private String ownerEmailAddress;
    // Phone number of the gym owner
    private String ownerPhone;
    // Address of the gym owner
    private String ownerAddress;
    // Password for owner authentication
    private String password;

    // Getter method for password
    public String getPassword() {
        return password;  // Return the password
    }

    // Setter method for password
    public void setPassword(String password) {
        this.password = password;  // Set the password
    }

    // Getter method for ownerId
    public int getOwnerId() {
        return ownerId;  // Return the ownerId
    }

    // Setter method for ownerId
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;  // Set the ownerId
    }

    // Getter method for ownerName
    public String getOwnerName() {
        return ownerName;  // Return the ownerName
    }

    // Setter method for ownerName
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;  // Set the ownerName
    }

    // Getter method for ownerEmailAddress
    public String getOwnerEmailAddress() {
        return ownerEmailAddress;  // Return the ownerEmailAddress
    }

    // Setter method for ownerEmailAddress
    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;  // Set the ownerEmailAddress
    }

    // Getter method for ownerPhone
    public String getOwnerPhone() {
        return ownerPhone;  // Return the ownerPhone
    }

    // Setter method for ownerPhone
    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;  // Set the ownerPhone
    }

    // Getter method for ownerAddress
    public String getOwnerAddress() {
        return ownerAddress;  // Return the ownerAddress
    }

    // Setter method for ownerAddress
    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;  // Set the ownerAddress
    }

    // Constructor to initialize GymOwner with name, email, phone, address, and password
    public GymOwner(String ownerName, String ownerEmailAddress, String ownerPhone, String ownerAddress, String password) {
        this.ownerName = ownerName;  // Set the ownerName
        this.ownerEmailAddress = ownerEmailAddress;  // Set the ownerEmailAddress
        this.ownerPhone = ownerPhone;  // Set the ownerPhone
        this.ownerAddress = ownerAddress;  // Set the ownerAddress
        this.password = password;  // Set the password
    }

    // Constructor to initialize GymOwner with all fields including ownerId
    public GymOwner(int ownerId, String ownerName, String ownerEmailAddress, String ownerPhone, String ownerAddress, String password) {
        this.ownerId = ownerId;  // Set the ownerId
        this.ownerName = ownerName;  // Set the ownerName
        this.ownerEmailAddress = ownerEmailAddress;  // Set the ownerEmailAddress
        this.ownerPhone = ownerPhone;  // Set the ownerPhone
        this.ownerAddress = ownerAddress;  // Set the ownerAddress
        this.password = password;  // Set the password
    }
}
