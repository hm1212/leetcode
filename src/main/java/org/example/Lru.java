package org.example;

/**
 * @Author hhmm
 * @date 2024/3/8
 **/

import java.util.HashMap;

/**
 * 手写LRU算法
 */

public class Lru {

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node(K key, V value) {
            this.pre = this.next = null;
            this.key = key;
            this.value = value;
        }
    }

    class DoubleLinkedList<K, V> {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.pre = head;
        }

        public void addNode(Node node) {
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        }

        public void removeNode(Node node) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
            node.pre = null;
            node.next = null;
        }

        public Node getLastNode() {
            return tail.pre;
        }


    }

    int capacity;
    DoubleLinkedList<String, String> doubleLinkedList;
    HashMap<String, Node<String, String>> map;

    public Lru(int capacity) {
        this.capacity = capacity;
        this.doubleLinkedList = new DoubleLinkedList<>();
        map = new HashMap<>();
    }

    public String get(String key) {
        if (!map.containsKey(key)) {
            return String.valueOf(-1);
        }
        Node<String, String> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        if (map.containsKey(key)) {
            Node<String, String> node = map.get(key);
            node.value = value;
            map.put(key, node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addNode(node);
        } else {
            Node<String, String> node = new Node<>(key, value);
            if (map.size() == capacity) {
                Node lastNode = doubleLinkedList.getLastNode();
                doubleLinkedList.removeNode(lastNode);
                map.remove(lastNode.key,lastNode);
                doubleLinkedList.addNode(node);
                map.put(key,node);
            } else {
                doubleLinkedList.addNode(node);
                map.put(key,node);
            }
        }
    }

    public static void main(String[] args) {
        Lru lru = new Lru(3);
        lru.put("1", "1");
        lru.put("2", "2");
        lru.put("3", "3");
        System.out.println(lru.map.keySet());
        lru.put("4", "1");
        System.out.println(lru.map.keySet());
        lru.put("3", "1");
        System.out.println(lru.map.keySet());
        lru.put("3", "1");
        System.out.println(lru.map.keySet());
        lru.put("3", "1");
        System.out.println(lru.map.keySet());
        lru.put("5", "1");
        System.out.println(lru.map.keySet());


    }


}
