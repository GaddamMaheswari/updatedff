package com.flipfit.bean;

public class GymCustomer {
    // Properties of the GymCustomer class with their respective getters and setters

    // Unique ID for the customer
    private int customerId;
    // Name of the customer
    private String customerName;
    // Address of the customer
    private String customerAddress;
    // Email address of the customer
    private String customerEmailAddress;
    // Phone number of the customer
    private String customerPhone;
    // Password for customer authentication
    private String password;

    // Getter method for password
    public String getPassword() {
        return password;  // Return the password
    }

    // Setter method for password
    public void setPassword(String password) {
        this.password = password;  // Set the password
    }

    // Getter method for customerId
    public int getCustomerId() {
        return customerId;  // Return the customerId
    }

    // Setter method for customerId
    public void setCustomerId(int customerId) {
        this.customerId = customerId;  // Set the customerId
    }

    // Getter method for customerName
    public String getCustomerName() {
        return customerName;  // Return the customerName
    }

    // Setter method for customerName
    public void setCustomerName(String customerName) {
        this.customerName = customerName;  // Set the customerName
    }

    // Getter method for customerAddress
    public String getCustomerAddress() {
        return customerAddress;  // Return the customerAddress
    }

    // Setter method for customerAddress
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;  // Set the customerAddress
    }

    // Getter method for customerEmailAddress
    public String getCustomerEmailAddress() {
        return customerEmailAddress;  // Return the customerEmailAddress
    }

    // Setter method for customerEmailAddress
    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;  // Set the customerEmailAddress
    }

    // Getter method for customerPhone
    public String getCustomerPhone() {
        return customerPhone;  // Return the customerPhone
    }

    // Setter method for customerPhone
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;  // Set the customerPhone
    }

    // Constructor to initialize GymCustomer with name, address, email, phone, and password
    public GymCustomer(String name, String address, String email, String phone, String password) {
        this.customerName = name;  // Set the customerName
        this.customerAddress = address;  // Set the customerAddress
        this.customerEmailAddress = email;  // Set the customerEmailAddress
        this.customerPhone = phone;  // Set the customerPhone
        this.password = password;  // Set the password
    }

    // Constructor to initialize GymCustomer with all fields including customerId
    public GymCustomer(int customerId, String customerName, String customerAddress, String customerEmailAddress, String customerPhone, String password) {
        this.customerId = customerId;  // Set the customerId
        this.customerName = customerName;  // Set the customerName
        this.customerAddress = customerAddress;  // Set the customerAddress
        this.customerEmailAddress = customerEmailAddress;  // Set the customerEmailAddress
        this.customerPhone = customerPhone;  // Set the customerPhone
        this.password = password;  // Set the password
    }
}
