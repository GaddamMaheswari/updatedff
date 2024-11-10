package com.flipfit.business;

import com.flipfit.bean.GymSlots;

import java.util.Date;
import java.util.List;

public interface GymCenterBusiness {

    // Method to retrieve available gym slots for a given center on a specific date
    // Parameters: centerId (ID of the gym center), date (the date for which slots are requested)
    // Returns a list of GymSlots objects representing the available slots for the center on the given date
    public List<GymSlots> viewSlots(int centerId, Date date);
}
