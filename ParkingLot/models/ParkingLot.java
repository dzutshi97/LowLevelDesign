package com.lld.ParkingLot.models;

import java.util.Set;
import java.util.TreeSet;

public class ParkingLot {

    private String lotName;
    private Set<ParkingFloor> parkingFloors;
    private String location;
    private static int MAX_FLOOR_CAPACITY = 10;



    public ParkingLot(String lotName, String location,TreeSet<ParkingFloor> floors) {
        this.lotName = lotName;
        this.parkingFloors = floors;
        this.location = location;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public Set<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(Set<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
