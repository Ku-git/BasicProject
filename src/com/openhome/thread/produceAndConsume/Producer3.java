package com.openhome.thread.produceAndConsume;

import java.util.concurrent.BlockingQueue;

public class Producer3 implements Runnable {

    private BlockingQueue<Integer> produceQueue;

    public Producer3(BlockingQueue<Integer> produceQueue) {
        this.produceQueue = produceQueue;
    }

    public void run() {
        System.out.println("生產者開始產生");
        for(int i = 0; i <= 10; i++) {
            try {
                produceQueue.put(i);
                System.out.println("生產者提供整數 " + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
