package com.openhome.thread;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ArrayList2<T> {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Object[] elements;
    private int next;

    public ArrayList2(int capacity) {
        elements = new Object[capacity];
    }

    public ArrayList2() {
        this(16);
    }

    public void add(T element) {
        lock.writeLock().lock();
        try {
            if(next == elements.length) {
                elements = Arrays.copyOf(elements, 2 * elements.length);
            }
            elements[next++] = element;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public T get(int index) {
        lock.readLock().lock();
        try {
            return (T) elements[index];
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
           return next;
        } finally {
            lock.readLock().unlock();
        }
    }
}
