package com.openhome.thread;

import java.util.concurrent.locks.ReentrantLock;

public class NoDeadLockDemo {

    public static void main(String[] args) {
        var resource1 = new NoDeadLockDemo.Resource("resource1");
        var resource2 = new NoDeadLockDemo.Resource("resource2");

        var thread1 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                resource1.cooperate(resource2);
            }
        });

        var thread2 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                resource2.cooperate(resource1);
            }
        });

        thread1.start();
        thread2.start();
    }

    static class Resource {

        private String name;

        private ReentrantLock lock = new ReentrantLock();

        Resource(String name) {
            this.name = name;
        }

        void cooperate(Resource resource) {
            while(true) {
                try {
                    if (lockMeAnd(resource)) {
                        System.out.printf("%s 整合 %s 的資源%n", this.name, resource.name);

                        break;
                    }
                } finally {
                    unLockMeAnd(resource);
                }
            }
        }

        private boolean lockMeAnd(Resource resource) {
            return lock.tryLock() && resource.lock.tryLock();
        }

        private void unLockMeAnd(Resource resource) {
            if(lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            if(resource.lock.isHeldByCurrentThread()) {
                resource.lock.unlock();
            }
        }

    }
}
