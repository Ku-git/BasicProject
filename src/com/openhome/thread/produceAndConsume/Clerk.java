package com.openhome.thread.produceAndConsume;

public class Clerk {

    private final int EMPTY = 0;
    private int product = EMPTY;

    public synchronized void setProduct(int product) throws InterruptedException {
        try {
            waitIfFull();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.product = product;
        System.out.printf("生產者設定 (%d)%n", this.product);
        notify();
    }

    private synchronized void waitIfFull() throws InterruptedException {
        while (this.product != EMPTY) {
//            System.out.println("產生滿了 需要等待");
            wait();
        }
    }

    public synchronized int getProduct() throws InterruptedException {
        waitIfEmpty();
        int product = this.product;
        this.product = EMPTY;
        System.out.printf("消費者取走了 (%d)%n", product);
        notify();
        return product;
    }

    private synchronized void waitIfEmpty() throws InterruptedException {
        while (this.product == EMPTY) {
//            System.out.println("產生空了 需要等待");
            wait();
        }
    }
}
