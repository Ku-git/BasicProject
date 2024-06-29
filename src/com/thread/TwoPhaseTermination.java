package com.thread;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TwoPhaseTermination {

    private static AtomicInteger count = new AtomicInteger(0);
    /**
     * 延續work thread，處理中止的情形
     * Interrupt:
     *  執行緒可能在任務處理過程進入等待狀態，如果想在送出停止需求後，
     *  讓進入等待狀態的執行緒醒來，就 Java 而言，可以執行 Thread 的 interrupt 方法，
     *  執行緒會離開等待狀態，並從等待處拋出 InterruptedException。
     */
    public static void main(String[] args) throws InterruptedException {

        var r1 = generateRunnable();
        WorkerThreadService.submit(r1);
        Thread.sleep(500);

        var r2 = generateRunnable();
        WorkerThreadService.submit(r2);
        Thread.sleep(500);

        WorkerThreadService.terminate();
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
    private boolean isWorkable = true;

    Worker(BlockingQueue<Runnable> runnables) {
        this.runnables = runnables;
    }

    public void run() {
        while(isWorkable) {
            Optional<Runnable> task = getTask();
            if (task.isPresent()) {
                task.get().run();
            }
        }
        System.out.println("Worker is terminating...");
    }

    private Optional<Runnable> getTask() {

        try {
            return Optional.of(runnables.take());
        } catch (InterruptedException e) {
            System.out.println("worker is interrupted");
        }
        return Optional.empty();
    }

    void terminate() {
        isWorkable = false;
        this.interrupt();
    }
}

class WorkerThreadService {
    private static final BlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>();
    private static final Worker worker;

    static {
        worker = new Worker(runnables);
        worker.start();
    }

    static void submit(Runnable runnable) {
        runnables.add(runnable);
    }

    static void terminate() {
        worker.terminate();
    }

}