package com.leetcode.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private class Node {
        private Node prev;
        private Node next;
        private int key;
        private int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(0, 0);//dummy head
        this.tail = new Node(99, 99);//dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {

        if(!map.containsKey(key)) {
            return -1;
        }
        Node current = map.get(key);
        removeNode(current);
        addToHead(current);

        return map.get(key).value;
    }

    public void put(int key, int value) {

        Node node;
        if(map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            removeNode(node);
        } else {
            node = new Node(key, value);
        }
        map.put(key, node);
        if(map.size() > capacity) {
            int removeKey = tail.prev.key;
            removeNode(tail.prev);
            map.remove(removeKey);
        }
        addToHead(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
