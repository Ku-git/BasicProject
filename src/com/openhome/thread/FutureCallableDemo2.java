package com.openhome.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallableDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println("第30個費式數列, 等待回覆");

        Future<Long> future = executorService.submit(() -> fibonacci(30));
        while(!future.isDone()) {
            System.out.println("尚未完成");
        }

        System.out.println("ans: " + future.get());
    }

    static long fibonacci(long n) {
        if(n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
