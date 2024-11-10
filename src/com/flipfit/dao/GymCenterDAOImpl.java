package com.flipfit.dao;

import com.flipfit.bean.GymSlots;
import com.flipfit.business.GymSlotsBusiness;
import com.flipfit.business.GymSlotsBusinessImpl;
import com.flipfit.exceptions.DBConnectionException;
import com.flipfit.exceptions.ResourceNotFoundException;
import com.flipfit.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GymCenterDAOImpl implements GymCenterDAO {

    private Connection conn = null;
    private PreparedStatement statement = null;

    /**
     * This method retrieves all available slots for a given gym center on a specific date.
     * It queries the Slots table for the centerId and calculates the available seats for each slot
     * by invoking the GymSlotsBusiness logic.
     *
     * @param centerId the ID of the gym center
     * @param date the date for which the available slots are required
     * @return List<GymSlots> a list of available slots with seat availability
     * @throws ResourceNotFoundException if no slots are found for the center
     */
    @Override
    public List<GymSlots> viewSlots(int centerId, Date date) throws ResourceNotFoundException {
        // Create a list to store the available slots for the given gym center on the specified date
        List<GymSlots> slots = new ArrayList<>();

        try {
            // Establish a database connection
            conn = DBConnection.connect();

            // Query to retrieve all slots for the given centerId
            statement = conn.prepareStatement("SELECT * from Slots where centerId = ?");
            statement.setInt(1, centerId); // Set the centerId parameter
            ResultSet resultSet = statement.executeQuery(); // Execute the query

            int NumSeatsAvailable = 0;

            // Iterate over the result set to populate the slot details
            while (resultSet.next()) {
                // Retrieve slot ID and calculate available seats for each slot on the given date
                int slotID = resultSet.getInt(1);
                GymSlotsBusiness slotsBusiness = new GymSlotsBusinessImpl();
                NumSeatsAvailable = slotsBusiness.getNumAvailableSeats(slotID, date, resultSet.getInt(5));

                // Add the slot information (including available seats) to the list
                slots.add(new GymSlots(
                        resultSet.getInt(2), // centerId
                        slotID, // slotId
                        resultSet.getTime(3).toLocalTime(), // startTime
                        resultSet.getTime(4).toLocalTime(), // endTime
                        resultSet.getInt(6), // totalSeats
                        NumSeatsAvailable // availableSeats
                ));
            }

            // If no slots are found, throw an exception
            if (slots.isEmpty()) {
                throw new ResourceNotFoundException("No slots found for center");
            }
        } catch (SQLException se) {
            se.printStackTrace(); // Handle SQL exceptions
        } catch (DBConnectionException e) {
            System.out.println(e); // Handle database connection exceptions
        }

        // Return the list of available slots
        return slots;
    }
}
