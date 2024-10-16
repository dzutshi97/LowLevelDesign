package com.Uber;

/**
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Algo:
 * Store a key-value pair
 * Update a key-value pair
 * Given a key, determine if it exists in the data structure. If it does, return the value. If it doesn't, return -1.
 * When a new key-value pair is added, create a new linked list node and put it at the back.
 * When an existing key is updated or fetched, find its associated linked list node. Move it to the back.
 * When a new key-value pair is added and the size of the data structure exceeds capacity, remove the linked list node at the front.
 *
 * Thread safe approach - https://leetcode.com/problems/lru-cache/solutions/1011481/thread-safe-java-implementation-with-concurrenthashmap-and-reentrantlock/?envType=company&envId=uber&favoriteSlug=uber-three-months
 * We use ConcurrentHashMap, AtomicInteger, and ReentrantLock to achieve thread-safety.
 * Comment posted which is valid acc to me: why do we still need ConcurrentHashMap and AtomicInteger if using lock here?
 *
 * Below is Doubly linked list + HashMap approach
 */
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
}

//https://leetcode.com/problems/lru-cache/solutions/3334371/java-dll-hashmap-solution/?envType=company&envId=uber&favoriteSlug=uber-three-months
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

}
/** TODO: Code on your own
 * class LRUCache {
 *     Node head = new Node(0, 0);
 *     Node tail = new Node(0, 0);
 *     Map<Integer, Node> map = new HashMap<>();
 *     int capacity;
 *
 *     public LRUCache(int capacity) {
 *         this.capacity = capacity;
 *         head.next = tail;
 *         tail.next = head;
 *     }
 *
 *     public int get(int key) {
 *         if (map.containsKey(key)) {
 *             Node node = map.get(key);
 *             remove(node);
 *             insert(node);
 *             return node.value;
 *         } else {
 *             return -1;
 *         }
 *     }
 *
 *     public void put(int key, int value) {
 *         if (map.containsKey(key)) {
 *             remove(map.get(key));
 *        }
 *         if (map.size() == capacity) {
 *             remove(tail.prev);
 *        }
 *        insert(new Node(key, value));
 *     }
 *
 *
 *     private void remove(Node node) {
 *         map.remove(node.key);
 *         node.prev.next = node.next;
 *         node.next.prev = node.prev;
 *
 *     }
 *
 *     private void insert(Node node) {
 *         map.put(node.key, node);
 *         node.next = head.next;
 *         node.next.prev = node;
 *         head.next = node;
 *         node.prev = head;
 *     }
 *
 *
 *
 *     class Node {
 *         Node prev, next;
 *         int value, key;
 *         Node(int key, int value) {
 *             this.key = key;
 *             this.value = value;
 *         }
 *     }
 * }
 *
 * //https://leetcode.com/problems/lru-cache/solutions/3334371/java-dll-hashmap-solution/?envType=company&envId=uber&favoriteSlug=uber-three-months
 * /**
 *  * Your LRUCache object will be instantiated and called as such:
 *  * LRUCache obj = new LRUCache(capacity);
 *  * int param_1 = obj.get(key);
 *  * obj.put(key,value);
 *  */

