package com.openhome;

import java.util.*;

public class IterableExample {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("test", "test2", "test3");

        System.out.println("list: ");
        list.forEach(System.out::println);
        forEach(list);
        System.out.println("set:");
        Set<String> set = new HashSet<>(list);
        forEach(set);
        set.forEach(System.out::println);
        System.out.println("deque: ");
        ArrayDeque<String> deque = new ArrayDeque<>(list);
        forEach(deque);
        deque.forEach(System.out::println);
    }

    static void forEach(Iterable iterable) {
        for (Object o : iterable) {
            System.out.println(o);
        }
    }


}
