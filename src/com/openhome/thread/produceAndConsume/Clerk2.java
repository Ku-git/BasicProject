package com.openhome.thread.produceAndConsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Clerk2 {

    private final int EMPTY = 0;
    private int product = EMPTY;
    private final Lock lock = new ReentrantLock();
    private final Condition producerCond = lock.newCondition();
    private final Condition consumerCond = lock.newCondition();

    public void setProduct(int product) throws InterruptedException {
        lock.lock();
        try {
            waitIfFull();
            this.product = product;
            System.out.printf("生產者設定 (%d)%n", this.product);
            //通知消費者等待集合中的消費者執行緒
            consumerCond.signal();
        } finally {
            lock.unlock();
        }
    }

    private void waitIfFull() throws InterruptedException {
        while(product != EMPTY) {
//            System.out.println("產生滿了 需要等待");
//            //至生產者等待集等待
            producerCond.await();
        }
    }

    public int getProduct() throws InterruptedException {
        lock.lock();
        try {
            waitIfEmpty();
            int product = this.product;
            this.product = EMPTY;
            System.out.printf("消費者取走了 (%d)%n", product);
            //通知生產者等待集合中的生產者執行緒
            producerCond.signal();
            return product;
        } finally {
            lock.unlock();
        }
    }

    private void waitIfEmpty() throws InterruptedException {
        while(product == EMPTY) {
//            System.out.println("產生空了 需要等待");
            //至消費者等待集等待
            consumerCond.await();
        }
    }
}
