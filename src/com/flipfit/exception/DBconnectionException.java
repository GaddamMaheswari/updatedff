package com.flipfit.exception;

// Custom exception class to handle database connection errors
public class DBconnectionException extends Exception {

    // Constructor that accepts a message
    public DBconnectionException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }

    // Constructor that accepts both a message and a cause (another throwable)
    public DBconnectionException(String message, Throwable cause) {
        super(message, cause);  // Passing both the message and cause to the superclass
    }
}
