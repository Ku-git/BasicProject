package com.openhome.thread;

public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread start");

        var threadB = new Thread(() -> {
            System.out.println("threadB start");
            for(int i = 0; i < 5; i++) {
                System.out.println("threadB execute");
            }
            System.out.println("threadB end");
        });
        threadB.start();
        //ThreadB 加入 Main thread 流程
        threadB.join();
        System.out.println("Main thread end");
    }
}
