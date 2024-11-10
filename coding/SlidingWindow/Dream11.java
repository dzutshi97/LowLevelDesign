package Service;

import java.util.*;
public class Dream11 {
    public static void main(String[] args) {

//        int arr[] = new int[]{1, 2, 3, 1}; //true
//        int arr[] = new int[]{1,2,3,1,2,3}; //false
        int arr[] = new int[]{1,0,1,1}; //true
//        int k = 3;
//        int k = 2;
        int k = 1;
        Dream11 main = new Dream11();
        System.out.println(main.solve(arr, k));

    }

    public boolean solve(int arr[], int k) {
        Map<Integer, Set<Integer>> hmap = new HashMap<>();
//build map: element - unique indices
        for (int i = 0; i < arr.length; i++) {
            if(!hmap.containsKey(arr[i])){
                hmap.put(arr[i], new HashSet<>());
            }
            Set<Integer> indices = hmap.get(arr[i]);
            indices.add(i);
            hmap.put(arr[i], indices);
        }
        //iterate over values - indices
        for (int element : hmap.keySet()) {
            Set<Integer> indices = hmap.get(element);
            ArrayList<Integer> al = new ArrayList<>(indices);
            for (int i = 0; i < al.size() - 1; i++) {
                if (Math.abs(al.get(i) - al.get(i + 1)) <= k) {
                    return true;
                }
            }
        }
        return false;
    }
}


