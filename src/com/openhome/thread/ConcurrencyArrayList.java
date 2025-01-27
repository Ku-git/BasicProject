package com.openhome.thread;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyArrayList<T> {

    private Lock lock = new ReentrantLock();
    private Object[] elements;
    private int next;

    public ConcurrencyArrayList(int capacity) {
        elements = new Object[capacity];
    }

    public void add(Object element) {
        lock.lock();
        try {
            if(next == elements.length) {
                elements = Arrays.copyOf(elements, elements.length * 2);
            }
            elements[next++] = element;
        } finally {
            lock.unlock();
        }
    }

    public T get(int index) {
        lock.lock();
        try {
            return (T) elements[index];
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return next;
        } finally {
            lock.unlock();
        }
    }


}
