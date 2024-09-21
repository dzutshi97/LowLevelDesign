package com.Uber.CircuitBreaker.service;

import com.Uber.CircuitBreaker.models.Method;
import com.Uber.CircuitBreaker.models.Request;
import com.Uber.CircuitBreaker.models.ResponseCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Circuitbreaker {

    ConcurrentHashMap<String, Method> methods = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();


    public void createMethod(Method method){
        methods.put(method.getName(), method);
    }

    public void sendRequest(String methodName, Request req){
        Method method = methods.get(methodName);
        if(method == null){
            System.out.println("invalid method name - "+methodName);
        }
        if(method.isCircuitOpen()){
            System.out.println("Circuit is open for "+method.getName()+" please try again after x interval");
            return;
        }
        run(method,50,req,100000, 10);
    }

    public void run(Method method, int thresholdPercent, Request newRequest, long minAllowedTimeWindow, int totalAllowedRequests){
        lock.lock();
        try {
            System.out.println("Processing>>> "+newRequest.getReqId());

            boolean isCircuitOpen =method.isCircuitOpen();
            List<Request> requestList = method.getRequestList();
//            requestList.add(newRequest); //imp for right output

            if(isCircuitOpen){
                System.out.println("Circuit is already open. PLease try again later!");
                return;
            }

            long currentTime = System.currentTimeMillis();

            // Collect elements to remove in a separate list to avoid ConcurrentModificationException
            List<Request> toRemove = new ArrayList<>();
            for(Request request: requestList){
                if(currentTime - request.getStartTime() > minAllowedTimeWindow){ //remove all old requests
                    toRemove.add(request);
                }
            }
            // Remove the collected elements
            method.getRequestList().removeAll(toRemove);
            //add new request
            method.getRequestList().add(newRequest);

            int noOfErrors = 0;
            int totalNoOfRequests = 0;

            for (Request request : requestList) {
                if (currentTime - request.getStartTime() < minAllowedTimeWindow && request.getResponseCode() != ResponseCode.OK) {
                    System.out.println("Error found on request id ="+ request.getReqId());
                    noOfErrors++;
                }
                totalNoOfRequests++;
            }
            //totalNoOfRequests > totalAllowedRequests ||
            if ( totalNoOfRequests > totalAllowedRequests || (((double) noOfErrors / totalNoOfRequests) >= (double) thresholdPercent / 100)) { //0.5 for 50%
                System.out.println(newRequest.getReqId());
                System.out.println(((double) noOfErrors / totalNoOfRequests));
                System.out.println(((double) noOfErrors));
                System.out.println(((double) totalNoOfRequests));
//                System.out.println(0/ totalNoOfRequests);
                method.setCircuitOpen(true);
                //sleep for 2 secs
                new Thread(() -> {
                    try{
                        System.out.println("Sleeping for 2 secs....");
                        TimeUnit.SECONDS.sleep(2); //we can also use Thread.sleep
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        method.setCircuitOpen(false);
                        System.out.println("Closing circuit!");
                    }
                }).start();
            } else {
                System.out.println("Request processed sucessfully!");
//                requestList.add(newRequest);
            }
        } finally {
            lock.unlock();
        }
    }
}
