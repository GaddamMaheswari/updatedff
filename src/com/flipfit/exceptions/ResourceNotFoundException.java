package com.flipfit.exceptions;

// Custom exception class to handle scenarios where a resource is not found
public class ResourceNotFoundException extends Exception {

    // Constructor that accepts a message to describe the exception
    public ResourceNotFoundException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }
}
