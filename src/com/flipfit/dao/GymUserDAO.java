package com.flipfit.dao;

import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.GymOwner;
import com.flipfit.exceptions.DBConnectionException;
import com.flipfit.exceptions.InvalidCredentialsException;
import com.flipfit.exceptions.ResourceNotFoundException;

import java.util.List;

public interface GymUserDAO {
    public List<GymCustomer> viewAllCustomers() throws ResourceNotFoundException;
    public List<GymOwner> viewAllGymOwners() throws ResourceNotFoundException;
    public int login(String email, String password, String role) throws InvalidCredentialsException, DBConnectionException;
}
