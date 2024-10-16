//LC: Insert Delete GetRandom
package com.Uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {
    ArrayList<Integer> list; //list stores the value
    HashMap<Integer, Integer> hmap; //map stores <values,Index>

    public RandomizedSet() {
        this.list = new ArrayList<>();
        this.hmap = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(hmap.containsKey(val)){
            return false;
        }
        list.add(val);
        hmap.put(val, list.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!hmap.containsKey(val)){
            return false;
        }
        int valIdx = hmap.get(val);
        if(valIdx < list.size()-1){
            int lastElement =  list.get(list.size()-1);
            list.set(valIdx,lastElement);
            hmap.put(lastElement,valIdx); //update the last element's new pos in map
        }
        hmap.remove(val);
        list.remove(list.size()-1); // the originl last element still exists at the last position. So remove it.
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        int val= list.get(random.nextInt(list.size()));
        return val;
    }
}

