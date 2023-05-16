package com.company.mkr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Mkr {
    public static void main(String[] args) {
        Random random = new Random();

        double[] array = new double[10_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextDouble() * random.nextInt(100);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ArrayList<Task> tasks = new ArrayList<>();

        double parallelSum = 0;

        int numOfTasks = 100;
        int step = array.length / numOfTasks;
        for (int i = 0; i < numOfTasks; i++) {
            int start = i * step;
            int end = start + step;
            tasks.add(new Task(array, start, end));

        }

        try {
            List<Future<Double>> futures = executorService.invokeAll(tasks);

            executorService.shutdown();

            for (Future<Double> future : futures) {
                parallelSum += future.get();
            }

            System.out.println("Sum: " + parallelSum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}


