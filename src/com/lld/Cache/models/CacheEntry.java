package com.lld.Cache.models;

public class CacheEntry<V> {
    V value;
    int ttl;
    long lastUpdatedTime;

    public CacheEntry(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime() {
        this.lastUpdatedTime = System.currentTimeMillis();
    }
}
