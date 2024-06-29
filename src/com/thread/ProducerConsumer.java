package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    /**
     * 協調生產／消費
     * 設計概念:
     * 如果你讓生產者／消費者直接處理等待、通知的話，
     * 會產生複雜的同步問題，沒有搞好的話，還可能產生死結；
     * 不如將等待、通知的職責，交由一個居中的協調者。
     * 描述上像是Mediator
     */
    public static void main(String[] args) {

        var clerk = new ProducerAndConsumer1.Clerk();
        new Thread(new ProducerAndConsumer1.Producer(clerk)).start();
        new Thread(new ProducerAndConsumer1.Consumer(clerk)).start();

        // 容量為1
        BlockingQueue<Integer> productQueue = new ArrayBlockingQueue<>(1);
        new Thread(new ProducerConsumer2.Producer(productQueue)).start();
        new Thread(new ProducerConsumer2.Consumer(productQueue)).start();
    }
}

class ProducerAndConsumer1 {

    static class Producer implements Runnable {

        private Clerk clerk;

        public Producer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {

            for(int i = 1; i < 10; i++) {
                try {
                    clerk.setProduct(i);
                    System.out.println("set product: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private Clerk clerk;

        public Consumer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {

            for (int i = 1; i < 10; i++) {
                try {
                    int val = clerk.getProduct();
                    System.out.println("get product:" + val);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Clerk {

        private final int EMPTY = 0;
        private int product = EMPTY;

        synchronized void setProduct(int product) throws InterruptedException {

            waitIfFull();
            this.product = product;
            notify();
        }

        //可重入同步(Reentrant synchronization)
        synchronized void waitIfFull() throws InterruptedException {
            if(this.product != EMPTY) {
                wait();
            }
        }

        synchronized int getProduct() throws InterruptedException {

            waitIfEmpty();
            var p = this.product;
            this.product = EMPTY;
            notify();
            return p;
        }

        private synchronized void waitIfEmpty() throws InterruptedException {
            while (this.product == EMPTY) {
                wait();
            }
        }

    }
}

class ProducerConsumer2 {

    static class Producer implements Runnable {

        private BlockingQueue<Integer> productQueue;

        public Producer(BlockingQueue<Integer> productQueue) {
            this.productQueue = productQueue;
        }

        @Override
        public void run() {

            for(var product = 1; product < 10; product++) {
                try {
                    productQueue.put(product);
                    System.out.println("set product: " + product);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private BlockingQueue<Integer> productQueue;

        public Consumer(BlockingQueue<Integer> productQueue) {
            this.productQueue = productQueue;
        }

        @Override
        public void run() {

            for (int i = 1; i < 10; i++) {
                try {
                    var product = productQueue.take();
                    System.out.println("get product:" + product);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}