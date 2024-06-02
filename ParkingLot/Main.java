package com.lld.ParkingLot;


import com.lld.ParkingLot.models.ParkingLot;
import com.lld.ParkingLot.models.ParkingStrategyType;
import com.lld.ParkingLot.models.Vehicle;
import com.lld.ParkingLot.service.ParkingLotService;

public class Main {

    public static void main(String[] args) {

        ParkingLotService service = new ParkingLotService();
        ParkingLot lot = service.initializeParkingLot("mumbai","bpcl-lot-A",5);

//        Vehicle v1 = null;
//        for(int i=0; i<2; i++){
        Vehicle v1 = service.park(ParkingStrategyType.NATURAL_ORDER);
        System.out.println("Vehicle 1 parked in FLOOR - "+ v1.getSlot().getFloor().getFloorNo()  +" SLOT - "+v1.getSlot().getSlotId());
//        }
        Vehicle v2 = service.park(ParkingStrategyType.NATURAL_ORDER);
        System.out.println("Vehicle 2 parked in FLOOR - "+ v2.getSlot().getFloor().getFloorNo()  +" SLOT - "+v2.getSlot().getSlotId());
        service.unpark(v2);
        Vehicle v3= service.park(ParkingStrategyType.NATURAL_ORDER);
        System.out.println("Vehicle 3 parked in FLOOR - "+ v3.getSlot().getFloor().getFloorNo()  +" SLOT - "+v3.getSlot().getSlotId());





    }
}
