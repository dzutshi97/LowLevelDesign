package com.lld.LFUCache.test;

import com.lld.LFUCache.LFUCache;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LFUCacheTest {

    @Test
    public void test1() {
        LFUCache cache = new LFUCache(3);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int j = 0; j < 5; j++) {
            executor.submit(() -> {

                for (int i = 0; i < 4; i++) {
                    cache.put(i, "value-" + i);
                }
                LFUCache.Pair p1 = (LFUCache.Pair) cache.get(1);
                assertNotNull(p1);
                LFUCache.Pair p2 = (LFUCache.Pair) cache.get(2);
                assertNotNull(p2);

                LFUCache.Pair p3 = (LFUCache.Pair) cache.get(2);
                assertNotNull(p3);

                LFUCache.Pair p4 = (LFUCache.Pair) cache.get(2);
                assertNotNull(p4);

                LFUCache.Pair p5 = (LFUCache.Pair) cache.get(2);
                assertNotNull(p5);


                for (int i = 5; i < 8; i++) {
                    cache.put(i, "value-" + i);
                }
                LFUCache.Pair p6 = (LFUCache.Pair) cache.get(0); //evicted
                assertNull(p6);
                LFUCache.Pair p7 = (LFUCache.Pair) cache.get(3);//evicted
                assertNull(p7);

            });
        }
// Shutdown executor
        executor.shutdown();
// Wait for all tasks to complete
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Shutdown executor
// Shutdown executor
        executor.shutdown();
        try {
            // Wait for all tasks to complete
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                // Force shutdown if the waiting period exceeds 60 seconds
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            // Handle interruption
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
