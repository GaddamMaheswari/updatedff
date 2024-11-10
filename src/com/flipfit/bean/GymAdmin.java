package com.flipfit.bean;

public class GymAdmin {
    private int adminId;  // Stores the admin ID
    private String adminName;  // Stores the admin's name
    private String adminEmailAddress;  // Stores the admin's email address
    private int phone;  // Stores the admin's phone number
    private String password;  // Stores the admin's password

    // Getter method for phone
    public int getAdminPhone() {
        return phone;  // Return the admin's phone number
    }

    // Setter method for phone
    public void setAdminPhone(int phone) {
        this.phone = phone;  // Set the admin's phone number
    }

    // Getter method for password
    public String getPassword() {
        return password;  // Return the admin's password
    }

    // Setter method for password
    public void setPassword(String password) {
        this.password = password;  // Set the admin's password
    }

    // Getter method for adminId
    public int getAdminId() {
        return adminId;  // Return the admin ID
    }

    // Setter method for adminId
    public void setAdminId(int adminId) {
        this.adminId = adminId;  // Set the admin ID
    }

    // Getter method for adminName
    public String getAdminName() {
        return adminName;  // Return the admin's name
    }

    // Setter method for adminName
    public void setAdminName(String adminName) {
        this.adminName = adminName;  // Set the admin's name
    }

    // Getter method for adminEmailAddress
    public String getAdminEmailAddress() {
        return adminEmailAddress;  // Return the admin's email address
    }

    // Setter method for adminEmailAddress
    public void setAdminEmailAddress(String adminEmailAddress) {
        this.adminEmailAddress = adminEmailAddress;  // Set the admin's email address
    }
}
