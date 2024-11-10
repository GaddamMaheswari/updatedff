package com.flipfit.dao;

import com.flipfit.bean.GymOwner;
import com.flipfit.bean.GymSlots;
import com.flipfit.exceptions.DBConnectionException;
import com.flipfit.exceptions.DataEntryFailedException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceAlreadyExistsException;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GymOwnerDAOImpl implements GymOwnerDAO {
    private Connection connection = null; // Connection object to interact with the database
    private PreparedStatement statement = null; // PreparedStatement for executing SQL queries

    /**
     * Creates a profile for the gym owner.
     * It checks if the gym owner's email address already exists,
     * and if not, adds the owner's details to the `User`, `OwnerInfo`,
     * and `Registration` tables.
     * @param gymOwner The GymOwner object containing the gym owner's details.
     * @return boolean indicating success or failure of profile creation.
     * @throws InvalidCredentialsException If the user already exists with the given email address.
     * @throws DataEntryFailedException If there is an error during database insertion.
     */
    @Override
    public boolean createProfile(GymOwner gymOwner) throws InvalidCredentialsException, DataEntryFailedException {
        try {
            // Establishing database connection
            connection = DBConnection.connect();

            // Checking if the user already exists with the given email address
            statement = connection.prepareStatement("SELECT * from Registration where EmailAddress = ?");
            statement.setString(1, gymOwner.getOwnerEmailAddress());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                throw new InvalidCredentialsException("User already exists with this email address");
            }

            // Adding user profile to the database
            System.out.println("Adding User Profile");
            statement = connection.prepareStatement("insert into User(`Name`,`Email`,`PhoneNumber`,`Role`,`Address`) values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, gymOwner.getOwnerName());
            statement.setString(2, gymOwner.getOwnerEmailAddress());
            statement.setString(3, gymOwner.getOwnerPhone());
            statement.setString(4, "gymowner");
            statement.setString(5, gymOwner.getOwnerAddress());
            int rowsAffected = statement.executeUpdate();
            int ownerId = 0;
            if (rowsAffected > 0) {
                // Retrieve the generated ownerId if the profile insertion is successful
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    ownerId = generatedKeys.getInt(1);
                }
            } else {
                throw new DataEntryFailedException("Failed Adding User Details to User Database");
            }

            // Inserting gym owner information into the OwnerInfo table
            statement = connection.prepareStatement("insert into OwnerInfo values (?,?,?,?,?,?)");
            statement.setInt(1, ownerId);
            statement.setString(2, gymOwner.getOwnerName());
            statement.setString(3, gymOwner.getOwnerEmailAddress());
            statement.setString(4, gymOwner.getOwnerAddress());
            statement.setString(5, gymOwner.getOwnerPhone());
            statement.setString(6, gymOwner.getPassword());
            statement.executeUpdate();

            // Inserting registration information into the Registration table
            statement = connection.prepareStatement("insert into Registration values (?,?,?,?)");
            statement.setInt(1, ownerId);
            statement.setString(2, gymOwner.getOwnerEmailAddress());
            statement.setString(3, gymOwner.getPassword());
            statement.setString(4, "gymowner");
            statement.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Registers a new gym center by adding the details into the `OwnerRequest` table.
     * The owner must first have a profile created in the system.
     * @param ownerId The owner ID.
     * @param centerName The name of the gym center.
     * @param location The location of the gym center.
     * @param slots The number of available slots for the center.
     * @return boolean indicating success or failure of the registration.
     * @throws DataEntryFailedException If the data entry fails in the database.
     */
    @Override
    public boolean registerCenter(int ownerId, String centerName, String location, int slots) throws DataEntryFailedException {
        try {
            // Establishing database connection
            connection = DBConnection.connect();

            // Inserting gym center registration request into OwnerRequest table
            statement = connection.prepareStatement("insert into OwnerRequest(`OwnerId`,`CenterName`,`CenterLocation`,`NumOfSlots`) values (?,?,?,?)");
            statement.setInt(1, ownerId);
            statement.setString(2, centerName);
            statement.setString(3, location);
            statement.setInt(4, slots);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected <= 0) {
                throw new DataEntryFailedException("Data Entry Failed into Owner Request Database");
            } else {
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Adds a new slot to a gym center.
     * Checks if the slot already exists and inserts the slot into the `Slots` table.
     * @param centerID The ID of the gym center.
     * @param slot The GymSlots object containing the slot details.
     * @return boolean indicating success or failure of slot addition.
     * @throws ResourceAlreadyExistsException If the slot already exists for the given center and timings.
     * @throws DataEntryFailedException If the data entry fails during insertion into the database.
     */
    @Override
    public boolean addSlots(int centerID, GymSlots slot) throws ResourceAlreadyExistsException, DataEntryFailedException {
        // Check if the slot already exists for the given gym center
        if (isSlotExists(centerID, slot)) {
            throw new ResourceAlreadyExistsException("Slot already exists for the given GymCenter, and given timings.");
        }

        // SQL query for adding a new slot
        String sql = "INSERT INTO Slots(`CenterId`,`StartTime`,`EndTime`,`NumOfSeats`,`Cost`) VALUES (?,?,?,?,?)";

        try {
            // Establishing database connection
            connection = DBConnection.connect();

            // Preparing and executing the SQL statement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, centerID); // Assuming slot.getSlotID() retrieves the slot ID
            statement.setTime(2, Time.valueOf(slot.getStartTime())); // Assuming slot.getStarttime() returns LocalDateTime
            statement.setTime(3, Time.valueOf(slot.getEndTime())); // Assuming slot.getEndTime() returns a LocalTime object
            statement.setInt(4, slot.getTotalSeats());
            statement.setInt(5, slot.getCost()); // Assuming gymCenter.getGymID() retrieves the gymID

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted <= 0) {
                throw new DataEntryFailedException("Failed to add slot");
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Checks if a slot already exists in the system for a given gym center.
     * @param centerID The ID of the gym center.
     * @param slot The GymSlots object containing the slot details.
     * @return boolean indicating whether the slot exists or not.
     */
    public boolean isSlotExists(int centerID, GymSlots slot) {
        // SQL query to check if the slot already exists in the Slots table
        String sql = "SELECT COUNT(*) AS count FROM Slots WHERE centerID = ? AND starttime = ? AND endtime = ?";
        try {
            connection = DBConnection.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, centerID);
            statement.setTime(2, Time.valueOf(slot.getStartTime())); // Assuming slot.getStarttime() returns LocalDateTime
            statement.setTime(3, Time.valueOf(slot.getEndTime())); //

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Deletes a slot from the gym center by its start time.
     * @param centerID The ID of the gym center.
     * @param starttime The start time of the slot to be deleted.
     * @return boolean indicating success or failure of the slot deletion.
     * @throws DataEntryFailedException If the slot cannot be deleted from the database.
     */
    @Override
    public boolean deleteSlot(int centerID, LocalTime starttime) throws DataEntryFailedException {
        // SQL query to delete a slot from the Slots table
        String sql = "DELETE FROM Slots WHERE centerID = ? AND starttime = ?";

        try {
            // Establishing database connection
            connection = DBConnection.connect();

            // Preparing and executing the SQL statement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, centerID);
            statement.setTime(2, Time.valueOf(starttime));
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new DataEntryFailedException("Failed to delete the slot");
            } else {
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Deletes a gym center from the system based on its ID.
     * @param centerID The ID of the gym center to be deleted.
     * @return boolean indicating success or failure of the deletion.
     * @throws DataEntryFailedException If the gym center cannot be deleted.
     */
    @Override
    public boolean deleteCenter(int centerID) throws DataEntryFailedException {
        try {
            // SQL query to delete a gym center from the GymCenters table
            String sql = "DELETE FROM GymCenters WHERE CenterID = ?";
            connection = DBConnection.connect();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, centerID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected <= 0) {
                throw new DataEntryFailedException("Failed to delete the gym center");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Edits the profile of a gym owner.
     * Updates the owner information in the `OwnerInfo` table.
     * @param gymOwner The GymOwner object containing the updated details.
     * @return boolean indicating success or failure of the profile update.
     */
    @Override
    public boolean editProfile(GymOwner gymOwner) throws DataEntryFailedException {
        try {
            // Establishing database connection
            connection = DBConnection.connect();

            // SQL query to update the owner's profile in the OwnerInfo table
            statement = connection.prepareStatement("UPDATE OwnerInfo SET OwnerName = ?, OwnerAddress = ?, OwnerPhone = ? WHERE OwnerId = ?");
            statement.setString(1, gymOwner.getOwnerName());
            statement.setString(2, gymOwner.getOwnerAddress());
            statement.setString(3, gymOwner.getOwnerPhone());
            statement.setInt(4, gymOwner.getOwnerId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated <= 0) {
                throw new DataEntryFailedException("Failed to update profile information");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Books a slot for a customer at a gym center.
     * This function records the booking in the `SlotBookings` table.
     * @param centerID The ID of the gym center.
     * @param slotID The ID of the slot being booked.
     * @param userID The ID of the user making the booking.
     * @return boolean indicating success or failure of the booking.
     * @throws DataEntryFailedException If the booking fails.
     */
    @Override
    public boolean bookSlot(int centerID, int slotID, int userID) throws DataEntryFailedException {
        try {
            // SQL query to book the slot for the user
            String sql = "INSERT INTO SlotBookings(`UserId`, `CenterId`, `SlotId`) VALUES (?, ?, ?)";
            connection = DBConnection.connect();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setInt(2, centerID);
            statement.setInt(3, slotID);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted <= 0) {
                throw new DataEntryFailedException("Failed to book the slot");
            } else {
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }
}
