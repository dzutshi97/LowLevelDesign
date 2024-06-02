package com.lld.LRUCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        Cache<Integer,CacheEntry> cache = new LRUCache(100,3);

        ExecutorService executor = Executors.newFixedThreadPool(5); // Number of concurrent clients
        for (int i=0;i<5;i++){
            final int key = i;
            executor.submit(() -> {
                cache.put(key,new CacheEntry<>("Value-" + key, 6000));
            });
        }
        executor.shutdown();
        // Wait for all tasks to complete
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Verify cache contents after concurrent access
//        assertEquals(maxCapacity, cache.size()); // Asserting that the cache size is not exceeded
        for (int i = 0; i < 5; i++) {
//            assertNull(cache.get(i + 5)); // Asserting that least recently used entries are evicted
            CacheEntry val = cache.get(i);
            System.out.println(val.getValue());

        }
    }
}
