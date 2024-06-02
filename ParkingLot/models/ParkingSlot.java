package com.lld.ParkingLot.models;

import java.util.UUID;

public class ParkingSlot {

    private int slotId;
    private boolean free;
    private VehicleType vehicleType;
    private Vehicle vehicle;
    private ParkingFloor floor;


    public ParkingSlot( VehicleType vehicleType) {
        this.slotId = IncrementalIdGenerator.getNextSlotId();
        this.free = true;
        this.vehicleType = vehicleType;
    }


    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingFloor getFloor() {
        return floor;
    }

    public void setFloor(ParkingFloor floor) {
        this.floor = floor;
    }

    public void unassignCar(){
        this.free = true;
        this.vehicle = null;
    }
}
