package com.flipfit.business;

import com.flipfit.bean.GymAdmin;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymOwner;
import com.flipfit.dao.GymUserDAO;
import com.flipfit.dao.GymUserDAOImpl;
import com.flipfit.exceptions.DBConnectionException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class GymUserBusinessImpl implements GymUserBusiness {

    // DAO instance to interact with the database
    GymUserDAO gymUserDAO = new GymUserDAOImpl();

    /**
     * Retrieves a list of all customers by calling the DAO.
     * If no customers are found, it handles the ResourceNotFoundException and returns an empty list.
     * @return A list of GymCustomer objects.
     */
    @Override
    public List<GymCustomer> viewAllCustomers() {
        try {
            // Fetching list of customers from the DAO
            return gymUserDAO.viewAllCustomers();
        } catch (ResourceNotFoundException e) {
            // Handling case when no resources are found
            System.out.println(e);
        }
        // Returning an empty list if exception occurs
        return new ArrayList();
    }

    /**
     * Retrieves a list of all gym owners by calling the DAO.
     * If no gym owners are found, it handles the ResourceNotFoundException and returns an empty list.
     * @return A list of GymOwner objects.
     */
    @Override
    public List<GymOwner> viewAllGymOwners() {
        try {
            // Fetching list of gym owners from the DAO
            return gymUserDAO.viewAllGymOwners();
        } catch (ResourceNotFoundException e) {
            // Handling case when no resources are found
            System.out.println(e);
        }
        // Returning an empty list if exception occurs
        return new ArrayList();
    }

    /**
     * Validates user login credentials by calling the DAO.
     * If the credentials are invalid or there is a database connection error, it returns -1.
     * @param email The user's email address.
     * @param password The user's password.
     * @param role The user's role (either "customer" or "gymowner").
     * @return The user ID if login is successful, otherwise -1.
     */
    @Override
    public int login(String email, String password, String role) {
        try {
            // Calling DAO to verify login credentials and returning user role identifier
            return gymUserDAO.login(email, password, role);
        } catch (InvalidCredentialsException inex) {
            // Handling invalid credentials exception
            System.out.println(inex);
        } catch (DBConnectionException dbex){
            // Handling database connection exception
            System.out.println(dbex);
        }
        // Returning -1 if login fails
        return -1;
    }
}
