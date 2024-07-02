package com.thread;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteLockDemo {

    /**
     * Java 標準 API 的 ReadWriteLock 介面定義了讀取鎖定與寫入鎖定行為，
     * 可以使用 readLock、writeLock 方法傳回 Lock 實作物件，可以依需求取得不同的讀寫鎖定實作物件。
     * 例如，ReentrantReadWriteLock 是 ReadWriteLock 介面的主要實作類別，readLock 方法會傳回
     * ReentrantReadWriteLock.ReadLock 實例，writeLock 方法會傳回 ReentrantReadWriteLock.WriteLock 實例。
     * 實際應用的例子，針對讀取多而寫入少，想增加讀取效率的情況： 下例的arraylist
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Data data = new Data("data count:");

        for(int i = 0; i < 6; i++) {
            executor.execute(() -> {
                try {
                    data.writeData();
                    data.readDate();
                    data.writeData();
                    data.readDate();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            executor.submit(() -> {
               try {
                   data.writeData();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
            });
        }

        executor.shutdown();
    }
}

/**
 * 讀寫鎖:
 *  若有執行緒正在讀取物件狀態，由於沒有變更狀態，其他執行緒若也是讀取，
 *  可以不用鎖定物件，這時只要針對寫入執行緒鎖定就可以了；
 *  若有執行緒正在寫入，可以鎖定物件，其他執行緒必須等待寫入完成，才能取得讀取鎖定。
 *  看似單純，不過在讀取時，由於不鎖定，也就會有許多執行緒正在讀取的可能性，
 *  這時就必須記錄讀取執行緒的數量；
 *  另外，為了避免寫入者飢餓，因為有過多讀取執行緒，造成寫入執行緒遲遲無法取得鎖定，你可能得考慮寫入執行緒優先取得鎖定的問題。
 *  將這些任務考量進去，會構成複雜的鎖定邏輯，比較好的方式是，
 *  設計一個專門管理鎖定的物件，由它來負責讀寫的鎖定與解除問題 -> 目前的data
 */
public class ReadWriteLock {

    private boolean writeFirst = true;
    /**
     * synchronized 本身已經保證了內存可見性和操作的原子性。因此，volatile 關鍵字在這種情況下是多餘的。
     * 且lock是為了不同物件使用，static可以移除
     */
    private /*static volatile*/ int writingWriters = 0;
    private /*static volatile*/ int waitingWriters = 0;
    private /*static volatile*/ int readingReaders = 0;

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (writeFirst && waitingWriters > 0)) {
            wait();
        }
        readingReaders++;
    }

    public synchronized void readUnLock() {
        readingReaders--;
        writeFirst = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;

        while (readingReaders > 0 || writingWriters > 0) {
            wait();
        }
        Thread.sleep(1000);

        waitingWriters--;
        writingWriters++;
    }

    public synchronized void writeUnLock() {
        writingWriters--;
        writeFirst = false;
        notifyAll();
    }
}

class Data {

    private ReadWriteLock lock;
    private String data;
    private static AtomicInteger writeTimes = new AtomicInteger(0);

    public Data(String data) {
        this.data = data;
        lock = new ReadWriteLock();
    }

    public void readDate() throws InterruptedException {
        lock.readLock();
        try {
            doRead();
        } finally {
            lock.readUnLock();
        }
    }

    private void doRead() {
        System.out.println("data: " + data);
    }

    public void writeData() throws InterruptedException {
        lock.writeLock();
        try {
            doWrite();
        } finally {
            lock.writeUnLock();
        }
    }

    private void doWrite() {
        data += writeTimes.incrementAndGet() + ",";
    }

}

class ArrayLockList<E> {
    private java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock();
    private Object[] elems;
    private int next;

    public ArrayLockList(int capacity) {
        elems = new Object[capacity];
    }

    public ArrayLockList() {
        elems = new Object[16];
    }

    public void add(E elem) {
        lock.writeLock().lock();
        try {
            if(next == elems.length) {
                elems = Arrays.copyOf(elems, elems.length * 2);
            }
            elems[next++] = elem;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public E get(int index) {
        lock.readLock().lock();
        try {
            return (E) elems[index];
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