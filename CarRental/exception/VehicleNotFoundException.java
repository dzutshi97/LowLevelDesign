package com.lld.CarRental.exception;

import com.lld.CarRental.models.Vehicle;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException() {
        super("Vehicle Not found in system");
    }
}
