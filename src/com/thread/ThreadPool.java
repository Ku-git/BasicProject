package com.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    /**
     * java.util.concurrent.Executors 的 newCachedThreadPool、newFixedThreadPool
     * 靜態方法來建構想要的執行緒池
     */
    public static void main(String[] args) {

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor();
        List<String> names = new ArrayList<>();
        names.add("Mark");
        names.add("Ku");
        names.add("Rex");
        for(String name: names) {
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " execute " + name);
            });
        }
        threadPool.removeIdle();
    }

    static class Worker extends Thread {

        private Runnable runnable;
        private boolean isOnDuty = true;

        boolean isIdle() {
            return runnable == null;
        }

        void accept(Runnable runnable) {
            System.out.println(this.getName() + " accept runnable");
            synchronized (this) {
                if (isIdle()) {
                    System.out.println(this.getName() + " is Idle, notify another wait worker");
                   this.runnable = runnable;
                   notify();
                }
            }
        }

        public void run() {

            while (isOnDuty) {
                synchronized (this) {
                    runnable.run();
                    runnable = null;
                    System.out.println("worker finish job, then wait");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        void terminate() {
            isOnDuty = false;
            accept(() -> {});
        }
    }
}

class ThreadPoolExecutor {

    private List<ThreadPool.Worker> workers = new ArrayList<>();

    synchronized void submit(Runnable runnable) {

        for (var worker : workers) {
            if (worker.isIdle()) {
                System.out.println("worker is Idle, accept runnable");
                worker.accept(runnable);
                return;
            }
        }

        System.out.println("no workers");
        //沒有空閒的worker
        var worker = new ThreadPool.Worker();
        worker.accept(runnable);
        worker.start();
        workers.add(worker);
    }

    synchronized void removeIdle() {
        for (var worker : workers) {
            if(worker.isIdle()) {
                System.out.println(worker.getName() + " is Idle, go deleting");
                workers.remove(worker);
                worker.terminate();
            }
        }
    }
}
