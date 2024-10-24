package com.Uber;

import java.util.HashMap;
import java.util.Map;

/**
  Implement the LRUCache class:
 
  LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
  int get(int key) Return the value of the key if the key exists, otherwise return -1.
  void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
  The functions get and put must each run in O(1) average time complexity.
 
  Algo:
  Store a key-value pair
  Update a key-value pair
  Given a key, determine if it exists in the data structure. If it does, return the value. If it doesn't, return -1.
  When a new key-value pair is added, create a new linked list node and put it at the back.
  When an existing key is updated or fetched, find its associated linked list node. Move it to the back.
  When a new key-value pair is added and the size of the data structure exceeds capacity, remove the linked list node at the front.
 
  Thread safe approach - https://leetcode.com/problems/lru-cache/solutions/1011481/thread-safe-java-implementation-with-concurrenthashmap-and-reentrantlock/?envType=company&envId=uber&favoriteSlug=uber-three-months
  We use ConcurrentHashMap, AtomicInteger, and ReentrantLock to achieve thread-safety.
  Comment posted which is valid acc to me: why do we still need ConcurrentHashMap and AtomicInteger if using lock here?
 
  Below is Doubly linked list + HashMap approach
 */
//TC: O(1)
  public class LRUCache {
      Node head = new Node(0, 0);
      Node tail = new Node(0, 0);
      Map<Integer, Node> map = new HashMap<>();
      int capacity;
 
      public LRUCache(int capacity) {
          this.capacity = capacity;
          head.next = tail;
          tail.next = head;
      }

    /**
     * get(int key):
     *
     * Checks if the key exists in the cache:
     * If it does, it retrieves the corresponding node, removes it from its current position in the list, and reinserts it at the front (to mark it as recently used), then returns its value.
     * If it doesn’t exist, it returns -1.
     * @param key
     * @return
     */

    public int get(int key) {
          if (map.containsKey(key)) {
              Node node = map.get(key);
              remove(node);
              insert(node);
              return node.value;
          } else {
              return -1;
          }
      }

    /**
     * put(int key, int value):
     *
     * Checks if the key already exists:
     * If it does, it removes the old node.
     * If the cache is at capacity, it removes the least recently used node (the node just before the tail).
     * Finally, it inserts the new node into the cache.
     * @param key
     * @param value
     */
      public void put(int key, int value) {
          if (map.containsKey(key)) {
              remove(map.get(key));
         }
          if (map.size() == capacity) {
              remove(tail.prev);
         }
         insert(new Node(key, value));
      }

      private void remove(Node node) {
          map.remove(node.key);
          node.prev.next = node.next;
          node.next.prev = node.prev;
 
      }
 
      private void insert(Node node) {
          map.put(node.key, node);
          node.next = head.next;
          node.next.prev = node;
          head.next = node;
          node.prev = head;
      }

      class Node {
          Node prev, next;
          int value, key;
          Node(int key, int value) {
              this.key = key;
              this.value = value;
          }
      }

    public static void main(String[] args) {

        // Test Case 1: Initialize LRUCache with capacity 2
        LRUCache cache = new LRUCache(2);

        // Test Case 2: Put key-value pairs
        System.out.println("Put(1, 1):");
        cache.put(1, 1); // Cache is {1=1}

        System.out.println("Get(1): " + cache.get(1)); // Returns 1

        System.out.println("Put(2, 2):");
        cache.put(2, 2); // Cache is {1=1, 2=2}

        System.out.println("Get(2): " + cache.get(2)); // Returns 2

        System.out.println("Put(3, 3):");
        cache.put(3, 3); // Evicts key 1, Cache is {2=2, 3=3}

        System.out.println("Get(1): " + cache.get(1)); // Returns -1 (not found)

        System.out.println("Put(4, 4):");
        cache.put(4, 4); // Evicts key 2, Cache is {3=3, 4=4}

        System.out.println("Get(2): " + cache.get(2)); // Returns -1 (not found)

        System.out.println("Get(3): " + cache.get(3)); // Returns 3

        System.out.println("Get(4): " + cache.get(4)); // Returns 4

        System.out.println("Put(3, 30):");
        cache.put(3, 30); // Update key 3 with a new value, Cache is {4=4, 3=30}

        System.out.println("Get(3): " + cache.get(3)); // Returns 30
        System.out.println("Get(4): " + cache.get(4)); // Returns 4
    }
  }

 //https://leetcode.com/problems/lru-cache/solutions/3334371/java-dll-hashmap-solution/?envType=company&envId=uber&favoriteSlug=uber-three-months

/**
    Your LRUCache object will be instantiated and called as such:
    LRUCache obj = new LRUCache(capacity);
    int param_1 = obj.get(key);
    obj.put(key,value);
   */



