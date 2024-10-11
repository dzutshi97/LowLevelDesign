package com.lld.TicTacToe.models;

import java.util.Random;

public class RandomAIMoveStrategy implements MoveStrategy{

    Random random = new Random();
    @Override
    public int[] getNextMove() {
        //use random to get next move
        return null;

    }
}
