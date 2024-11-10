package com.flipfit.dao;

import com.flipfit.bean.GymSlots;
import com.flipfit.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.List;

public interface GymCenterDAO {

    /**
     * This method retrieves the available slots for a specific gym center on a given date.
     *
     * @param centerId - the ID of the gym center for which slots are to be retrieved.
     * @param date - the date for which available slots are to be fetched.
     * @return List<GymSlots> - a list of available gym slots for the specified center and date.
     * @throws ResourceNotFoundException - thrown if no slots are available for the provided date or center.
     */
    public List<GymSlots> viewSlots(int centerId, Date date) throws ResourceNotFoundException;
}
