package com.lld.Hackathon.model;

public enum Level {

    EASY(1),
    MEDIUM(2),
    HARD(3);

    private int levelno;

    Level(int levelno) {
        this.levelno = levelno;
    }

    public int getLevelno() {
        return levelno;
    }

}
