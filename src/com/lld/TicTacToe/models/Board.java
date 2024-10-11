package com.lld.TicTacToe.models;

import java.util.List;

public class Board {

    char[][] board;


    public Board(int m, int n) {
        char[][] board = new char[m][n];
        this.board = board;
    }
}
