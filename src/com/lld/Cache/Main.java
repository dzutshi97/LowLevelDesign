package com.lld.Cache;

import com.lld.Cache.models.LRUCache;

public class Main {

    public static void main(String[] args) {

        LRUCache<String,String> cache = new LRUCache<String, String>(5000,3); //5 secs TTL, 3 items

        cache.put("1", "one");
        cache.put("2", "two");
        cache.put("3", "three");

        System.out.println("Get key 1: " + cache.get("1"));
        System.out.println("Get key 2: " + cache.get("2"));

        cache.put("4", "four");
        System.out.println("Get key 3 (should be null): " + cache.get("3"));
        System.out.println("Get key 4: " + cache.get("4"));

        try {
            Thread.sleep(6000); // Wait for TTL to expire
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Get key 1 (should be null due to TTL expiry): " + cache.get("1"));
        System.out.println("Get key 4 (should be null due to TTL expiry): " + cache.get("4"));

    }
}
