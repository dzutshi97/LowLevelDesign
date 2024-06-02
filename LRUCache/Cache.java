package com.lld.LRUCache;

public interface Cache<K,V> {

    //In Java, methods declared within an interface are implicitly public and abstract, meaning they are accessible from any class that implements the interface,
    void put(K key, V value);

    V get(K key);
    void evict();

}
