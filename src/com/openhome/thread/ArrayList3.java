package com.openhome.thread;

import java.util.Arrays;
import java.util.concurrent.locks.StampedLock;

public class ArrayList3<T> {

    private StampedLock lock = new StampedLock();
    private Object[] elements;
    private int next;

    public ArrayList3(int capacity) {
        elements = new Object[capacity];
    }

    public ArrayList3() {
        this(16);
    }

    public void add(T element) {
        long stamp = lock.writeLock();
        try {
            if(next == elements.length) {
                elements = Arrays.copyOf(elements, elements.length * 2);
            }
            elements[next++] = element;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public T get(int index) {
        long stamp = lock.tryOptimisticRead();
        Object element = elements[index];
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                element = elements[index];
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return (T) element;
    }

    public int size() {
        long stamp = lock.tryOptimisticRead();
        int size = next;
        if(!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                size = next;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return size;
    }
}
