package com.flipfit.dao;

import com.flipfit.bean.GymSlots;
import com.flipfit.business.GymSlotsBusiness;
import com.flipfit.business.GymSlotsBusinessImpl;
import com.flipfit.exceptions.*;
import com.flipfit.exception.DBconnectionException;
import com.flipfit.exception.InvalidInputException;
import com.flipfit.exception.ResourceNotFoundException;
import com.flipfit.exception.UnauthorisedAccessException;
import com.flipfit.utils.DBConnection;
import com.flipfit.bean.GymBooking;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymPayment;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GymCustomerDAOImpl implements GymCustomerDAO {
    private Connection conn = null;
    private PreparedStatement statement = null;

    /**
     * This method checks if a booking with the given bookingID exists in the database.
     * @param bookingID The booking ID to be checked.
     * @return boolean indicating if the booking exists.
     */
    public boolean IfBoookingExists(int bookingID) {
        try {
            conn = DBConnection.connect();
            statement = conn.prepareStatement("select * from bookings where BookingId = ?");
            statement.setInt(1, bookingID);  // Set the bookingID parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return false;  // No booking found
            }
            return true;   // Booking found
        } catch (SQLException| DBconnectionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method checks if a customer with the given customerID exists in the database.
     * @param customerID The customer ID to be checked.
     * @return boolean indicating if the customer exists.
     */
    public boolean IfCustomerExists(int customerID) {
        try {
            conn = DBConnection.connect();
            statement = conn.prepareStatement("select * from Customer where CustId = ?");
            statement.setInt(1, customerID);  // Set the customerID parameter
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();   // Returns true if customer exists
        } catch (SQLException| DBconnectionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to create a new gym customer profile in the database.
     * @param customer The customer data to be added to the database.
     * @return boolean indicating whether the profile was created successfully.
     */
    @Override
    public boolean createProfile(GymCustomer customer) throws InvalidCredentialsException, DataEntryFailedException {
        try {
            // check whether user with the mail id exists
            conn = DBConnection.connect();
            statement = conn.prepareStatement("Select * From Registration where EmailAddress = ?");
            statement.setString(1, customer.getCustomerEmailAddress());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                throw new InvalidCredentialsException("User already exists with this email address");
            }

            // adds the user data into user table
            System.out.println("Adding User Profile");
            statement = conn.prepareStatement("insert into User(`Name`,`Email`,`PhoneNumber`,`Role`,`Address`) values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getCustomerEmailAddress());
            statement.setString(3, customer.getCustomerPhone());
            statement.setString(4, "gymcustomer");
            statement.setString(5, customer.getCustomerAddress());
            int rowsAffected = statement.executeUpdate();
            int customerId = 0;
            if (rowsAffected > 0) {
                // Retrieve the generated customerId
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customerId = generatedKeys.getInt(1);
                }
            } else {
                throw new DataEntryFailedException("Failed Adding User Details to User Database");
            }

            // adds the data into customer table
            statement = conn.prepareStatement("insert into Customer values (?,?,?,?,?,?)");
            statement.setInt(1, customerId);
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getCustomerEmailAddress());
            statement.setString(4, customer.getCustomerAddress());
            statement.setString(5, customer.getCustomerPhone());
            statement.setString(6, customer.getPassword());
            statement.executeUpdate();

            // adds the emailId, userId and password to registration table
            statement = conn.prepareStatement("insert into Registration values (?,?,?,?)");
            statement.setInt(1, customerId);
            statement.setString(2, customer.getCustomerEmailAddress());
            statement.setString(3, customer.getPassword());
            statement.setString(4, "gymcustomer");
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
     * This method allows a gym customer to edit their profile details in the database.
     * @param customer The customer data to be updated in the database.
     * @return boolean indicating whether the profile was updated successfully.
     */
    @Override
    public boolean editProfile(GymCustomer customer) throws DataEntryFailedException {
        String sql = "UPDATE Customer SET Name = ?, Email = ?, Address = ?, PhoneNumber = ?, Password=? WHERE CustId = ?";

        try {
            conn = DBConnection.connect();
            // update the user details in customer table
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getCustomerEmailAddress());
            statement.setString(3, customer.getCustomerAddress());
            statement.setString(4, customer.getCustomerPhone());
            statement.setString(5, customer.getPassword());
            statement.setInt(6, customer.getCustomerId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated <= 0) {
                throw new DataEntryFailedException("Failed to update profile in Customer Database");
            }

            // update the user details in user table
            statement = conn.prepareStatement("UPDATE User SET Name = ?, Email = ?, Address = ?, PhoneNumber = ? WHERE UserId = ?");
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getCustomerEmailAddress());
            statement.setString(3, customer.getCustomerAddress());
            statement.setString(4, customer.getCustomerPhone());
            statement.setInt(5, customer.getCustomerId());
            statement.executeUpdate();

            // update the user details in registration table
            statement = conn.prepareStatement("UPDATE Registration SET EmailAddress = ?, Password = ? WHERE UserId = ?");
            statement.setString(1, customer.getCustomerEmailAddress());
            statement.setString(2, customer.getPassword());
            statement.setInt(3, customer.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException | InvalidInputException | DBconnectionException se ) {
            se.printStackTrace();
        }
        return false;
    }

    /**
     * This method retrieves and displays the bookings made by a specific customer.
     * @param customerId The ID of the customer whose bookings are to be retrieved.
     */
    @Override
    public void viewBookings(int customerId) {
        try {
            conn = DBConnection.connect();
            System.out.println("Adding User Profile");
            statement = conn.prepareStatement("select * from bookings where CustomerId = ?");
            statement.setInt(1, customerId);
            statement.executeQuery();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method checks whether a booking is on the waitlist.
     * @param bookingID The ID of the booking to check.
     * @return boolean indicating whether the booking is on the waitlist.
     */
    @Override
    public boolean waitlistStatus(int bookingID) {
        try {
            if(!IfBoookingExists(bookingID))
                throw new ResourceNotFoundException("Booking ID already exists");

            conn = DBConnection.connect();
            System.out.println("Checking Waitlisted");
            statement = conn.prepareStatement("select * from waitlist where BookingID = ?");
            statement.setInt(1, bookingID);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.getInt("Status") > 0;

        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method cancels a specific booking made by a customer.
     * @param bookingID The ID of the booking to be cancelled.
     * @param customerID The ID of the customer cancelling the booking.
     * @return boolean indicating whether the booking was successfully cancelled.
     */
    @Override
    public boolean cancelBooking(int bookingID , int customerID ) {
        try {
            if(!IfCustomerExists(customerID))
                throw new UnauthorisedAccessException("UnAutherised Access!");
            conn = DBConnection.connect();
            System.out.println("Cancel Booking...");
            statement = conn.prepareStatement("DELETE FROM Bookings where BookingID=?");
            statement.setInt(1, bookingID);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return false;
    }




    @Override
/**
 * Creates a booking for a customer for a specific slot and date.
 * This method checks if a booking already exists for the slot and date.
 * If no booking exists, it creates a new entry in the `AvailableSeats` table and updates the seat count.
 * If a booking already exists and seats are available, it updates the available seats and creates a new booking.
 * @param customerID The ID of the customer making the booking.
 * @param slotID The ID of the slot being booked.
 * @param centerId The ID of the center where the booking is made.
 * @param date The date for the booking.
 * @return The booking ID if the booking is successfully created, otherwise -1.
 * @throws ResourceNotFoundException if no seats are available for the selected slot and date.
 */
    public int createBooking(int customerID, int slotID, int centerId, Date date) throws ResourceNotFoundException {
        try {
            conn = DBConnection.connect();
            statement = conn.prepareStatement("select * from AvailableSeats where slotId=? and Date=?");
            statement.setInt(1, slotID);
            statement.setDate(2, new java.sql.Date(date.getTime()));
            ResultSet resultSet = statement.executeQuery();
            int bookingID = 0;

            if (resultSet.next() && resultSet.getInt(3) > 0) {
                int numSeats = resultSet.getInt(3);
                statement = conn.prepareStatement("update AvailableSeats set NumSeats = ? where slotId=? and Date=?");
                statement.setInt(1, numSeats - 1);
                statement.setInt(2, slotID);
                statement.setDate(3, new java.sql.Date(date.getTime()));
                statement.executeUpdate();

                statement = conn.prepareStatement("insert into CustomerBooking(`CustId`,`centerId`,`slotId`,`Date`) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setInt(1, customerID);
                statement.setInt(2, centerId);
                statement.setInt(3, slotID);
                statement.setDate(4, new java.sql.Date(date.getTime()));
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        bookingID = generatedKeys.getInt(1);
                    }
                }
            } else if (!resultSet.next()) {
                statement = conn.prepareStatement("select * from slots where slotsId = ?");
                statement.setInt(1, slotID);
                resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("Something went wrong");
                }
                int TotalSeats = resultSet.getInt(5);
                statement = conn.prepareStatement("insert into AvailableSeats(`slotId`,`Date`,`NumSeats`) values (?,?,?)");
                statement.setInt(1, slotID);
                statement.setDate(2, new java.sql.Date(date.getTime()));
                statement.setInt(3, TotalSeats - 1);
                statement.executeUpdate();

                statement = conn.prepareStatement("insert into CustomerBooking(`CustId`,`centerId`,`slotId`,`Date`) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setInt(1, customerID);
                statement.setInt(2, centerId);
                statement.setInt(3, slotID);
                statement.setDate(4, new java.sql.Date(date.getTime()));
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        bookingID = generatedKeys.getInt(1);
                    }
                }
            } else {
                throw new ResourceNotFoundException("No Seats Available for the slot on that date");
            }
            return bookingID;
        } catch (SQLException se) {
            se.printStackTrace();
            return -1;
        } catch (DBConnectionException e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * Makes a payment for a specific booking.
     * This method verifies that the booking exists, retrieves the cost associated with the booking,
     * and processes the payment by adding an entry into the `payment` table.
     * @param paymentData The payment data including booking ID, mode, and amount.
     * @return The payment ID if the payment is successfully processed, otherwise -1.
     * @throws DataEntryFailedException if there is an issue inserting the payment data into the database.
     */
    @Override
    public int makepayment(GymPayment paymentData) throws DataEntryFailedException {
        try {
            conn = DBConnection.connect();
            statement = conn.prepareStatement("Select * from CustomerBooking where BookingId = ?");
            statement.setInt(1, paymentData.getBookingID());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int slotId = resultSet.getInt(4);
            statement = conn.prepareStatement("SELECT * from Slots where slotsId = ?");
            statement.setInt(1, slotId);
            resultSet = statement.executeQuery();
            resultSet.next();
            int cost = resultSet.getInt(6);

            statement = conn.prepareStatement("insert into payment(`BookingId`, `Mode`, `Amount`) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, paymentData.getBookingID());
            statement.setString(2, paymentData.getMode());
            statement.setInt(3, cost);
            int rowsAffected = statement.executeUpdate();
            int paymentId = 0;

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    paymentId = generatedKeys.getInt(1);
                }
            } else {
                throw new DataEntryFailedException("Data Entry Failed into the Payments Database");
            }
            return paymentId;
        } catch (SQLException se) {
            se.printStackTrace();
            return -1;
        } catch (DBConnectionException e) {
            System.out.println(e);
        }
        return -1;
    }

    /**
     * Modifies an existing booking by canceling the previous booking and creating a new one.
     * @param bookingID The ID of the booking to be modified.
     * @param customerID The ID of the customer modifying the booking.
     * @param centerID The ID of the center where the new booking will be made.
     * @param slotID The ID of the slot for the new booking.
     * @return The ID of the new booking if successfully created, otherwise -1.
     */
    @Override
    public int modifyBooking(int bookingID, int customerID, int centerID, int slotID) {
        try {
            cancelBooking(bookingID, customerID);
            int newBookingID = createBooking(customerID, centerID, slotID);
            return newBookingID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Updates the password for a customer.
     * This method verifies the customer's email and role, and then updates the password in the `Registration`
     * and `Customer` tables.
     * @param email The customer's email address.
     * @param password The new password.
     * @param role The role associated with the email.
     * @return true if the password is successfully updated, otherwise false.
     * @throws InvalidCredentialsException if the provided email or role is not found in the database.
     */
    @Override
    public boolean updatepwd(String email, String password, String role) throws InvalidCredentialsException {
        try {
            if (!IfCustomerExists(CustomerID))
                throw new UnauthorisedAccessException("UnAutherised Access!");

            conn = DBConnection.connect();
            statement = conn.prepareStatement("Select * from Registration where EmailAddress=? and role=?");
            statement.setString(1, email);
            statement.setString(2, role);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new InvalidCredentialsException("You are not registered for this role yet!!");
            } else {
                int id = resultSet.getInt(1);
                statement = conn.prepareStatement("update Registration set Password=? where UserId=?");
                statement.setString(1, password);
                statement.setInt(2, id);
                statement.executeUpdate();

                statement = conn.prepareStatement("update Customer set Password=? where CustId=?");
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