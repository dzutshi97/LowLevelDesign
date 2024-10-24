package com.lld.TicTacToe.models;

import com.lld.SnakeLadder_old.models.Move;

//Player acts as a context class which holds reference to the concrete strategy interface/abstraction.
public class Player {
    private char symbol;
    private MoveStrategy moveStrategy;

    public int[] makeNextMove(MoveStrategy moveStrategy){
        int[] pos = moveStrategy.getNextMove();
        return pos;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
