package com.openhome.thread.produceAndConsume;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDemo3 {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
        new Thread(new Producer3(queue)).start();
        new Thread(new Consumer3(queue)).start();
    }
}
