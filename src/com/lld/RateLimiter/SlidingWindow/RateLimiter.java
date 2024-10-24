package com.lld.RateLimiter.SlidingWindow;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * link: https://leetcode.com/discuss/interview-question/system-design/124558/Uber-or-Rate-Limiter
 * Whenever you expose a web service / api endpoint, you need to implement a rate limiter to prevent abuse of the service (DOS attacks).
 *
 * Implement a RateLimiter Class with an isAllow method. Every request comes in with a unique clientID, deny a request if that client has made more than 100 requests in the past second.
 */
public class RateLimiter {
    private int maxRequests;
    private long interval;
    private Map<String, Queue<Long>> clientRequestTimestamps;
    private Map<String, Lock> clientLocks;


    public RateLimiter(int maxRequests, long interval) {
        this.maxRequests = maxRequests;
        this.interval = interval;
        this.clientRequestTimestamps = new HashMap<>();
    }

    /**
     * By using explicit locks, each client ID is associated with its own lock, allowing concurrent requests from different clients to proceed independently.
     * This helps to reduce contention and improve concurrency in high concurrent scenarios.
     * Note that when using locks, it's important to handle exceptions and ensure that locks are properly released to avoid deadlocks or resource leaks.
     * The finally block ensures that the lock is always released, regardless of whether an exception occurs or not.
     *
     * @param clientId
     * @return
     */
    public boolean isAllow (String clientId) {

        long currentTime = System.currentTimeMillis();

        Lock lock = getClientLock(clientId);
        lock.lock();
        try {
            Queue<Long> requestTimestamps = clientRequestTimestamps.get(clientId);

            if (requestTimestamps == null) {
                requestTimestamps = new LinkedList<>();
                requestTimestamps.offer(currentTime);
                clientRequestTimestamps.put(clientId, requestTimestamps);
                return true;
            }

            while (!requestTimestamps.isEmpty() && currentTime - requestTimestamps.peek() > interval) { //or --> currentTime + requestTimestamps.peek() > interval
                requestTimestamps.poll();
            }
            if (requestTimestamps.size() == maxRequests) {
                return false;
            }

            if (requestTimestamps.size() < maxRequests) {
                requestTimestamps.offer(currentTime);
                clientRequestTimestamps.put(clientId, requestTimestamps);
                return true;
            }
        }
        finally {
            lock.unlock();
        }
        return false;
    }
    private Lock getClientLock(String clientId){
        return clientLocks.computeIfAbsent(clientId, k -> new ReentrantLock());
    }
}
/** TEST CASE FOR CONCURRENCY
 *   // AtomicLong to generate unique IDs for each thread
 *     private static final AtomicLong threadIdGenerator = new AtomicLong(1);
 *
 *     @Test
 *     public void testConcurrency() throws InterruptedException {
 *         int numThreads = 10;
 *         CountDownLatch latch = new CountDownLatch(numThreads);
 *
 *         for (int i = 0; i < numThreads; i++) {
 *             // Create a unique clientId for each thread
 *             String clientId = "testClientId-" + threadIdGenerator.getAndIncrement();
 *             new Thread(() -> {
 *                 try {
 *                     isAllow(clientId);
 *                 } finally {
 *                     latch.countDown();
 *                 }
 *             }).start();
 *         }
 *
 *         latch.await();
 *
 *         // Assertions...
 *     }
 */
