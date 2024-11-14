package org.example.rootPackage.future3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> System.out.println("Hello"), "succeed");  //callable parameter

        System.out.println(future.get());   //blocking call
        if (future.isDone()) {
            System.out.println("Task is done !");
        }

        executorService.shutdown();
    }
}