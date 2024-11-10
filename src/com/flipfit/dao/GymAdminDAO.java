package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceNotFoundException;
import com.flipfit.exceptions.StatusUpdatedException;

import java.util.*;

public interface GymAdminDAO {

        /**
         * Method to retrieve all bookings in the system.
         *
         * @return List of GymBooking objects.
         * @throws ResourceNotFoundException if no bookings are found.
         */
        public List<GymBooking> viewBookings() throws ResourceNotFoundException;

        /**
         * Method to approve or reject a gym owner registration request.
         *
         * @param requestId The ID of the registration request.
         * @param status The status of the registration (approved/rejected).
         * @throws StatusUpdatedException if the status could not be updated.
         * @throws ResourceNotFoundException if the registration request is not found.
         */
        public void approveOwnerRegistration(int requestId, String status) throws StatusUpdatedException, ResourceNotFoundException;

        /**
         * Method to retrieve all pending owner registration requests.
         *
         * @return List of GymOwnerRequest objects.
         * @throws ResourceNotFoundException if no pending requests are found.
         */
        public List<GymOwnerRequest> pendingRequests() throws ResourceNotFoundException;

        /**
         * Method to retrieve all gym centers in the system.
         *
         * @return List of GymCenter objects.
         * @throws ResourceNotFoundException if no gym centers are found.
         */
        public List<GymCenter> viewCenter() throws ResourceNotFoundException;

        /**
         * Method to update the password for a user (Admin or Owner).
         *
         * @param email The email of the user.
         * @param password The new password to be set.
         * @param role The role of the user (Admin/Owner/Customer).
         * @return boolean indicating whether the password update was successful.
         * @throws InvalidCredentialsException if the provided credentials are invalid.
         */
        public boolean updatepwd(String email, String password, String role) throws InvalidCredentialsException;
}
