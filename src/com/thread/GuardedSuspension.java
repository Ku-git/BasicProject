package com.thread;

import java.util.Random;

public class GuardedSuspension {

    public static void main(String[] args) {

        testSemaphore();

        testBarrier();
    }

    private static void testSemaphore() {
        var MAX = 5;
        var semaphore = new Semaphore(MAX);
        var random = new Random();

        for(int i = 0; i < 10; i++) {
            pause(random.nextInt(0, 5) * 10);
            final int no = i;
            new Thread(() -> {
                while (true) {
                    semaphore.acquire();
                    System.out.println(no + " 進場");
                    pause(random.nextInt(0, 10) * 100);

                    semaphore.release();
                    System.out.println(no + " 離場");
                    System.out.println(Thread.currentThread().getName() + " stop");
                    Thread.currentThread().stop();
                }
            }).start();

        }
    }

    static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testBarrier() {
        var MAX = 2;
        var barrier = new Barrier(MAX);

        var server = new Thread(() -> {
            barrier.await();
            System.out.println("Server go!");
        });

        var client = new Thread(() -> {
            barrier.await();
            System.out.println("client go!");
        });

        server.start();
        client.start();
    }
}

/**
 * 標準 API 可能就內建了 Semaphore，java.util.concurrent.Semaphore
 * 概念與原理:
 *  Semaphore 是一種計數信號量，它控制同時訪問某個特定資源的執行緒數量。
 * 優點:
 *  1. 控制資源的並發訪問，避免資源爭用。
 *  2. 支援公平模式（FIFO）和非公平模式（LIFO），靈活性高。
 * 缺點:
 *  1. 需要小心設計，避免死鎖或資源枯竭等問題。
 * 使用場景
 *  1. 限制同一時間訪問某資源的執行緒數量，比如限制同時訪問一個文件、資料庫或其他共享資源的執行緒數量。
 *  2. 用於控制執行緒池的大小。
 * 使用場景
 *  1. 多執行緒協同完成分階段任務，如並行計算、任務分塊處理。
 *  2. 執行多個獨立執行緒的階段性工作，等待所有執行緒完成某一階段後再進入下一階段。
 */
class Semaphore {
    private int count;

    public Semaphore(int count) {
        this.count = count;
    }

    synchronized void acquire() {
        while (count == 0) {
            System.out.println("執行緒已滿");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        count--;
    }

    synchronized void release() {
        count++;
        if (count == 1) {
           notifyAll();
        }
    }
}

/**
 * Java 標準 API，有java.util.concurrent.CyclicBarrier
 * 概念與原理:
 *   Barrier 是一種屏障，同步點，它允許一組執行緒在達到某個同步點之前彼此等待。
 * 優點
 *  1. 簡化多執行緒的階段性協調工作。
 *  2. 支援重用，可以多次使用同一個屏障。
 * 缺點
 *  1. 當某個執行緒出現問題，無法到達屏障點時，可能會導致死鎖。
 *  2. 不適用於動態變化的執行緒數量。
 * Barrier vs countDownLatch
 *  Barrier 適用於多個執行緒在多個階段進行同步。
 *      ex: 用於多個執行緒在多個同步點達成一致，允許重複使用。在每個同步點之後，所有執行緒繼續執行下一階段。
 *  CountDownLatch 適用於主執行緒等待子執行緒完成或多個事件同步。
 *      ex: 用於一次性同步，主執行緒等待多個子執行緒完成，計數器只能減到零後才繼續執行。
 */
class Barrier {
    private final int limit;
    private int count;

    public Barrier(int limit) {
        this.limit = limit;
    }

    synchronized void await() {
        count++;
        while (count < limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
    }
}