package com.flipfit.business;

import com.flipfit.bean.GymBooking;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymPayment;
import com.flipfit.dao.GymCustomerDAO;
import com.flipfit.dao.GymCustomerDAOImpl;
import com.flipfit.exceptions.DataEntryFailedException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceNotFoundException;
import com.flipfit.exceptions.UnauthorisedAccessException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GymCustomerBusinessImpl implements GymCustomerBusiness {
    Scanner scanner = new Scanner(System.in);
    GymCustomerDAO custDAO = new GymCustomerDAOImpl();

    /**
     * Creates a new profile for a gym customer.
     * This method adds a new customer profile to the system.
     * @param customer - the GymCustomer object containing the profile details to be created
     * @return true if the profile is created successfully, otherwise false
     */
    @Override
    public boolean createProfile(GymCustomer customer) {
        try {
            return custDAO.createProfile(customer);  // Create customer profile
        } catch (InvalidCredentialsException | DataEntryFailedException e) {
            System.out.println(e);
        }
        return false;  // Return false if creation fails
    }

    /**
     * Edits the profile of an existing gym customer.
     * This method allows customers to update their profile information.
     * @param customer - the GymCustomer object containing the updated profile details
     * @return true if the profile is edited successfully, otherwise false
     */
    @Override
    public boolean editProfile(GymCustomer customer) {
        try {
            return custDAO.editProfile(customer);  // Edit customer profile
        } catch (DataEntryFailedException e) {
            System.out.println(e);
        }
        return false;  // Return false if editing fails
    }

    /**
     * Creates a new booking for the customer.
     * The method books a slot at a specific gym center on a specific date.
     * @param customerId - the ID of the customer making the booking
     * @param slotid - the ID of the slot to be booked
     * @param centerId - the ID of the gym center where the booking is made
     * @param date - the date of the booking
     * @return the booking ID if successful, otherwise -1
     */
    @Override
    public int createBooking(int customerId, int slotid, int centerId, Date date) {
        try {
            return custDAO.createBooking(customerId, slotid, centerId, date);  // Create a booking
        } catch (ResourceNotFoundException e) {
            System.out.println(e);
        }
        return -1;  // Return -1 if the booking creation fails
    }

    /**
     * Views all bookings made by a specific customer.
     * This method retrieves and returns all bookings associated with the customer.
     * @param customerId - the ID of the customer whose bookings are to be viewed
     * @return a list of GymBooking objects containing the customer's bookings, or an empty list if none are found
     */
    @Override
    public List<GymBooking> viewBookings(int customerId) {
        try {
            return custDAO.viewBookings(customerId);  // View all bookings for a specific customer
        } catch (ResourceNotFoundException e) {
            System.out.println(e);
        }
        return new ArrayList<>();  // Return an empty list if no bookings are found
    }

    /**
     * Cancels an existing booking made by the customer.
     * This method allows the customer to cancel a specific booking.
     * @param customerId - the ID of the customer who is canceling the booking
     * @param bookingId - the ID of the booking to be canceled
     * @return true if the booking is canceled successfully, otherwise false
     */
    @Override
    public boolean cancelBooking(int customerId, int bookingId) {
        try {
            return custDAO.cancelBooking(customerId, bookingId);  // Cancel a booking for a customer
        } catch (InvalidCredentialsException | UnauthorisedAccessException e) {
            System.out.println(e);
        }
        return false;  // Return false if the cancellation fails
    }

    /**
     * Processes the payment for a booking.
     * This method handles the payment for the specified booking using the given payment mode.
     * @param bookingId - the ID of the booking for which the payment is being made
     * @param mode - the mode of payment (e.g., Credit Card, Debit Card, etc.)
     * @return the payment ID if successful, otherwise -1
     */
    @Override
    public int makepayment(int bookingId, String mode) {
        GymPayment gymPayment = new GymPayment(bookingId, mode);
        try {
            return custDAO.makepayment(gymPayment);  // Process payment for a booking
        } catch (DataEntryFailedException e) {
            System.out.println(e);
        }
        return -1;  // Return -1 if payment processing fails
    }

    /**
     * Updates the password for the customer.
     * This method allows the customer to change their password.
     * @param email - the email of the customer
     * @param password - the new password to set
     * @param role - the role of the user (could be used for additional permission handling)
     * @return true if the password is updated successfully, otherwise false
     */
    @Override
    public boolean updatepwd(String email, String password, String role) {
        try {
            return custDAO.updatepwd(email, password, role);  // Update customer password
        } catch (InvalidCredentialsException e) {
            System.out.println(e);
        }
        return false;  // Return false if the password update fails
    }
}
