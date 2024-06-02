package com.lld.LRUCache;

import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K,V> implements Cache<K,V>{
    ConcurrentHashMap<K,V> map;
    ConcurrentLinkedDeque<K> q;
    int MAX_CAPACITY;
    int ttl;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int maxCapacity, int ttl) {
        this.map = new ConcurrentHashMap<>();
        this.q = new ConcurrentLinkedDeque<>();
        this.MAX_CAPACITY = maxCapacity;
        this.ttl = ttl;
//        Thread t = new Thread(() -> {
//            while(true){
//                try{
//                    Thread.sleep(1000);
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//                evict();
//            }
//        });
//        t.start();
        //Schedule evict thread to run periodically
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::evict, 0, 1, TimeUnit.SECONDS);
    }
    @Override
    public void put(K key, V value) {
       lock.writeLock().lock();
        try {
            if(map.size() >= MAX_CAPACITY){
                K k = q.removeLast();
                map.remove(k);
            }
            map.put(key, value);
            q.addFirst(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }
    @Override
    public V get(K key) {
        lock.readLock().lock();
        try {
            if(!map.containsKey(key)) {
                return null;
            }
            V val = map.get(key);
            q.remove(key);
            q.addFirst(key);
            return val;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }
    @Override
    public void evict() {
        Iterator<K> iterator = q.iterator();
        while(iterator.hasNext()){
            K key = iterator.next();
            CacheEntry<V> val = (CacheEntry<V>) map.get(key);
            if((val.getLastAccessedTime() + val.getTtl()) >= System.currentTimeMillis()){
                System.out.println("Removing the element ="+val.getValue());
                iterator.remove();
                map.remove(key);
            }
        }
    }
}
// TC:  map.put(key, (V) new CacheEntry<V>(value,4));
/**
 * Using an iterator is recommended for several reasons:
 *
 * Concurrent Modification: When you're iterating over a collection and modifying it at the same time,
 * using an iterator prevents concurrent modification exceptions.
 * Directly removing elements from a collection while iterating over it can lead to unpredictable behavior and may throw ConcurrentModificationException.
 */

/**
 *
 * When to call evict method?
 * Periodically: You can run a background thread that periodically calls evict() at fixed intervals or after a certain amount of time has elapsed.
 * This ensures that the cache is regularly cleaned up, even if it's not actively being used.
 *
 * On cache access: You can call evict() whenever the cache is accessed, such as when a get() or put() operation is performed. This ensures that expired items are removed whenever the cache is used, rather than waiting for a specific trigger.
 */