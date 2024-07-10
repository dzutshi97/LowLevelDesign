package com.SnakeLadderGame.models;

import java.util.HashMap;

public class Board {

    int maxPos;
//    int winnerPos;
    HashMap<Integer, BoardEntity> boardEntityPos;

    public Board(int maxPos) {
        this.maxPos = maxPos;
//        int winnerPos = m * n;
        this.boardEntityPos = new HashMap<>();
    }
    public void addEntity(BoardEntity entity) {
        boardEntityPos.put(entity.start, entity);
        }
}
