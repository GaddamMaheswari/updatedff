package com.flipfit.exceptions;

// Custom exception class to handle scenarios where the status has been updated
public class StatusUpdatedException extends Exception {

    // Constructor that accepts a message to describe the exception
    public StatusUpdatedException(String message) {
        super(message);  // Passing the message to the superclass (Exception)
    }
}
