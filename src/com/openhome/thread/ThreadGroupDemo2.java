package com.openhome.thread;

public class ThreadGroupDemo2 {

    public static void main(String[] args) {

        var group = new ThreadGroup("group");

        var thread1 = new Thread(group, () -> {
            throw new RuntimeException("thread1 測試案例");
        });
        thread1.setUncaughtExceptionHandler((thread, throwable) -> {
            System.out.printf("%s: %s%n", thread.getName(), throwable.getMessage());
        });

        var thread2 = new Thread(group, () -> {
            throw new RuntimeException("thread2 測試案例");
        });

        thread1.start();
        thread2.start();
    }
}
