package com.SnakeLadderGame.models;

import java.util.Random;

public class Dice {
    int max;

    public int roll(int max){
        Random r= new Random();
        return r.nextInt(max)  + 1; //return random no between 1 to max
    }
}
