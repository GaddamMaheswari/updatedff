package com.flipfit.exceptions;

// Custom exception class to handle scenarios where a resource already exists
public class ResourceAlreadyExistsException extends Exception {

    // Constructor that accepts a message to describe the exception
    public ResourceAlreadyExistsException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }
}
