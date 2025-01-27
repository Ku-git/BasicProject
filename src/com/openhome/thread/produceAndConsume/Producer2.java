package com.openhome.thread.produceAndConsume;

public class Producer2 implements Runnable {

    private Clerk2 clerk;

    public Producer2(Clerk2 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生產者產生整數...");
        for (int product = 1; product <= 100; product++) {
            try {
                clerk.setProduct(product);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
