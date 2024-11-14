package org.example.rootPackage.synchronizedMethod;

public class BankAccount {
    private int balance = 100;

    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

        if (balance >= amount) {
            try {
                System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");

                // Simulate time taken to process the withdrawal
                Thread.sleep(3000);

                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
            } catch (Exception e) {

            }
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }
    }
}