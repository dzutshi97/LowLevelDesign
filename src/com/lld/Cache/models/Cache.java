package com.lld.Cache.models;

import java.util.LinkedHashMap;

public interface Cache<K,V> {
    public V get(K key);
    public void put(K key, V value);
    public void evict();
}
