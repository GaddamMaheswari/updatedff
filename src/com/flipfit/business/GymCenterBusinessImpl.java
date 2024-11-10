package com.flipfit.business;

import com.flipfit.bean.GymSlots;
import com.flipfit.dao.GymCenterDAO;
import com.flipfit.dao.GymCenterDAOImpl;
import com.flipfit.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GymCenterBusinessImpl implements GymCenterBusiness {

    // Create an instance of GymCenterDAO to interact with the data access layer
    GymCenterDAO gymCenterDAO = new GymCenterDAOImpl();

    /**
     * Views the available slots for a specific gym center on a specific date.
     * This method retrieves all the available slots for the gym center on the given date.
     * @param centerId - the ID of the gym center whose slots are to be viewed
     * @param date - the date for which the slots are being retrieved
     * @return a list of GymSlots containing the available slots for the given center and date, or an empty list if no slots are found
     */
    @Override
    public List<GymSlots> viewSlots(int centerId, Date date) {
        try {
            // Calling the viewSlots method from GymCenterDAO to get available slots for the center on the given date
            return gymCenterDAO.viewSlots(centerId, date);
        } catch (ResourceNotFoundException e) {
            // Catching the exception if no slots are found for the given center and date
            System.out.println(e);
        }
        // Return an empty list if an exception occurs
        return new ArrayList();
    }
}
