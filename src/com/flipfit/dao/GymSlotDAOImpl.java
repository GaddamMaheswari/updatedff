package com.flipfit.dao;

import com.flipfit.exceptions.DBConnectionException;
import com.flipfit.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GymSlotDAOImpl implements GymSlotDAO {
    private Connection conn = null;  // Connection object for interacting with the database
    private PreparedStatement statement = null;  // PreparedStatement object for executing SQL queries

    /**
     * Retrieves the number of available seats for a given slot on a specific date.
     * If no available seats are found in the database, it returns the total number of seats.
     * @param slotId The ID of the slot.
     * @param date The date for which the seat availability is being checked.
     * @param TotalSeats The total number of seats in the slot.
     * @return The number of available seats.
     */
    @Override
    public int getNumAvailableSeats(int slotId, Date date, int TotalSeats) {
        int NumSeatsAvailable = 0;  // Variable to hold the number of available seats

        try {
            // Establishing a connection to the database
            conn = DBConnection.connect();

            // SQL query to fetch the available seats for the given slotId and date
            statement = conn.prepareStatement("select * from AvailableSeats where slotId = ? and Date = ?");
            statement.setInt(1, slotId);  // Setting the slotId in the query
            statement.setDate(2, new java.sql.Date(date.getTime()));  // Setting the date in the query

            // Executing the query and getting the result
            ResultSet resultSet1 = statement.executeQuery();

            // If a result is found, fetch the available seats, else assume all seats are available
            if(resultSet1.next()) {
                NumSeatsAvailable = resultSet1.getInt(3);  // Retrieve the available seats from the result set
            } else {
                NumSeatsAvailable = TotalSeats;  // If no result found, all seats are available
            }
        } catch (SQLException e) {
            // Catch and print any SQL exceptions
            e.printStackTrace();
        } catch (DBConnectionException e) {
            // Catch and print any DB connection exceptions
            System.out.println(e);
        }

        // Return the number of available seats
        return NumSeatsAvailable;
    }
}
