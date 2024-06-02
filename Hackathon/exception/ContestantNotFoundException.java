package com.lld.Hackathon.exception;

public class ContestantNotFoundException extends RuntimeException{

    public ContestantNotFoundException() {
        super("Not a valid contestant id");
    }
}
