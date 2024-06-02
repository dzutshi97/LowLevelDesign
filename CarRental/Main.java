package com.lld.CarRental;

import com.lld.CarRental.models.Vehicle;
import com.lld.CarRental.models.VehicleType;
import com.lld.CarRental.service.VehicleRentalService;

public class Main {

    public static void main(String[] args) {

        VehicleRentalService vehicleRentalService = new VehicleRentalService();

        vehicleRentalService.onboardStation(VehicleType.CAR);
        Vehicle vehicle = vehicleRentalService.searchVehicle(VehicleType.CAR);

        if(vehicle!=null){
            vehicleRentalService.BookVehicle(vehicle);
        }
    }
}
