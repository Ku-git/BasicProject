package com.openhome.thread.produceAndConsume;

public class Consumer2 implements Runnable {

    private Clerk2 clerk;

    public Consumer2(Clerk2 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消費者開始消耗整數...");
        for (int i = 1; i <= 100; i++) {
            try {
                clerk.getProduct();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
