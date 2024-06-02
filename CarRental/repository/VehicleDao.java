package com.lld.CarRental.repository;

import com.lld.CarRental.models.Vehicle;

import java.util.HashMap;

public class VehicleDao {
    HashMap<String , Vehicle> vehicleDao;

    public VehicleDao() {
        this.vehicleDao = new HashMap<>();
    }

    public HashMap<String, Vehicle> getVehicleDao() {
        return vehicleDao;
    }

    public void setVehicleDao(HashMap<String, Vehicle> vehicleDao) {
        this.vehicleDao = vehicleDao;
    }
}
