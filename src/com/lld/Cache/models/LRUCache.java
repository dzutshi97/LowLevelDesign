package com.lld.Cache.models;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K,V> implements Cache<K,V>{

    private final ConcurrentHashMap<K,CacheEntry> map;
    private final ConcurrentLinkedDeque<K> deque;
    private final long ttl;
    private final int MAX_CAPACITY;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int ttl, int maxCapacity) {
        MAX_CAPACITY = maxCapacity;
        this.map = new ConcurrentHashMap<>();
        this.deque = new ConcurrentLinkedDeque<>();
        this.ttl = ttl;

        Thread t = new Thread(() -> {
            while(true){
                try{
                    Thread.sleep(300);
                }catch (Exception e){
                    e.printStackTrace();
                }
                evict();
            }
        });
        t.start(); //imp
    }

    @Override
    public V get(K key) {
        lock.readLock().lock();
        try{
            if(!map.containsKey(key)){
                return null;
            }
            deque.remove(key);
            deque.addFirst(key);
            CacheEntry<V> val =map.get(key);
            val.setLastUpdatedTime(); //?
            return val.value;
        }finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void put(K key, V value) {
        lock.writeLock().lock();
        try{
            if(map.size() >= this.MAX_CAPACITY){
                K removalK = deque.removeLast();
                map.remove(removalK);
            }
            CacheEntry<V> entry = new CacheEntry<>(value);
            entry.setLastUpdatedTime();
            map.put(key, entry);
            deque.addFirst(key);
        }finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void evict() {
        lock.writeLock().lock();
        try{
            Iterator<K> iterator = deque.iterator();
            while(iterator.hasNext()){
                K key = iterator.next();
                CacheEntry<V> entry = map.get(key);
                if(entry.lastUpdatedTime + entry.getTtl() <= System.currentTimeMillis()){
                    iterator.remove();
                    map.remove(key);
                    deque.remove(key);
                    System.out.println("Evicting key: " + key + ", value: " + entry.getValue());
                }
            }
        }finally {
            lock.writeLock().unlock();
        }
    }
}
/**
 * public class Driver {
 *     public static void main(String[] args) {
 *         LRUCache<String, String> cache = new LRUCache<>(5000, 3);
 *
 *         // Adding entries to the cache
 *         cache.put("1", "One");
 *         cache.put("2", "Two");
 *         cache.put("3", "Three");
 *
 *         System.out.println("Initial Cache:");
 *         System.out.println("Key 1: " + cache.get("1")); // Should print One
 *         System.out.println("Key 2: " + cache.get("2")); // Should print Two
 *         System.out.println("Key 3: " + cache.get("3")); // Should print Three
 *
 *         // Adding one more entry, which should cause the first one to be evicted
 *         cache.put("4", "Four");
 *         System.out.println("\nAfter adding key 4:");
 *         System.out.println("Key 1: " + cache.get("1")); // Should print null (evicted)
 *         System.out.println("Key 2: " + cache.get("2")); // Should print Two
 *         System.out.println("Key 3: " + cache.get("3")); // Should print Three
 *         System.out.println("Key 4: " + cache.get("4")); // Should print Four
 *
 *         // Access key 2 to make it most recently used
 *         cache.get("2");
 *
 *         // Adding another entry, which should cause key 3 to be evicted
 *         cache.put("5", "Five");
 *         System.out.println("\nAfter adding key 5:");
 *         System.out.println("Key 2: " + cache.get("2")); // Should print Two
 *         System.out.println("Key 3: " + cache.get("3")); // Should print null (evicted)
 *         System.out.println("Key 4: " + cache.get("4")); // Should print Four
 *         System.out.println("Key 5: " + cache.get("5")); // Should print Five
 *
 *         // Wait for the TTL to expire and check eviction
 *         try {
 *             Thread.sleep(6000);
 *         } catch (InterruptedException e) {
 *             e.printStackTrace();
 *         }
 *
 *         System.out.println("\nAfter TTL expiration:");
 *         System.out.println("Key 2: " + cache.get("2")); // Should print null (evicted due to TTL)
 *         System.out.println("Key 4: " + cache.get("4")); // Should print null (evicted due to TTL)
 *         System.out.println("Key 5: " + cache.get("5")); // Should print null (evicted due to TTL)
 *     }
 * }
 *
 */
