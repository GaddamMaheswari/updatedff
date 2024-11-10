package com.flipfit.dao;

import java.util.Date;

public interface GymSlotDAO {
    public int getNumAvailableSeats(int slotId, Date date, int TotalSeats);
}
