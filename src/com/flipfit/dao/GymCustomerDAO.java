package com.flipfit.dao;

import com.flipfit.bean.GymBooking;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymPayment;
import com.flipfit.exceptions.DataEntryFailedException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceNotFoundException;
import com.flipfit.exceptions.UnauthorisedAccessException;

import java.util.*;

public interface GymCustomerDAO {

    // Method to create a new customer profile
    public boolean createProfile(GymCustomer customer) throws InvalidCredentialsException, DataEntryFailedException;

    // Method to edit an existing customer profile
    public boolean editProfile(GymCustomer customer) throws DataEntryFailedException;

    // Method to view all bookings made by a customer
    public List<GymBooking> viewBookings(int customerId) throws ResourceNotFoundException;

    // Method to create a new booking for a customer
    public int createBooking(int customerID, int slotID, int centerId, Date date) throws ResourceNotFoundException;

    // Method to cancel an existing booking made by a customer
    public boolean cancelBooking(int customerId, int bookingID) throws InvalidCredentialsException, UnauthorisedAccessException;

    // Method to process a payment for a booking
    public int makepayment(GymPayment paymentData) throws DataEntryFailedException;

    // Method to update customer password
    public boolean updatepwd(String email, String password, String role) throws InvalidCredentialsException;
}
