package com.Uber.CircuitBreaker.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Method {

    private String id;
    private String name;
    private volatile boolean isCircuitOpen;
    List<Request> requestList;

    public Method(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public boolean isCircuitOpen(){
        return isCircuitOpen;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCircuitOpen(boolean circuitOpen) {
        isCircuitOpen = circuitOpen;
    }

    public List<Request> getRequestList() {
        if(requestList == null){
            requestList = new ArrayList<>();
        }
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }
}
