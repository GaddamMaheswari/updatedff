package com.flipfit.exceptions;

// Custom exception class to handle invalid credentials scenarios
public class InvalidCredentialsException extends Exception {

    // Constructor that accepts a message to describe the exception
    public InvalidCredentialsException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }

    // Constructor that accepts both a message and a cause (another throwable)
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);  // Passing both the message and cause to the superclass
    }
}
