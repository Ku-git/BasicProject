package com.thread;

import java.util.concurrent.Callable;

public class Future<T> implements Runnable {

    private Callable<T> callable;
    private T result;

    public Future(Callable<T> callable) {
        this.callable = callable;
    }

    boolean isDone() {
        return result != null;
    }

    synchronized T get() throws InterruptedException {
        while (result == null) {
            wait();
        }
        return result;
    }

    @Override
    public void run() {

        try {
            synchronized (this) {
                result = callable.call();
                notify();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> Future<T> submit(Callable<T> callable) {
        var future = new Future<>(callable);
        new Thread(future).start();
        return future;
    }

}

class FutureDemo {

    /**
     * 設計用意:
     *  Future 就是讓你在未來取得結果。可以將想執行的工作交給 Future，
     *  Future 會使用另一執行緒來進行工作，你就可以先忙別的事去，過些時候，再從 Future 取得結果。
     * Java Library:
     *  1. Java 的並行 API 中，規範了 java.util.concurrent.Future 等介面，
     *      例如 java.util.concurrent.FutureTask 是 Future 的實作類別，建構時可傳入 Callable 實作物件指定的執行的內容。
     *  2. Executors.newCachedThreadPool 會建立 ExecutorService 的實例，具有執行緒池的功能，
     *      ExecutorService 定義了 submit 方法傳回 Future 實例。
     */
    public static void main(String[] main) throws InterruptedException {

        var future = Future.submit(() -> fibonacci(30));
        System.out.println("fibonacci 30");
        while (!future.isDone()) {
            System.out.println("wait a moment");
        }
        System.out.println("result: " + future.get());
    }

    static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
