package com.lld.ParkingLot.models;

import java.util.HashSet;
import java.util.Set;

public class ParkingFloor {

    private int floorNo;
    private Set<ParkingSlot> parkingSlots;
    private boolean isFull;
    private static int max_floor_capacity;


    public ParkingFloor(int floorNo, HashSet<ParkingSlot> slots) {
        this.floorNo = floorNo;
        this.parkingSlots = slots;
        this.isFull = false;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public static int getMax_floor_capacity() {
        return max_floor_capacity;
    }

    public static void setMax_floor_capacity(int max_floor_capacity) {
        ParkingFloor.max_floor_capacity = max_floor_capacity;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public Set<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(Set<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }
}
