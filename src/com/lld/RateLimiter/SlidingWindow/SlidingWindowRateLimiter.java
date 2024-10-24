package com.lld.RateLimiter.SlidingWindow;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Sliding window rate limiter. TC: O(1) since we are using deque. No need of using exlicit read/write locks
 * as we are using thread safe data structure - ConcurrentLinkedDeque
 *
 * Performance Considerations:
 * Client-Level Locking: Each client gets its own lock, which minimizes contention across different clients,
 * allowing concurrent requests for different clients to proceed without blocking each other.
 * The lock ensures that requests for the same client are handled sequentially.
 *
 * Lock Creation Overhead: computeIfAbsent lazily creates a lock for each client, so locks are only created when necessary.
 */
//  * link: https://leetcode.com/discuss/interview-question/system-design/124558/Uber-or-Rate-Limiter
//  * Whenever you expose a web service / api endpoint, you need to implement a rate limiter to prevent abuse of the service (DOS attacks).
//  *
//  * Implement a RateLimiter Class with an isAllow method. Every request comes in with a unique clientID, deny a request if that client has made more than 100 requests in the past second.
//  */
public class SlidingWindowRateLimiter {
    private final Map<String, ConcurrentLinkedDeque<Long>> clientRequestTimestamps = new ConcurrentHashMap<>();
    private final int maxRequests;
    private final long interval;
    //if question asks to add a client level lock:
    private final Map<String, Lock> clientLocks = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int maxRequests, long interval) {
        this.maxRequests = maxRequests;
        this.interval = interval;
    }

    public boolean isAllow(String clientId) {
        long currentTime = System.currentTimeMillis();

        Lock lock = clientLocks.computeIfAbsent(clientId, k -> new ReentrantLock());
        lock.lock(); //needed only for client level locking
        try {
            ConcurrentLinkedDeque<Long> deque = clientRequestTimestamps.computeIfAbsent(clientId, k -> new ConcurrentLinkedDeque<>());
            if (deque.isEmpty()) {
                deque.offerLast(currentTime);
//            clientRequestTimestamps.put(clientId, deque); <--Unnecessary Calls to put(). This is redundant & gives concurrentModificationException
//            When clientRequestTimestamps.put(clientId, deque) is called, multiple
//            threads can overwrite each other, leading to race conditions.
                return true;
            }

            while (!deque.isEmpty() && currentTime - deque.peekFirst() > interval) {
                deque.pollFirst();
            }

            if (deque.size() >= maxRequests) {
                return false;
            }

            deque.offer(currentTime);
//        clientRequestTimestamps.put(clientId, deque);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(5, 10000); // max 5 requests in 10 seconds

        // Test Case 1: Allow 5 requests within the rate limit
        System.out.println("Test Case 1: Within Rate Limit");
        String client1 = "client1";
        for (int i = 1; i <= 5; i++) {
            boolean allowed = rateLimiter.isAllow(client1);
            System.out.println("Request " + i + " allowed: " + allowed); // Should all print true
        }

        // Test Case 2: Exceed Rate Limit
        System.out.println("\nTest Case 2: Exceed Rate Limit");
        boolean exceededRequest = rateLimiter.isAllow(client1);
        System.out.println("Request after limit exceeded: " + exceededRequest); // Should print false
//
        // Wait for 10 seconds to allow the window to slide
        System.out.println("\nWaiting for the window to slide (10 seconds)...");
        Thread.sleep(10000); // Sleep for 10 seconds

        // Test Case 3: After sliding window, requests should be allowed again
        System.out.println("Test Case 3: After Sliding Window");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = rateLimiter.isAllow(client1);
            System.out.println("Request " + i + " after sliding window allowed: " + allowed); // Should all print true
        }

        // Test Case 4: Concurrent requests from different clients
        System.out.println("\nTest Case 4: Concurrent Requests from Different Clients");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        String client2 = "client2";
        String client3 = "client3";

        for (int i = 1; i <= 3; i++) {
            final int requestId = i;
            executorService.submit(() -> {
                System.out.println("Client 2, Request " + requestId + " allowed: " + rateLimiter.isAllow(client2));
                System.out.println("Client 3, Request " + requestId + " allowed: " + rateLimiter.isAllow(client3));
            });
        }

        // Wait for concurrent threads to finish
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        // Test Case 5: Single client making rapid requests (burst)
        System.out.println("\nTest Case 5: Burst Requests from a Single Client");
        for (int i = 1; i <= 6; i++) { // 6th request should be blocked
            boolean allowed = rateLimiter.isAllow(client1);
            System.out.println("Burst Request " + i + " allowed: " + allowed);
        }
    }
}




// package com.lld.RateLimiter.SlidingWindow;


// import java.util.HashMap;
// import java.util.LinkedList;
// import java.util.Map;
// import java.util.Queue;
// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;

// /**
//  * link: https://leetcode.com/discuss/interview-question/system-design/124558/Uber-or-Rate-Limiter
//  * Whenever you expose a web service / api endpoint, you need to implement a rate limiter to prevent abuse of the service (DOS attacks).
//  *
//  * Implement a RateLimiter Class with an isAllow method. Every request comes in with a unique clientID, deny a request if that client has made more than 100 requests in the past second.
//  */
// public class RateLimiter {
//     private int maxRequests;
//     private long interval;
//     private Map<String, Queue<Long>> clientRequestTimestamps;
//     private Map<String, Lock> clientLocks;


//     public RateLimiter(int maxRequests, long interval) {
//         this.maxRequests = maxRequests;
//         this.interval = interval;
//         this.clientRequestTimestamps = new HashMap<>();
//     }

//     /**
//      * By using explicit locks, each client ID is associated with its own lock, allowing concurrent requests from different clients to proceed independently.
//      * This helps to reduce contention and improve concurrency in high concurrent scenarios.
//      * Note that when using locks, it's important to handle exceptions and ensure that locks are properly released to avoid deadlocks or resource leaks.
//      * The finally block ensures that the lock is always released, regardless of whether an exception occurs or not.
//      *
//      * @param clientId
//      * @return
//      */
//     public boolean isAllow (String clientId) {

//         long currentTime = System.currentTimeMillis();

//         Lock lock = getClientLock(clientId);
//         lock.lock();
//         try {
//             Queue<Long> requestTimestamps = clientRequestTimestamps.get(clientId);

//             if (requestTimestamps == null) {
//                 requestTimestamps = new LinkedList<>();
//                 requestTimestamps.offer(currentTime);
//                 clientRequestTimestamps.put(clientId, requestTimestamps);
//                 return true;
//             }

//             while (!requestTimestamps.isEmpty() && currentTime - requestTimestamps.peek() > interval) { //or --> currentTime + requestTimestamps.peek() > interval
//                 requestTimestamps.poll();
//             }
//             if (requestTimestamps.size() == maxRequests) {
//                 return false;
//             }

//             if (requestTimestamps.size() < maxRequests) {
//                 requestTimestamps.offer(currentTime);
//                 clientRequestTimestamps.put(clientId, requestTimestamps);
//                 return true;
//             }
//         }
//         finally {
//             lock.unlock();
//         }
//         return false;
//     }
//     private Lock getClientLock(String clientId){
//         return clientLocks.computeIfAbsent(clientId, k -> new ReentrantLock());
//     }
// }
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
