package com.flipfit.dao;

import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.GymSlots;
import com.flipfit.exceptions.DataEntryFailedException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceAlreadyExistsException;
import java.time.LocalTime;

public interface GymOwnerDAO {

    // Creates a new gym owner profile
    public boolean createProfile(GymOwner gymOwner) throws InvalidCredentialsException, DataEntryFailedException;

    // Registers a new gym center for the owner
    public boolean registerCenter(int ownerId, String centerName, String location, int slots) throws DataEntryFailedException;

    // Adds new slots to an existing gym center
    public boolean addSlots(int centerID, GymSlots slot) throws ResourceAlreadyExistsException, DataEntryFailedException;

    // Deletes a specific gym slot from a gym center
    public boolean deleteSlot(int centerID, LocalTime starttime) throws DataEntryFailedException;

    // Deletes a gym center
    public boolean deleteCenter(int centerId) throws DataEntryFailedException;

    // Edits an existing gym owner profile
    public boolean editProfile(GymOwner gymOwner) throws DataEntryFailedException;

    // Updates the gym owner's password
    public boolean updatepwd(String email, String password, String role) throws InvalidCredentialsException;
}
