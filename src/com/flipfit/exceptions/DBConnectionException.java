package com.flipfit.exceptions;

// Custom exception class to handle database connection failure scenarios
public class DBConnectionException extends Exception {

    // Constructor that accepts a message to describe the exception
    public DBConnectionException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }

    // Constructor that accepts both a message and a cause (another throwable)
    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);  // Passing both the message and cause to the superclass
    }
}
