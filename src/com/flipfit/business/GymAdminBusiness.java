package com.flipfit.business;

import com.flipfit.bean.*;

import java.util.*;

public interface GymAdminBusiness {
    // Method to retrieve all the gym bookings
    // Returns a list of GymBooking objects
    public List<GymBooking> viewBookings();

    // Method to approve a gym owner's registration
    // Takes requestId to identify the registration request and statuss to approve or reject the request
    // No return value
    public void approveOwnerRegistration(int requestId, String statuss);

    // Method to get all pending gym owner registration requests
    // Returns a list of GymOwnerRequest objects
    public List<GymOwnerRequest> pendingRequests();

    // Method to view the gym centers managed by the admin
    // Returns a list of GymCenter objects
    public List<GymCenter> viewCenter();

    // Method to update the admin's password
    // Takes email for identification, new password, and the role of the user (admin or other)
    // Returns true if the update is successful, false otherwise
    public boolean updatepwd(String email, String password, String role);
}
