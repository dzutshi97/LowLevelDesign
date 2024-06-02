package com.lld.ParkingLot.service;

import com.lld.ParkingLot.models.*;
import com.lld.ParkingLot.repository.ParkingFloorDao;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class ParkingLotService {
    private ParkingFloorDao dao;
    private ParkingStrategy strategy;
    private static ParkingLot parkingLot;

    public ParkingLotService() {
        this.dao = new ParkingFloorDao();
    }

    public ParkingLot initializeParkingLot(String location, String name, int noOfFloors){ //Singleton design pattern
            if (parkingLot == null)
                parkingLot = createParkingLot(location, name, noOfFloors);
            return parkingLot;
    }

    public ParkingLot createParkingLot(String location,String name, int noOfFloors){

        TreeSet<ParkingFloor> floors = new TreeSet<>(Comparator.comparing(ParkingFloor::getFloorNo));

        for(int i =0;i<noOfFloors;i++){ //floors
            HashSet<ParkingSlot> slots = new HashSet<>();
            ParkingFloor floor = new ParkingFloor(i,slots);
            for(int j=0; j<3;j++){ //slots
                ParkingSlot slot = new ParkingSlot(VehicleType.CAR);
                slot.setFloor(floor);
                slots.add(slot);
            }
            floors.add(floor);
            //store each floor in dao
            this.dao.getParkingFloorDao().put(i,floor);
        }

        ParkingLot lot = new ParkingLot("Bpcl parking lot - A",location,floors);
        return lot;
    }

    public Vehicle park(ParkingStrategyType strategyType){
        Vehicle vehicle = new Vehicle(123,VehicleType.CAR,"1234");
        //get the next available slot based on parking strategy
        if (strategyType.equals(ParkingStrategyType.NATURAL_ORDER)){
            this.strategy = new NaturalOrderParkingStrategy(this.dao);
            ParkingSlot slot = strategy.getNextSlot();

            //throw exception if slot not available

            //park vehicle
            slot.setFree(false);
            slot.setVehicle(vehicle);
            slot.setVehicleType(VehicleType.CAR);

            //generate ticket
            String ticketNo = generateTicket(slot.getFloor().getFloorNo(), slot.getSlotId());
            vehicle.setTicketNo(ticketNo);
            vehicle.setSlot(slot);
            return vehicle;
        }
        return null;
    }

    public void unpark(Vehicle vehicle){
//        validateVehicleTicket()
        ParkingSlot slot = vehicle.getSlot();
        slot.unassignCar();
    }

    public String generateTicket(int floorNo, int slotNo){
        return floorNo + "_" + slotNo;
    }

}
