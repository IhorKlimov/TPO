package com.company.lab2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Integer> drop;

    public Producer(BlockingQueue<Integer> drop) {
        this.drop = drop;
    }

    public void run() {
        int importantInfo[] = new int[1000];
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            importantInfo[i] = random.nextInt(50_000);
        }

        try {
            for (int i = 0; i < importantInfo.length; i++) {
                drop.put(importantInfo[i]);
                Thread.sleep(random.nextInt(5000));
            }
            drop.put(-1);
        } catch (InterruptedException e) {
            // NOOP
        }
    }
}
