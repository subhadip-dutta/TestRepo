package org.example.rootPackage.synchronizedBlock;

public class Counter {
    private int count = 0; // shared resource

    public void increment(int i) {
        synchronized (this) {
            count++;
            System.out.println(Thread.currentThread().getName() + " : i = " + i);
        }
        System.out.println("check : " + Thread.currentThread().getName() + " : i = " + i);
        System.out.println("checking : " + Thread.currentThread().getName() + " : i = " + i);
    }

    public int getCount() {
        return count;
    }
}
