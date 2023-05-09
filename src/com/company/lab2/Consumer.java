package com.company.lab2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> drop;

    public Consumer(BlockingQueue<Integer> drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        try {
            for (int message = drop.take(); message != -1; message = drop.take()) {
                System.out.format("MESSAGE RECEIVED: %s%n", message);
                Thread.sleep(random.nextInt(5000));
            }
        } catch (InterruptedException e) {
            // NOOP
        }
    }
}
