package com.lld.ParkingLot.models;

public class IncrementalIdGenerator {

    public static int slotId = 1;

    public static int getNextSlotId() {
        return slotId++;
    }
}
