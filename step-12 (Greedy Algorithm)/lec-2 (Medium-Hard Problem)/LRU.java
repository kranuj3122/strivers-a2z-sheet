import java.util.*;

class Node {
    Node prev, next;
    int key, val;
    Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.prev = this.next = null;
    }
}

class LRUCache {
    Map<Integer, Node> mp;
    int capacity;
    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.mp = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    public void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }
    public void addNodeAtFront(Node node) {
        Node headNext = this.head.next;
        node.next = headNext;
        node.prev = this.head;
        this.head.next = node;
        headNext.prev = node;
    }
    public void printDLL(Node head) {
        while(head != null) {
            System.out.println(head.key+"->"+head.val);
            head = head.next;
        }
    }
    public int get(int key) {
        if(this.mp.containsKey(key)) {
            Node node = mp.get(key);
            deleteNode(node);
            addNodeAtFront(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(this.mp.containsKey(key)) {
            Node node = mp.get(key);
            node.val = value;
            deleteNode(node);
            addNodeAtFront(node);
        }
        else {
            Node node = new Node(key, value);
            if(this.mp.size() == this.capacity) {
                Node tailPrev = this.tail.prev;
                deleteNode(tailPrev);
                this.mp.remove(tailPrev.key);
            }
            addNodeAtFront(node);
            this.mp.put(key, node);
        }
        // System.out.println(this.mp.toString());
        // printDLL(this.head);
        
    }
}

public class LRU {
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1)); // returns 1
        lru.put(3, 3);                  // evicts key 2
        System.out.println(lru.get(2)); // returns -1 (not found)
        lru.put(4, 4);                  // evicts key 1
        System.out.println(lru.get(1)); // returns -1 (not found)
        System.out.println(lru.get(3)); // returns 3
        System.out.println(lru.get(4)); // returns 4
    }
}