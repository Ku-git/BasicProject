package com.openhome.thread;

public class Variable3Test {

    public static void main(String[] args) {

        var thread1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Variable3.increment();
            }
        });

        var thread2 = new Thread(() -> {
            while (true) {
                Variable3.showChanged();
            }
        });

        thread1.start();
        thread2.start();
    }
}

class Variable3 {
    static volatile int i = 0;
    static int j = 0;

    static void increment() {
        i++;
        System.out.println("thread1 變更了 i: " + i);
    }

    static void showChanged() {
        if (i != j) {
            j = i;
            System.out.println("i 變更了: " + i);
        }
    }
}
