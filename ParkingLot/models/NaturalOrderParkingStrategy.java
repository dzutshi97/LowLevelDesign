package com.lld.ParkingLot.models;

import com.lld.ParkingLot.repository.ParkingFloorDao;

import java.util.*;
import java.util.stream.Collectors;

public class NaturalOrderParkingStrategy implements ParkingStrategy {

    ParkingFloorDao parkingFloorDao;

    public NaturalOrderParkingStrategy(ParkingFloorDao parkingFloorDao) {
        this.parkingFloorDao = parkingFloorDao;
    }

    //    private static class ParkingSlotComparator implements Comparator<ParkingSlot>{ //WHY static? NOTE: Comparator works with Lists but not with HashSets!!
//        @Override
//        public int compare(ParkingSlot o1, ParkingSlot o2) {
//            return Integer.compare(o1.getSlotId(),o2.getSlotId());
//        }
//    }

    public ParkingSlot getNextSlot(){
        //Sort the slots in ascending order
        Map<Integer,ParkingFloor> parkingFloors = this.parkingFloorDao.getParkingFloorDao();

        for(Map.Entry<Integer,ParkingFloor> entry: parkingFloors.entrySet()){
            if(entry.getValue().isFull()){ //get next floor if current floor is full
                continue;
            }
            Set<ParkingSlot> slots = entry.getValue().getParkingSlots();

            List<ParkingSlot> slotList = entry.getValue().getParkingSlots().stream().collect(Collectors.toList());
            Optional<ParkingSlot> slot = slotList.stream().sorted(Comparator.comparingInt(ParkingSlot::getSlotId)).filter(ParkingSlot::isFree).findFirst();
            if (slot.isPresent()){
                return slot.get();
            }
//          Collections.sort(slots, new ParkingSlotComparator()); NOTE: Comparator works with Lists but not with HashSets!!
            //OR use treeSet
//            Set<ParkingSlot> sortedSlots = new TreeSet<>(Comparator.comparing(ParkingSlot::getSlotId));
//            sortedSlots.addAll(slots); //Note: instead of foreach we can use addAll
//            for(ParkingSlot slot: sortedSlots){
//                if(!slot.isFree()){
//                    continue;
//                }
//                return slot;
//            }
        }
        return null;
    }





}
