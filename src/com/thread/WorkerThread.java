package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerThread {

    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * 設計用意:
     *  執行緒建立後，從任務來源處取得任務，每次執行完後，繼續取得下個任務，
     *  這是 Worker Thread 的基本概念，可應用在一些需要冗長計算或背景執行的請求。
     * 功能邏輯:
     *  Worker thread:
     *      消費任務而言，這樣的 Worker 實現了〈Producer Consumer〉中 Consumer 的角色，
     *      至於協調任務的角色，這邊直接使用 Java 標準 API 的 java.util.concurrent.BlockingQueue。
     *  Worker Thread Service:
     *      將 Worker角色轉為被動，也就是被交辦任務，而不是主動取得任務，
     *      不用知道 BlockingQueue 這類角色的存在，任務的交辦可以由 ThreadService 這類角色負責，
     *      這時 ThreadService 就有執行緒池管理的概念了
     */
    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = generateRunnable();
        WorkerThreadService.submit(r1);

        Thread.sleep(500);
        Runnable r2 = generateRunnable();
        WorkerThreadService.submit(r2);
    }

    private static Runnable generateRunnable() {
        Runnable newRunnable = () -> {
            System.out.println("worker run: " + count.incrementAndGet());
        };
        return newRunnable;
    }

}

class Worker extends Thread {

    private BlockingQueue<Runnable> runnables;

    Worker(BlockingQueue<Runnable> runnables) {
        this.runnables = runnables;
    }

    @Override
    public void run() {
        while (true) {
            try {
                runnables.take().run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class WorkerThreadService {
    private static final BlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>();

    static {
        new Worker(runnables).start();
    }

    static void submit(Runnable runnable) {
        runnables.add(runnable);
    }

}
