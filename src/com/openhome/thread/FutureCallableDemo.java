package com.openhome.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureCallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Long> the30thFibFuture = new FutureTask<>(() -> fibonacci(30));

        System.out.println("第30個費式數列, 等待回覆");
        new Thread(the30thFibFuture).start();
        while (!the30thFibFuture.isDone()) {
            System.out.println("尚未完成");
        }

        System.out.println("ans: " + the30thFibFuture.get());
    }

    static long fibonacci(long n) {
        if(n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
