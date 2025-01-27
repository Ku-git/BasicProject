package com.openhome.thread;

public class PrintExample {
    public static void main(String[] args) {
        Runnable printTask = () -> {
            synchronized(System.out) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        };

        Thread thread1 = new Thread(printTask);
        Thread thread2 = new Thread(printTask);

        thread1.start();
        thread2.start();
    }
}
