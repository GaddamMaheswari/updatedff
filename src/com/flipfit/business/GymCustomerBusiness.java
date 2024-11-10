package com.flipfit.business;

import com.flipfit.bean.GymBooking;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymSlots;

import java.util.Date;
import java.util.List;

public interface GymCustomerBusiness {
    // Interface declaration for the services related to Gym Customer operations

    /**
     * Create a new customer profile.
     *
     * @param customer The customer object containing profile details.
     * @return true if the profile is created successfully, false otherwise.
     */
    public boolean createProfile(GymCustomer customer);

    /**
     * Edit an existing customer profile.
     *
     * @param customer The customer object with updated profile details.
     * @return true if the profile is edited successfully, false otherwise.
     */
    public boolean editProfile(GymCustomer customer);

    /**
     * Create a new booking for a customer.
     *
     * @param customerId The ID of the customer making the booking.
     * @param slotid The ID of the slot being booked.
     * @param centerId The ID of the gym center.
     * @param date The date of the booking.
     * @return The booking ID if the booking is created successfully.
     */
    public int createBooking(int customerId, int slotid, int centerId, Date date);

    /**
     * View a list of bookings made by a customer.
     *
     * @param customerId The ID of the customer whose bookings are being viewed.
     * @return A list of bookings associated with the given customer ID.
     */
    public List<GymBooking> viewBookings(int customerId);

    /**
     * Cancel a booking made by a customer.
     *
     * @param customerId The ID of the customer canceling the booking.
     * @param bookingId The ID of the booking being canceled.
     * @return true if the booking is canceled successfully, false otherwise.
     */
    public boolean cancelBooking(int customerId, int bookingId);

    /**
     * Make a payment for a booking.
     *
     * @param bookingId The ID of the booking for which the payment is being made.
     * @param mode The mode of payment (e.g., credit card, PayPal).
     * @return The payment ID if the payment is successful.
     */
    public int makepayment(int bookingId, String mode);

    /**
     * Update the password of a customer.
     *
     * @param email The email address associated with the customer account.
     * @param password The new password.
     * @param role The role of the user (e.g., "customer").
     * @return true if the password is updated successfully, false otherwise.
     */
    public boolean updatepwd(String email, String password, String role);
}
