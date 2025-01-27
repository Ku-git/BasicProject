package com.openhome.thread;

public class InterruptedDemo {

    public static void main(String[] args) {

        var thread = new Thread(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                System.out.println("wake up!");
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.interrupt();
    }
}
