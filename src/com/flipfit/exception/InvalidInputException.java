package com.flipfit.exception;

// Custom exception class to handle invalid input errors
public class InvalidInputException extends Exception {

    // Constructor that accepts a message
    public InvalidInputException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }

    // Constructor that accepts both a message and a cause (another throwable)
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);  // Passing both the message and cause to the superclass
    }
}
