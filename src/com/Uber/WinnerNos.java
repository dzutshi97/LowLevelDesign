package com.Uber;

import java.util.ArrayList;
import java.util.List;

/**
 * Question 1: You are given an array with numbers, each round, half of the elements are deleted, the lower number stays, the higher number goes. Comparison is only among consecutive elements.
 *
 *
 * Example1:
 * 1,2,3,4,5,6,7,8
 * Round1: 1,2,3,4,5,6,7,8
 * Round2: 1,3,5,7
 * Round3: 1,5
 * Round4: 1
 * 1 is winnner
 *
 *
 * Example2:
 * 1,2,3,4,5,6,7
 * 1,3,5,7
 * 1,5
 * 1
 * 1 is winner
 * Note: if number is odd, last element just moves to the next round
 * Print these rounds.
 */
public class WinnerNos {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);
//        input.add(8);
        WinnerNos winnerNos = new WinnerNos();
        winnerNos.solve(input);
    }

    public void solve(List<Integer> input){
        if(input.size() == 1){
            return;
        }
        List<Integer> nextIp = new ArrayList<>();

        for(int i=0; i<input.size(); i+=2){
            if(i+1 <input.size() && input.get(i) < input.get(i+1)){
                nextIp.add(input.get(i));
            }
        }
        if(input.size()%2 != 0){
            nextIp.add(input.get(input.size()-1));
        }
        for(int i=0; i<nextIp.size(); i++){
            System.out.print(nextIp.get(i)+ " ");
        }
        System.out.println();
        solve(nextIp);
    }
}
