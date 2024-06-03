package com.design;

import java.util.concurrent.*;

public class Singleton {

    //lazy initialization or 若不用lazy init直接new singleton就行
    private static volatile Singleton singleton = null;

    private Singleton() {
    }

    /**
     * 代表應用程式的 application 物件、代表全域的 global 物件、代表環境的 context 物件等，這類資源稱為 singleton
     * point:
     * 關注分離（separation of concerns）
     */
    public static Singleton getInstance() {
        if(singleton == null) {
            synchronized (Singleton.class) {
                if(singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Singleton> task = Singleton::getInstance;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton> future1 = executorService.submit(task);
        Future<Singleton> future2 = executorService.submit(task);

        Singleton singleton1 = future1.get();
        Singleton singleton2 = future2.get();

        assert singleton1 != singleton2: "Both instances should be the same";
        System.out.println(singleton1);
        System.out.println(singleton2);
        executorService.shutdown();
    }
}
