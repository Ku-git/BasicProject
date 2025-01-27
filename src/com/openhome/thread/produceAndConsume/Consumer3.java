package com.openhome.thread.produceAndConsume;

import java.util.concurrent.BlockingQueue;

public class Consumer3 implements Runnable {

    private BlockingQueue<Integer> produceQueue;

    public Consumer3(BlockingQueue<Integer> produceQueue) {
        this.produceQueue = produceQueue;
    }

    public void run() {
        System.out.println("消費者開始消耗");
        for(int i = 0; i <= 10; i++) {
            try {
                int product = produceQueue.take();
                System.out.println("消費者消耗整數 " + product);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
