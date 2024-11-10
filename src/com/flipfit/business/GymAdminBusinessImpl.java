package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.GymAdminDAO;
import com.flipfit.dao.GymAdminDAOImpl;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceNotFoundException;
import com.flipfit.exceptions.StatusUpdatedException;

import java.util.Scanner;
import java.util.*;

public class GymAdminBusinessImpl implements GymAdminBusiness {
    // Scanner instance for user input
    Scanner scanner = new Scanner(System.in);

    // DAO instance to interact with the database layer
    GymAdminDAO adminDAO = new GymAdminDAOImpl();

    /**
     * Fetches all gym bookings from the database.
     * @return a list of GymBooking objects.
     */
    public List<GymBooking> viewBookings() {
        try {
            // Attempt to fetch the bookings from the DAO
            return adminDAO.viewBookings();
        } catch (ResourceNotFoundException e) {
            // Handle exception if no bookings are found
            System.out.println(e);
        }
        // Return an empty list if no bookings are found or an exception occurs
        return new ArrayList<>();
    }

    /**
     * Approves a gym owner's registration request.
     * @param requestId the ID of the registration request.
     * @param statuss the new status to set for the request.
     */
    public void approveOwnerRegistration(int requestId, String statuss) {
        try {
            // Call DAO method to approve the request
            adminDAO.approveOwnerRegistration(requestId, statuss);
        } catch (StatusUpdatedException e) {
            // Handle exception if status cannot be updated
            System.out.println(e);
        } catch (ResourceNotFoundException e) {
            // Handle exception if resource is not found
            System.out.println(e);
        }
    }

    /**
     * Retrieves all pending gym owner registration requests.
     * @return a list of GymOwnerRequest objects.
     */
    public List<GymOwnerRequest> pendingRequests() {
        try {
            // Attempt to fetch the pending requests from DAO
            return adminDAO.pendingRequests();
        } catch (ResourceNotFoundException e) {
            // Handle exception if no requests are found
            System.out.println(e);
        }
        // Return an empty list if no requests are found or an exception occurs
        return new ArrayList<>();
    }

    /**
     * Fetches all gym centers from the database.
     * @return a list of GymCenter objects.
     */
    public List<GymCenter> viewCenter() {
        try {
            // Attempt to fetch the gym centers from DAO
            return adminDAO.viewCenter();
        } catch (ResourceNotFoundException e) {
            // Handle exception if no centers are found
            System.out.println(e);
        }
        // Return an empty list if no centers are found or an exception occurs
        return new ArrayList<>();
    }

    /**
     * Updates the admin's password.
     * @param email the email of the admin.
     * @param password the new password.
     * @param role the role of the user (to verify the admin).
     * @return true if the password was updated successfully, false otherwise.
     */
    @Override
    public boolean updatepwd(String email, String password, String role) {
        try {
            // Attempt to update the password through DAO
            return adminDAO.updatepwd(email, password, role);
        } catch (InvalidCredentialsException e) {
            // Handle exception if credentials are invalid
            System.out.println(e);
        }
        // Return false if an exception occurs or password update fails
        return false;
    }
}
