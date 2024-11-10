package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.GymSlots;

import java.time.LocalTime;

public interface GymOwnerBusiness {

    // Registers a new gym center for the specified owner
    // @param ownerId - the ID of the gym owner
    // @param centerName - the name of the gym center
    // @param location - the location of the gym center
    // @param slots - the number of slots available at the center
    public boolean registerCenter(int ownerId, String centerName, String location, int slots);

    // Adds a new slot to an existing gym center
    // @param centerId - the ID of the gym center to which the slot is added
    // @param slot - the GymSlots object containing details of the new slot (start time, end time, cost)
    public boolean addnewSlot(int centerId, GymSlots slot);

    // Deletes an existing slot from a gym center based on start time
    // @param centerId - the ID of the gym center
    // @param startTime - the start time of the slot to be deleted
    public boolean deleteSlot(int centerId, LocalTime startTime);

    // Deletes a gym center
    // @param centerId - the ID of the gym center to be deleted
    public boolean deleteCenter(int centerId);

    // Allows the gym owner to edit their profile details
    // @param owner - the GymOwner object containing the updated profile information
    public boolean editProfile(GymOwner owner);

    // Allows the gym owner to create a new profile
    // @param owner - the GymOwner object containing the profile details to be created
    public boolean createProfile(GymOwner owner);

    // Updates the password for the gym owner
    // @param email - the email of the gym owner
    // @param password - the new password to set
    // @param role - the role of the user (could be used for additional permission handling)
    public boolean updatepwd(String email, String password, String role);

}
