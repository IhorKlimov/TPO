package com.company.mkr.task16;

import java.util.concurrent.BlockingQueue;

public class TaskB implements Runnable {
    private BlockingQueue<Integer> buffer;

    public TaskB(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int message = buffer.take(); message != -1; message = buffer.take()) {
                System.out.println("Message received in thread B: "+ message);
            }
        } catch (InterruptedException e) {
            // NOOP
        }
    }
}
