package com.openhome.thread;

public class Variable2Test {

    public static void main(String[] args) {

        var thread1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Variable2.increment();
            }
        });

        var thread2 = new Thread(() -> {
            while (true) {
                Variable2.showChanged();
            }
        });

        thread1.start();
        thread2.start();
    }
}

class Variable2 {
    static int i = 0;
    static int j = 0;

    static synchronized void increment() {
        i++;
        System.out.println("thread1 變更了 i: " + i);
    }

    static synchronized void showChanged() {
        if (i != j) {
            j = i;
            System.out.println("i 變更了: " + i);
        }
    }
}