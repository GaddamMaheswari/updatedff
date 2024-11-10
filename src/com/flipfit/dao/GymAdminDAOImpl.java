package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.exceptions.DBConnectionException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceNotFoundException;
import com.flipfit.exceptions.StatusUpdatedException;
import com.flipfit.utils.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.*;

public class GymAdminDAOImpl implements GymAdminDAO {
    private Connection conn = null;
    private PreparedStatement statement = null;
    private PreparedStatement stmt = null;

    /**
     * This method retrieves all gym bookings from the CustomerBooking table and
     * fetches the associated gym center and slot information.
     * It returns a list of GymBooking objects containing the booking details.
     *
     * @return List<GymBooking> a list of gym bookings
     * @throws ResourceNotFoundException if no bookings are found
     */
    @Override
    public List<GymBooking> viewBookings() throws ResourceNotFoundException {
        List<GymBooking> bookings = new ArrayList<>();
        try {
            // We get all bookings from the CustomerBooking table
            conn = DBConnection.connect();
            statement = conn.prepareStatement("select * from CustomerBooking");
            ResultSet resultSet = statement.executeQuery();

            // For every booking, we fetch the associated gym center and slot information
            while (resultSet.next()) {
                statement = conn.prepareStatement("select * from GymCenters where centerId = ?");
                statement.setInt(1, resultSet.getInt(3));
                ResultSet resultSet1 = statement.executeQuery();
                resultSet1.next();
                String CenterName = resultSet1.getString(3);
                String CenterLoc = resultSet1.getString(4);

                statement = conn.prepareStatement("select * from Slots where slotsId = ?");
                statement.setInt(1, resultSet.getInt(4));
                resultSet1 = statement.executeQuery();
                resultSet1.next();
                LocalTime startTime = resultSet1.getTime(3).toLocalTime();
                LocalTime endTime = resultSet1.getTime(4).toLocalTime();

                // Store the booking details in the list
                bookings.add(new GymBooking(resultSet.getInt(2), CenterName, CenterLoc, startTime, endTime, resultSet.getDate(5)));
            }

            if(bookings.isEmpty()){
                throw new ResourceNotFoundException("Bookings not found");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return bookings;
    }

    /**
     * This method approves or rejects an owner's registration request based on
     * the provided request ID and status. If the request is approved, the gym center
     * details are inserted into the GymCenters table.
     *
     * @param requestId the ID of the registration request
     * @param statuss the status to set (approved or rejected)
     * @throws StatusUpdatedException if the status is already updated
     * @throws ResourceNotFoundException if the registration request does not exist
     */
    @Override
    public void approveOwnerRegistration(int requestId, String statuss) throws StatusUpdatedException, ResourceNotFoundException {
        try {
            conn = DBConnection.connect();

            // Check if the registration request exists
            statement = conn.prepareStatement("Select * from OwnerRequest where RequestId=?");
            statement.setInt(1, requestId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String status = resultSet.getString(3);

                // Check if the status is already updated
                if (!status.equals("pending")) {
                    throw new StatusUpdatedException("The request has already been approved/rejected");
                }

                // Update the status of the request
                int ownerId = resultSet.getInt(2);
                String centerName = resultSet.getString(4);
                String location = resultSet.getString(5);
                int NumOfSlots = resultSet.getInt(6);

                // Update the status in the OwnerRequest table
                statement = conn.prepareStatement("Update OwnerRequest set status=? where RequestId=?");
                statement.setString(1, statuss);
                statement.setInt(2, requestId);
                statement.executeUpdate();

                // If approved, add center to GymCenters table
                if (statuss.equals("approved")) {
                    statement = conn.prepareStatement("insert into GymCenters(`OwnerId`,`CenterName`,`Location`,`NumOfSlots`) values (?,?,?,?)");
                    statement.setInt(1, ownerId);
                    statement.setString(2, centerName);
                    statement.setString(3, location);
                    statement.setInt(4, NumOfSlots);
                    statement.executeUpdate();
                }
            } else {
                throw new ResourceNotFoundException("Such Request Does Not Exist");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
    }

    /**
     * This method retrieves all pending gym owner registration requests.
     * It returns a list of GymOwnerRequest objects containing the request details.
     *
     * @return List<GymOwnerRequest> a list of pending registration requests
     * @throws ResourceNotFoundException if no pending requests are found
     */
    @Override
    public List<GymOwnerRequest> pendingRequests() throws ResourceNotFoundException {
        List<GymOwnerRequest> requests = new ArrayList<>();
        try {
            // Get all pending owner registration requests
            conn = DBConnection.connect();
            statement = conn.prepareStatement("Select * from OwnerRequest where Status=?");
            statement.setString(1, "pending");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Store all the pending requests in the list
                requests.add(new GymOwnerRequest(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }

            if(requests.isEmpty()){
                throw new ResourceNotFoundException("No Pending Requests Available");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return requests;
    }

    /**
     * This method retrieves all gym centers from the GymCenters table.
     * It returns a list of GymCenter objects containing the center details.
     *
     * @return List<GymCenter> a list of gym centers
     * @throws ResourceNotFoundException if no gym centers are found
     */
    @Override
    public List<GymCenter> viewCenter() throws ResourceNotFoundException {
        List<GymCenter> centers = new ArrayList<>();
        try {
            // Get all gym centers
            conn = DBConnection.connect();
            statement = conn.prepareStatement("Select * from GymCenters");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Store the gym center information in a list
                centers.add(new GymCenter(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }

            if(centers.isEmpty()){
                throw new ResourceNotFoundException("No Gym Centers Available");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return centers;
    }

    /**
     * This method updates the password of a user (admin or gym owner) based on
     * the provided email and role. It updates the password in both the Registration
     * and AdminInfo tables for admins.
     *
     * @param email the email address of the user
     * @param password the new password to set
     * @param role the role of the user (admin or gym owner)
     * @return boolean true if the password was successfully updated, false otherwise
     * @throws InvalidCredentialsException if the user does not exist with the provided email and role
     */
    @Override
    public boolean updatepwd(String email, String password, String role) throws InvalidCredentialsException {
        try {
            // Check if the user exists with the provided email and role
            conn = DBConnection.connect();
            stmt = conn.prepareStatement("Select * from Registration where EmailAddress=? and role=?");
            stmt.setString(1, email);
            stmt.setString(2, role);
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next()) {
                throw new InvalidCredentialsException("You are not registered for this role yet!!");
            } else {
                // Update the password in the Registration table
                int id = resultSet.getInt(1);
                statement = conn.prepareStatement("update Registration set Password=? where UserId=?");
                statement.setString(1, password);
                statement.setInt(2, id);
                statement.executeUpdate();

                // Also update the password in the AdminInfo table for admins
                statement = conn.prepareStatement("update AdminInfo set Password=? where AdminId=?");
                statement.setString(1, password);
                statement.setInt(2, id);
                statement.executeUpdate();
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
