package com.flipfit.utils;

import com.flipfit.exceptions.DBConnectionException;  // Importing custom exception for DB connection errors
// import com.flipfit.exception.DBconnectionException;  // Alternative import for DBConnectionException (from a different package)

import java.sql.Connection;  // Importing SQL Connection class for database connection
import java.sql.DriverManager;  // Importing DriverManager class to manage database drivers
import java.sql.SQLException;  // Importing SQLException class for handling SQL errors

public class DBConnection {

    // Method to establish a connection to the database
    public static Connection connect() throws SQLException, DBConnectionException {  // Throws SQLException and custom DBConnectionException

        try {
            // Loading the MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection credentials
            String password = "Password123";  // Enter your password here

            // Establishing the connection to the database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/GymCustomer",  // Database URL
                    "root",  // Username
                    "sd@2801$");  // Password

            System.out.println("Database Connected");  // Successful connection message
            return con;  // Returning the connection object

        } catch (Exception e) {  // Catching any exceptions that occur during the connection process

            // If an exception occurs, print a failure message and throw a custom DBConnectionException
            System.out.println("Database Not Connected");
            throw new DBConnectionException("Failed to connect to the database: " + e.getMessage());  // Throwing the custom exception with the error message

        }
    }
}
