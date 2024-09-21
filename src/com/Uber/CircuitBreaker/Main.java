package com.Uber.CircuitBreaker;

import com.Uber.CircuitBreaker.models.Method;
import com.Uber.CircuitBreaker.models.Request;
import com.Uber.CircuitBreaker.models.ResponseCode;
import com.Uber.CircuitBreaker.service.Circuitbreaker;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Circuitbreaker circuitbreaker = new Circuitbreaker();
        Method method = new Method("method1");
        circuitbreaker.createMethod(method);


        //case 1: circuit should open after 10 requests are passed
//        System.out.println("case 1: circuit should open after 10 requests are passed");
//        for(int i=0; i<20; i++){
//            Request request = new Request(ResponseCode.OK);
//            System.out.println("sending request no - "+i);
//            circuitbreaker.sendRequest(method.getName(), request);
//        }

        //case 2: circuit should open after more than or equal to 50% (threshold) of requests are having errors
        System.out.println("case 2: circuit should open after more than or equal to 50% (threshold) of requests are having errors");
        for(int i=0; i<=5; i++) {
            Request request = new Request(ResponseCode.OK,i);
            System.out.println("sending request no- " + i);
            circuitbreaker.sendRequest(method.getName(), request);
        }
//        Request request = null;
        for(int i=6; i<=20; i++) {
//            if (i % 2 == 0) {
                Request request = new Request(ResponseCode.BAD_REQUEST,i);
//            }else {
//                request = new Request(ResponseCode.OK,i);
//            }
            System.out.println("sending request no- " + i);
            circuitbreaker.sendRequest(method.getName(), request);
        }
        TimeUnit.SECONDS.sleep(5);
        for(int i=21; i<=24; i++) { //these all should be processed
//            if (i % 2 == 0) {
            Request request = new Request(ResponseCode.OK,i);
//            }else {
//                request = new Request(ResponseCode.OK,i);
//            }
            System.out.println("sending request no- " + i);
            circuitbreaker.sendRequest(method.getName(), request);
        }


    }
}
