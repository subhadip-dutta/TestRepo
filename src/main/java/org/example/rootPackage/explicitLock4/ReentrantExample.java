package org.example.rootPackage.explicitLock4;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void performTask() {
        try {
            // Attempt to acquire lock interruptibly
            lock.lockInterruptibly();

            System.out.println("Thread name : " + Thread.currentThread().getName());
            System.out.println("Lock acquired. Performing task by : " + Thread.currentThread().getName());
            // Simulate some work
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("In EXCEPTION block -");
            System.out.println("\tThread name : " + Thread.currentThread().getName());
            System.out.println("\tInterrupted while waiting for lock.");
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

        //Thread t2 = new Thread(example::performTask, "Thread 2"); // by using method reference ...
        //Thread t2 = new Thread(() -> example.performTask(), "Thread 2");  // by using lambda ...
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.performTask();
            }
        }, "Thread 2");

        t1.start();
        t2.start();

        // Interrupt t2 after some time to demonstrate interrupt handling
        try {
            Thread.sleep(5000);
            t2.interrupt(); // Interrupt thread t2
        } catch (InterruptedException ignored) {
        }
    }
}