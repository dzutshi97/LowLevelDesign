package com.lld.TicTacToe.models;

import java.util.Scanner;

public class HumanMoveStrategy implements MoveStrategy{
    Scanner scanner = new Scanner(System.in);
    @Override
    public int[] getNextMove() {
        //use scanner to get next move as input from human
        System.out.println("Enter row and column: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new int[]{row, col};

    }
}
