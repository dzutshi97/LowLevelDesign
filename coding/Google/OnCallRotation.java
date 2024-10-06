package coding.Google;

import java.util.*;

/**
 * Given on-call rotation schedule for multiple people by: their name, start time and end time of the rotation:
 *
 *
 * Abby 1 10
 * Ben 5 7
 * Carla 6 12
 * David 15 17
 *
 *
 * Your goal is to return rotation table without overlapping periods representing who is on call during that time. Return "Start time", "End time" and list of on-call people:
 *
 *
 * 1 5 Abby
 * 5 6 Abby, Ben
 * 6 7 Abby, Ben, Carla
 * 7 10 Abby, Carla
 * 10 12 Carla
 * 15 17 David
 * //https://leetcode.com/discuss/interview-question/5670972/Google-L4-Onsite
 */
public class OnCallRotation {
    public static void main(String[] args) {
        Solve4 solve4 = new Solve4();
        // Hardcoding the input data as per the given example

        // Call the solve method to process the intervals and display the result
        solve4.solve();
    }
}
enum Time{
    START,
    END;
}
class Pair{
    Time time;
    int timeVal;
    String name;

    public Pair(Time time, int timeVal, String name) {
        this.time = time;
        this.timeVal = timeVal;
        this.name = name;
    }
}
class Solve4{
    TreeMap<Integer, Pair> map = new TreeMap<>();
    LinkedHashMap<String, List<String>> ans = new LinkedHashMap<>();


    public void solve(){
        List<String> names = new ArrayList<>();
        Integer prev = null;

        for(Integer timeVal: map.keySet()){

            if(prev!= null && !names.isEmpty())
            {
                ans.put(prev+"-"+timeVal, new ArrayList<>(names));
            }
            Pair pair = map.get(timeVal);
            if(pair.time.equals(Time.START)) {
                names.add(pair.name);
            }
            if(pair.time.equals(Time.END)){
                names.remove(pair.name);
            }
            prev = timeVal;
        }

        // Display the final result
        for (String key : ans.keySet()) {
            System.out.println(key + " " + ans.get(key));
        }
    }
}
