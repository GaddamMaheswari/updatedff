package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.GymSlots;
import com.flipfit.dao.GymAdminDAO;
import com.flipfit.dao.GymAdminDAOImpl;
import com.flipfit.dao.GymOwnerDAO;
import com.flipfit.dao.GymOwnerDAOImpl;
import com.flipfit.exceptions.DataEntryFailedException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceAlreadyExistsException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class GymOwnerBusinessImpl implements GymOwnerBusiness {

    // Create an instance of Scanner for input, and GymOwnerDAO for database interaction
    Scanner scanner = new Scanner(System.in);
    GymOwnerDAO ownerDAO = new GymOwnerDAOImpl();

    /**
     * Registers a new gym center for the specified owner.
     * This involves creating a new gym center record in the database with the given details.
     * @param ownerId - the ID of the gym owner
     * @param centerName - the name of the gym center
     * @param location - the location of the gym center
     * @param slots - the number of slots available at the center
     * @return true if the registration is successful, otherwise false
     */
    @Override
    public boolean registerCenter(int ownerId, String centerName, String location, int slots) {
        try {
            return ownerDAO.registerCenter(ownerId, centerName, location, slots);
        } catch (DataEntryFailedException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Adds a new slot to an existing gym center.
     * The new slot will be added with the provided details such as start time, end time, and cost.
     * @param centerId - the ID of the gym center to which the slot is added
     * @param slot - the GymSlots object containing details of the new slot (start time, end time, cost)
     * @return true if the slot is added successfully, otherwise false
     */
    @Override
    public boolean addnewSlot(int centerId, GymSlots slot) {
        try {
            ownerDAO.addSlots(centerId, slot);
        } catch (ResourceAlreadyExistsException e) {
            System.out.println(e);
        } catch (DataEntryFailedException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Deletes an existing slot from a gym center based on start time.
     * If the slot with the given start time exists, it will be removed.
     * @param centerId - the ID of the gym center
     * @param startTime - the start time of the slot to be deleted
     * @return true if the slot is deleted successfully, otherwise false
     */
    @Override
    public boolean deleteSlot(int centerId, LocalTime startTime) {
        try {
            return ownerDAO.deleteSlot(centerId, startTime);
        } catch (DataEntryFailedException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Deletes a gym center from the database.
     * The center will be removed along with all associated slots and data.
     * @param centerId - the ID of the gym center to be deleted
     * @return true if the center is deleted successfully, otherwise false
     */
    @Override
    public boolean deleteCenter(int centerId) {
        try {
            return ownerDAO.deleteCenter(centerId);
        } catch (DataEntryFailedException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Allows the gym owner to edit their profile details.
     * The profile information such as name, email, and other details are updated.
     * @param owner - the GymOwner object containing the updated profile information
     * @return true if the profile is updated successfully, otherwise false
     */
    @Override
    public boolean editProfile(GymOwner owner) {
        try {
            return ownerDAO.editProfile(owner);
        } catch (DataEntryFailedException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Allows the gym owner to create a new profile.
     * This method registers a new gym owner in the system with the provided profile details.
     * @param owner - the GymOwner object containing the profile details to be created
     * @return true if the profile is created successfully, otherwise false
     */
    @Override
    public boolean createProfile(GymOwner owner) {
        try {
            return ownerDAO.createProfile(owner);
        } catch (DataEntryFailedException e) {
            System.out.println(e);
        } catch (InvalidCredentialsException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Updates the password for the gym owner.
     * The new password is set for the gym owner based on the provided email and role.
     * @param email - the email of the gym owner
     * @param password - the new password to set
     * @param role - the role of the user (could be used for additional permission handling)
     * @return true if the password is updated successfully, otherwise false
     */
    @Override
    public boolean updatepwd(String email, String password, String role) {
        try {
            return ownerDAO.updatepwd(email, password, role);
        } catch (InvalidCredentialsException e) {
            System.out.println(e);
        }
        return false;
    }
}
