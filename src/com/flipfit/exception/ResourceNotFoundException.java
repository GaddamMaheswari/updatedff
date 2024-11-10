package com.flipfit.exception;

// Custom exception class to handle scenarios where a requested resource is not found
public class ResourceNotFoundException extends Exception {

    // Constructor that accepts a message to describe the exception
    public ResourceNotFoundException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }

    // Constructor that accepts both a message and a cause (another throwable)
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);  // Passing both the message and cause to the superclass
    }
}
