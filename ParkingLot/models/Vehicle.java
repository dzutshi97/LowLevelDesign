package com.lld.ParkingLot.models;

import java.util.UUID;

public class Vehicle {

    private String vehicleId;
    private int regNo;
    private VehicleType vehicleType;
    private String ticketNo;
    private ParkingSlot slot;

    public Vehicle(int regNo, VehicleType vehicleType, String ticketNo) {
        this.vehicleId = UUID.randomUUID().toString();
        this.regNo = regNo;
        this.vehicleType = vehicleType;
        this.ticketNo = ticketNo;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
}
