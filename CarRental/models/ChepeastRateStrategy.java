package com.lld.CarRental.models;

import java.util.*;

public class ChepeastRateStrategy implements SearchVehicleStrategy{


//    private static class CheapRateComparator implements Comparator<Vehicle> {
//        @Override
//        public int compare(Vehicle o1, Vehicle o2) {
//             return o1.getRate().equals(o2.getRate()) ? o1.getBranch().getDistance().compareTo(o2.getBranch().getDistance()) : o1.getRate().compareTo(o2.getRate());
//        }
//    }
    @Override
    public Vehicle search(List<Vehicle> vehicles) { //you can return Optional here. Refer - https://github.com/subbiah95/VehicleRentalService/blob/0e2865bf477266a05c51d2927fb076515a441224/com/vehicle/rental/service/VehicleBookingService.java#L57
//      vehicles.sort(new CheapRateComparator());
//      Use below lambda expression as shortcut since java8. Refer - https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property

        Collections.sort(vehicles, (o1,o2) ->
                o1.getRate().equals(o2.getRate()) ? o1.getBranch().getDistance().compareTo(o2.getBranch().getDistance()) : o1.getRate().compareTo(o2.getRate()));

        for(Vehicle v: vehicles){
            if(v.getReservationStatus().equals(ReservationStatus.AVAILABLE)){
                return v;
            }
        }
        return null;
    }
}
