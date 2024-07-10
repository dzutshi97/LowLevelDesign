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
