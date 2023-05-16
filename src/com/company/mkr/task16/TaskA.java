package com.company.mkr.task16;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class TaskA implements Runnable {
    private final BlockingQueue<Integer> buffer;
    private final Random random = new Random();

    public TaskA(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < 100_000; i++) {
                buffer.put(random.nextInt(Integer.MAX_VALUE));
                Thread.sleep(random.nextInt(4000));
            }
            buffer.put(-1);
        } catch (InterruptedException e) {
            // NOOP
        }
    }
}
