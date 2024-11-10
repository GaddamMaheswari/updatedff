package com.flipfit.business;

import com.flipfit.dao.GymSlotDAO;
import com.flipfit.dao.GymSlotDAOImpl;

import java.util.Date;

public class GymSlotsBusinessImpl implements GymSlotsBusiness {

    // Creating an instance of the GymSlotDAO to interact with the data layer
    GymSlotDAO gymSlotDAO = new GymSlotDAOImpl();

    // Method to get the number of available seats for a specific slot and date
    @Override
    public int getNumAvailableSeats(int slotId, Date date, int TotalSeats) {
        // Delegating the call to the DAO layer to get the available seats
        return gymSlotDAO.getNumAvailableSeats(slotId, date, TotalSeats);
    }
}
