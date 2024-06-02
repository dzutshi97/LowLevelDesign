package com.lld.CarRental.models;

import java.util.List;

public interface SearchVehicleStrategy {

    Vehicle search(List<Vehicle> vehicles);
}
