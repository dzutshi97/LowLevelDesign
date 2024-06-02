package com.lld.LFUCache;

public class Main {

    public static void main(String[] args) {

        LFUCache cache = new LFUCache(3);
        for(int i =0;i<4;i++){
            cache.put(i,"value-"+i);
        }

        LFUCache.Pair p1 = (LFUCache.Pair) cache.get(1);
        LFUCache.Pair p2 = (LFUCache.Pair) cache.get(2);
        LFUCache.Pair p3 = (LFUCache.Pair) cache.get(2);
        LFUCache.Pair p4 = (LFUCache.Pair) cache.get(2);
        LFUCache.Pair p5 = (LFUCache.Pair) cache.get(2);

        for(int i =5;i<8;i++){
            cache.put(i,"value-"+i);
        }
        LFUCache.Pair p6 = (LFUCache.Pair) cache.get(0); //evicted
        LFUCache.Pair p7 = (LFUCache.Pair) cache.get(3); //evicted


    }
}
