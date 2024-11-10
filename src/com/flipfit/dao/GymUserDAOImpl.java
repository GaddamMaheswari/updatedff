package com.flipfit.dao;

import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymOwner;
import com.flipfit.exceptions.DBConnectionException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceNotFoundException;
import com.flipfit.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GymUserDAOImpl implements GymUserDAO {

    private Connection conn = null;  // Connection object to interact with the database
    private PreparedStatement statement = null;  // PreparedStatement object to execute SQL queries

    /**
     * Retrieves a list of all gym customers from the database.
     * If no customers are found, it throws a ResourceNotFoundException.
     * @return A list of GymCustomer objects.
     * @throws ResourceNotFoundException if no customers are found in the database.
     */
    @Override
    public List<GymCustomer> viewAllCustomers() throws ResourceNotFoundException {
        List<GymCustomer> customers = new ArrayList<>();  // List to hold all customers
        try {
            // Establishing a connection to the database
            conn = DBConnection.connect();

            // Preparing the SQL query to select all customers
            statement = conn.prepareStatement("Select * from Customer");
            ResultSet rs = statement.executeQuery();  // Executing the query and storing the result

            // Looping through the result set and adding each customer to the list
            while (rs.next()) {
                customers.add(new GymCustomer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                // Uncomment the line below to print customer details (for debugging)
                // System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }

            // If the list is empty, throw ResourceNotFoundException
            if (customers.isEmpty()) {
                throw new ResourceNotFoundException("No customers found");
            }

        } catch (SQLException se) {
            // Catching and printing SQL exceptions
            se.printStackTrace();
        } catch (Exception e) {
            // Catching and printing any other exceptions
            e.printStackTrace();
        }
        return customers;  // Returning the list of customers
    }

    /**
     * Retrieves a list of all gym owners from the database.
     * If no gym owners are found, it throws a ResourceNotFoundException.
     * @return A list of GymOwner objects.
     * @throws ResourceNotFoundException if no gym owners are found in the database.
     */
    @Override
    public List<GymOwner> viewAllGymOwners() throws ResourceNotFoundException {
        List<GymOwner> owners = new ArrayList<>();  // List to hold all gym owners
        try {
            // Establishing a connection to the database
            conn = DBConnection.connect();

            // Preparing the SQL query to select all gym owners
            statement = conn.prepareStatement("Select * from OwnerInfo");
            ResultSet rs = statement.executeQuery();  // Executing the query and storing the result

            // Looping through the result set and adding each gym owner to the list
            while (rs.next()) {
                owners.add(new GymOwner(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6)));
                // Uncomment the line below to print owner details (for debugging)
                // System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }

            // If the list is empty, throw ResourceNotFoundException
            if (owners.isEmpty()) {
                throw new ResourceNotFoundException("No owner found");
            }

        } catch (SQLException se) {
            // Catching and printing SQL exceptions
            se.printStackTrace();
        } catch (Exception e) {
            // Catching and printing any other exceptions
            e.printStackTrace();
        }
        return owners;  // Returning the list of gym owners
    }

    /**
     * Validates the login credentials for a user and returns the user ID if the credentials are correct.
     * If the credentials are invalid, it throws an InvalidCredentialsException.
     * @param email The user's email address.
     * @param password The user's password.
     * @param role The user's role (either "customer" or "gymowner").
     * @return The user ID if login is successful.
     * @throws InvalidCredentialsException if the credentials are invalid.
     * @throws DBConnectionException if there is a problem with the database connection.
     */
    @Override
    public int login(String email, String password, String role) throws InvalidCredentialsException, DBConnectionException {
        try {
            // Establishing a connection to the database
            conn = DBConnection.connect();

            // Preparing the SQL query to validate login credentials
            statement = conn.prepareStatement("select * from registration where EmailAddress = ? and Password = ? and Role = ?");
            statement.setString(1, email);  // Setting the email in the query
            statement.setString(2, password);  // Setting the password in the query
            statement.setString(3, role);  // Setting the role in the query

            // Executing the query and checking if a matching result is found
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // If credentials are valid, return the user ID
                return resultSet.getInt("UserId");
            } else {
                // If credentials are invalid, throw InvalidCredentialsException
                throw new InvalidCredentialsException("Login Failed, Check your Credentials Again !!");
            }

        } catch (SQLException se) {
            // Catching and printing SQL exceptions
            se.printStackTrace();
        }
        return -1;  // Returning -1 if login failed
    }
}
