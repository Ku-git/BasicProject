package com.openhome.thread.produceAndConsume;

public class Producer implements Runnable {

    private Clerk clerk;

    public Producer(Clerk clerk) {
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
