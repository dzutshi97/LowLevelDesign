package com.lld.LRUCache;

public class CacheEntry<V> {

    private V value;
    private long lastAccessedTime;
    private long ttl;

    public CacheEntry(V value, long ttl) {
        this.value = value;
        this.lastAccessedTime = System.currentTimeMillis();
        this.ttl = ttl;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
