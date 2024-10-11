//package coding.Google;
//
///**
// * This is an open-ended question. there is just a log. A bunch of logs will be provided ⇒ [CheckPointName, CarNamePlate, TimeOfPassingTheCheckPoint]
// * I need to calculate the cost for each of the cars from it. That’s it.
// */
//
//import sun.rmi.runtime.Log;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.*;
//
///**
// * Solution:
// * Plan
// * Preprocess the Log:
// *
// * Parse the log to group all entries by CarNamePlate so that each car's checkpoint data is together.
// * Define Cost Logic: (Strategy pattern)
// *
// * Distance-based cost: Assign costs based on the sequence of checkpoints the car passes.
// * Time-based cost: Calculate time spent between consecutive checkpoints for each car.
// * Checkpoint-specific cost: Assign specific costs for passing certain checkpoints (like toll booths).
// * Algorithm:
// *
// * Sort log entries by CarNamePlate and then by TimeOfPassing.
// * For each car:
// * Track the time spent between checkpoints.
// * Track the number or sequence of checkpoints passed
// */
//class LogEntry{
//    String checkPointName;
//    String carNamePlate;
//    String timeOfPassing;
//}
//class CarTollTimes{
//    String tollName;
//    int timeInMinutes;
//
//
//}
//public class CarCheckpoint {
//
//    //Allot cost against each toll
//    //Given the log entries, group by Car & for each car sort by timeOfPassing.
//    //Cost calculation strategy: Calculate time diff from one toll to another. Add this time diff value with the toll unit cost
//    // Return a map of (K,V) => carNameplate, totalCost
//
//    //Allot cost against each toll
//    public void allotPriceForTolls(){
//        HashMap<String ,Integer> tollUnitPrices = new HashMap<>();
//        tollUnitPrices.put("A",10);
//        tollUnitPrices.put("B",20);
//        tollUnitPrices.put("C",30);
//    }
//
//    List<LogEntry> logEntries = new ArrayList<>();
//    HashMap<String, List<CarTollTimes>> carInfo = new HashMap<>();
//
//    public void calculateCost() {
//
//        //prepare map
//        for (int i = 0; i < logEntries.size(); i++) {
//            LogEntry entry = logEntries.get(i);
//            carInfo.putIfAbsent(entry.carNamePlate, new ArrayList<>());
//            List<CarTollTimes> carTollTimes = carInfo.get(entry.carNamePlate);
//            String[] timeParts = entry.timeOfPassing.split(":");
//            int hours = Integer.parseInt(timeParts[0]);
//            int mins = Integer.parseInt(timeParts[1]);
//            int timeOfPassingInMinutes = hours * 60 + mins;
//            carTollTimes.add(new CarTollTimes(to)); //sort by times
//            //update the map
//            carInfo.put(entry.carNamePlate,timings);
//        }
//        //
//        for(String carNamePlate : carInfo.keySet()){
//
//            List<Integer> timings = carInfo.get(carNamePlate);
//            for(int i=0; i<timings.size(); i++){
//                int currTime = timings.get(i);
//                int nextTime = timings.get(i+1);
//                int diff = nextTime - currTime;
////                int newCost = diff +
//            }
//
//
//        }
//
//    }
//
//
//
//}
