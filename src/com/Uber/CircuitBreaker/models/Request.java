package com.Uber.CircuitBreaker.models;

import java.util.UUID;

public class Request {

    private int reqId;
    private ResponseCode responseCode;
    private long startTime;
//    private


    public Request(ResponseCode responseCode, int reqId) {
        this.reqId = reqId;
        this.responseCode = responseCode;
        this.startTime = System.currentTimeMillis();
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
