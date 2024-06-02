package com.lld.ParkingLot.repository;

import com.lld.ParkingLot.models.ParkingFloor;
import com.lld.ParkingLot.models.ParkingFloor;

import java.util.TreeMap;

public class ParkingFloorDao {

    private TreeMap<Integer, ParkingFloor> parkingFloorDao;
    public ParkingFloorDao() {
        this.parkingFloorDao = new TreeMap<>();
    }

    public TreeMap<Integer, ParkingFloor> getParkingFloorDao() {
        return parkingFloorDao;
    }

    public void setParkingFloorDao(TreeMap<Integer, ParkingFloor> parkingFloorDao) {
        this.parkingFloorDao = parkingFloorDao;
    }
}
