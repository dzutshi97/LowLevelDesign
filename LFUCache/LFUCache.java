package com.lld.LFUCache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LFUCache<K,V> implements Cache<K,V>{
    HashMap<K,V> map;
    PriorityQueue<Pair> pq;
    int MAX_CAPACITY;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LFUCache(int capacity) {
        this.map = new HashMap<>();
        this.pq = new PriorityQueue<>(Comparator.comparingInt((Pair a) -> a.frequency));
        this.MAX_CAPACITY = capacity;
    }
    public class Pair{
        V value;
        K key;
        int frequency = 0;
    }

    @Override
    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            Pair pair = (Pair) map.get(key);
            if (pair == null) {
                //check if size of map is full, if yes, evict the least frequently used
                if (map.size() >= MAX_CAPACITY) {
                    evict();
                }
                Pair p = new Pair();
                p.frequency++;
                p.value = value;
                p.key = key;
                pq.offer(p);
                map.put(key, (V) p);
                return;
            }
            pair.frequency++;
            pair.value = value;
            if (pq.remove(pair)) {
                pq.offer(pair);
            }
        }finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public V get(K key) {
        lock.writeLock().lock();
        try {
            Pair pair = (Pair) map.get(key);
            if (pair == null) {
                System.out.println("Element not found!");
                return null;
            }
            pair.frequency++;
//            lock.writeLock().lock();
//            try {
                if (pq.remove(pair)) {
                    pq.offer(pair);
                }
//            }finally {
//                lock.writeLock().unlock();
//            }
            return (V) pair;
        }finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void evict() {
        //how many items you want to evict?
        while(map.size() >= MAX_CAPACITY){
            Pair p = pq.remove();
            map.remove(p.key);
        }
    }
}
