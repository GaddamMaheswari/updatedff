package com.flipfit.exceptions;

// Custom exception class to handle unauthorized access scenarios
public class UnauthorisedAccessException extends Exception {

    // Constructor that accepts a message to describe the exception
    public UnauthorisedAccessException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }

    // Constructor that accepts a message and a cause for the exception
    public UnauthorisedAccessException(String message, Throwable cause) {
        super(message, cause);  // Passing the message and cause to the superclass (Exception)
    }
}
