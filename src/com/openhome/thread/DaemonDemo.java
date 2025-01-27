package com.openhome.thread;

public class DaemonDemo {

    public static void main(String[] args) {
        var thread = new Thread(() -> {
            while(true) {
                System.out.println("thread running");
            }
        });
//        thread.setDaemon(true);//註解來測試
        thread.start();
    }
}
