package org.example.rootPackage.explicitLock5;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void performTask() {
        lock.lock();
        try {
            System.out.println("Thread name : " + Thread.currentThread().getName());
            System.out.println("Lock acquired. Performing task by : " + Thread.currentThread().getName());

            // Simulate some work
            String a = "";
            for (int i = 1; i <= 100000; i++) {
                a = a + "a";
            }
            System.out.println("Done by : " + Thread.currentThread().getName());
        } finally {
            // Check if the current thread holds the lock before unlocking
            if (lock.isHeldByCurrentThread()) {
                System.out.println("checking unlock by thread : " + Thread.currentThread().getName());
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();

        Thread t1 = new Thread(example::performTask, "Thread 1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.performTask();
            }
        }, "Thread 2");

        t1.start();
        t2.start();

        // Interrupt t2 after some time to demonstrate interrupt handling
        t2.interrupt(); // Interrupt thread t2
    }
}