package com.openhome.thread.produceAndConsume;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemo {

    public static void main(String[] args) throws InterruptedException {
        var start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        Clerk clerk = new Clerk();
        var thread1 = new Thread(new Producer(clerk));
        thread1.start();
        threads.add(thread1);
        var thread2 = new Thread(new Consumer(clerk));
        thread2.start();
        threads.add(thread2);

        for(Thread thread: threads) {
            thread.join();
        }

        System.out.println("cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}
