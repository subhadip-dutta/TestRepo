package org.example.rootPackage.executor2;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[9];
        for (int i = 1; i < 10; i++) {
            int finalI = i; //Variable used in lambda expression should be final or effectively final

            threads[i - 1] = new Thread(
                    () -> {
                        long result = factorial(finalI);
                        System.out.println("Thread name : " + Thread.currentThread().getName() + " | Result : " + result);
                    }
            );

            threads[i - 1].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Total time : " + (System.currentTimeMillis() - startTime));
    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
