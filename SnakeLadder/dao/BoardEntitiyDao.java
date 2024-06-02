package com.lld.SnakeLadder.dao;
import com.lld.SnakeLadder.models.BoardEntity;

import java.util.HashMap;

public class BoardEntitiyDao {
    //map to store board entity against it's position
    private HashMap<Integer, BoardEntity> map;
    private static volatile BoardEntitiyDao instance;
    private BoardEntitiyDao() {
        this.map = new HashMap<>();
    }
    public static BoardEntitiyDao getInstance() {
        if (instance == null) {
            synchronized (BoardEntitiyDao.class) {
                if (instance == null) {
                    instance = new BoardEntitiyDao();
                }
            }
        }
        return instance;
    }

    public HashMap<Integer, BoardEntity> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, BoardEntity> map) {
        this.map = map;
    }
}
