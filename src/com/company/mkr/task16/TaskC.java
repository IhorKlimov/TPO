package com.company.mkr.task16;

import java.util.concurrent.BlockingQueue;

public class TaskC implements Runnable {
    private BlockingQueue<Integer> buffer;

    public TaskC(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int message = buffer.take(); message != -1; message = buffer.take()) {
                System.out.println("Message received in thread C: "+ message);
            }
        } catch (InterruptedException e) {
            // NOOP
        }
    }
}
