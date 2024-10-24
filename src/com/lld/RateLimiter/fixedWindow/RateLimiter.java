package com.lld.RateLimiter.fixedWindow;

import java.util.HashMap;
//Fixed window algo: TC - O(1)
class RateLimit {

    int allowedInterval;
    int numOfAllowedReqsPerInterval;
    HashMap<Integer, RateLimiterConfig> records = new HashMap<>();

    public RateLimit(int allowedInterval, int numOfAllowedReqsPerInterval) {
        this.allowedInterval = allowedInterval;
        this.numOfAllowedReqsPerInterval = numOfAllowedReqsPerInterval;
    }

    static class RateLimiterConfig{
        long timestamp;
        int noOfReqsInCurrWindow;

        public RateLimiterConfig(long timestamp, int noOfReqsInCurrWindow) {
            this.timestamp = timestamp;
            this.noOfReqsInCurrWindow = noOfReqsInCurrWindow;
        }
    }
    class Customer{
        int id;
    }

    public boolean rateLimit(Integer custId) {
        long currTs = System.currentTimeMillis();
        RateLimiterConfig defaultRatelimiterCfg = new RateLimiterConfig(currTs, 0);
        RateLimiterConfig cfg = records.getOrDefault(custId, defaultRatelimiterCfg);
        long ts = cfg.timestamp;
        long timeDiff = currTs - ts;

        if(timeDiff < allowedInterval){
            if(cfg.noOfReqsInCurrWindow < numOfAllowedReqsPerInterval){
                //allow req
                RateLimiterConfig rcgf = new RateLimiterConfig(currTs, cfg.noOfReqsInCurrWindow + 1);
                records.put(custId, rcgf);
                System.out.println("allowed");
                return true;
            }else {
                //reject case
                System.out.println("fail");
                return false;
            }
        }else{
            //allow req
            RateLimiterConfig rcfg = new RateLimiterConfig(currTs, 1);
            records.put(custId, rcfg);
            System.out.println("allowed");
            return true;
        }
    }

}
public class RateLimiter{
    public static void main(String[] args) throws InterruptedException {

        RateLimit rateLimit = new RateLimit(2, 3);
        rateLimit.rateLimit(1);
        rateLimit.rateLimit(1);
        rateLimit.rateLimit(1); //allow till here
        rateLimit.rateLimit(1); //should fail
        rateLimit.rateLimit(1); //fail
        Thread.sleep(2000);
        rateLimit.rateLimit(1); //allow
        rateLimit.rateLimit(1); //allow

    }

}