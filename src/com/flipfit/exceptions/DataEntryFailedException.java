package com.flipfit.exceptions;

// Custom exception class to handle data entry failure scenarios
public class DataEntryFailedException extends Exception {

    // Constructor that accepts a message to describe the exception
    public DataEntryFailedException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }

    // Constructor that accepts both a message and a cause (another throwable)
    public DataEntryFailedException(String message, Throwable cause) {
        super(message, cause);  // Passing both the message and cause to the superclass
    }
}
