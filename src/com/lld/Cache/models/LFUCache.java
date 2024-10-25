package com.lld.Cache.models;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Map;

public class LFUCache<K, V> implements Cache<K, V> {

    private final ConcurrentHashMap<K, CacheEntry<K, V>> map;
    private final ConcurrentHashMap<K, Integer> frequencyMap;
    private final TreeMap<Integer, LinkedHashSet<K>> frequencyBuckets; // To maintain keys by frequency
    private final long ttl;
    private final int MAX_CAPACITY;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LFUCache(int ttl, int maxCapacity) {
        this.MAX_CAPACITY = maxCapacity;
        this.map = new ConcurrentHashMap<>();
        this.frequencyMap = new ConcurrentHashMap<>();
        this.frequencyBuckets = new TreeMap<>(); // TreeMap for sorted frequencies
        this.ttl = ttl;

        Thread evictThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                evict();
            }
        });
        evictThread.start(); // Start eviction thread
    }

    @Override
    public V get(K key) {
        lock.readLock().lock();
        try {
            if (!map.containsKey(key)) {
                return null;
            }
            CacheEntry<K, V> entry = map.get(key);
            if (entry.isExpired(ttl)) {
                removeKey(key);
                return null;
            }
            incrementFrequency(key);
            return entry.getValue();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            if (map.size() >= MAX_CAPACITY) {
                evictLFU();
            }

            CacheEntry<K, V> entry = new CacheEntry<>(key, value);
            map.put(key, entry);
            frequencyMap.put(key, 1); // Initial frequency set to 1
            frequencyBuckets.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void evict() {
        lock.writeLock().lock();
        try {
            Iterator<K> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                K key = iterator.next();
                CacheEntry<K, V> entry = map.get(key);
                if (entry.isExpired(ttl)) {
                    removeKey(key);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void incrementFrequency(K key) {
        int currentFreq = frequencyMap.get(key);
        frequencyMap.put(key, currentFreq + 1);

        // Remove key from old frequency bucket
        frequencyBuckets.get(currentFreq).remove(key);
        if (frequencyBuckets.get(currentFreq).isEmpty()) {
            frequencyBuckets.remove(currentFreq);
        }

        // Add key to new frequency bucket
        frequencyBuckets.computeIfAbsent(currentFreq + 1, k -> new LinkedHashSet<>()).add(key);
    }

    private void evictLFU() {
        if (frequencyBuckets.isEmpty()) {
            return;
        }

        // The first entry in frequencyBuckets has the least frequency
        Map.Entry<Integer, LinkedHashSet<K>> entry = frequencyBuckets.firstEntry();
        LinkedHashSet<K> keys = entry.getValue();
        K keyToEvict = keys.iterator().next();

        removeKey(keyToEvict);
    }

    private void removeKey(K key) {
        // Remove from map, frequencyMap, and frequencyBuckets
        map.remove(key);
        int freq = frequencyMap.remove(key);
        frequencyBuckets.get(freq).remove(key);
        if (frequencyBuckets.get(freq).isEmpty()) {
            frequencyBuckets.remove(freq);
        }
    }

    private static class CacheEntry<K, V> {
        private final V value;
        private final long creationTime;

        public CacheEntry(K key, V value) {
            this.value = value;
            this.creationTime = System.currentTimeMillis();
        }

        public V getValue() {
            return value;
        }

        public boolean isExpired(long ttl) {
            return System.currentTimeMillis() - creationTime > ttl;
        }
    }
}

